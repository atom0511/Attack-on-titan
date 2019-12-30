package Object;

import Effect.Animation;
import Effect.CacheDataLoader;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Plane extends ParticularObject {

    private boolean direction = true;
    public long startTimeToShoot;
    private Animation moveFoward, moveBack, moveFowardAndAttack, moveBackAndAttack;
    private float diagonalLine;
    public boolean isShooting = false;

    public Plane(float posX, float posY, float width, float height, float speedX, float speedY,
            float mass, int blood, GameWorld gameWorld) {

        super(posX, posY, width, height, mass, blood, gameWorld);
        setSpeedX(speedX);
        setSpeedY(speedY);

        startTimeToShoot = 0;
        moveFoward = CacheDataLoader.getInstance().getAnimation("movePlane");
        moveBack = CacheDataLoader.getInstance().getAnimation("movePlane");
        moveBack.flipAllImage();

        moveFowardAndAttack = CacheDataLoader.getInstance().getAnimation("moveAndShootPlane");
        moveBackAndAttack = CacheDataLoader.getInstance().getAnimation("moveAndShootPlane");
        moveBackAndAttack.flipAllImage();

    }

    @Override
    public void Update() {
        super.Update();
        moveFoward.Update(System.nanoTime());
        moveBack.Update(System.nanoTime());
        moveFowardAndAttack.Update(System.nanoTime());
        moveBackAndAttack.Update(System.nanoTime());
        int time = 10000000;
        if (System.nanoTime() % 10000 == 0) {
            time = 1000000;
        }
        if (System.nanoTime() - startTimeToShoot > 100 * time) {
            isShooting = false;
        }

        if (getPosX() > 50 && direction == true) {
            setDirection(RIGHT_DIR);
            setPosX(getPosX() - getSpeedX());
        }
        if (getPosX() < 1750 && direction == false) {
            setDirection(LEFT_DIR);
            setPosX(getPosX() + getSpeedX());
        }
        if (getPosX() <= 50) {
            direction = false;
        }
        if (getPosX() >= 1550) {
            direction = true;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect((int) getPosX(), (int) getPosY(), (int) getWidth(), (int) getHeight());
        if (isShooting == false) {
            if (getDirection() == LEFT_DIR) {
                moveFoward.draw(g, (int) (getPosX() + 75), (int) getPosY() + 10);
            } else {
                moveBack.draw(g, (int) (getPosX() + 75), (int) getPosY() + 10);
            }
        } else {
            if (getDirection() == LEFT_DIR) {
                moveFowardAndAttack.draw(g, (int) (getPosX() + 75), (int) getPosY() + 10);
            } else {
                moveBackAndAttack.draw(g, (int) (getPosX() + 75), (int) getPosY() + 10);
            }
        }
    }

    public void attack(float targetX, float targetY) {
        isShooting = true;
        if (getDirection() == LEFT_DIR) {
            Bullet bullet = new PlaneBullet(getPosX() + 125, getPosY() + 30, getGameWorld());
            bullet.setSpeedX((float) -2 * (getPosX() + 75 - targetX)
                    / (diagonalLine(getPosX() + 75, getPosY() + 30, targetX, targetY)));
            bullet.setSpeedY((float) -2 * (getPosY() + 30 - targetY)
                    / (diagonalLine(getPosX() + 75, getPosY() + 30, targetX, targetY)));
            bullet.setTeamType(getTeamType());
            getGameWorld().bulletManager.addObject(bullet);
        } else if (getDirection() == RIGHT_DIR) {
            Bullet bullet = new PlaneBullet(getPosX() + 5, getPosY() + 30, getGameWorld());
            bullet.setSpeedX((float) -2 * (getPosX() + 75 - targetX)
                    / (diagonalLine(getPosX() + 75, getPosY() + 30, targetX, targetY)));
            bullet.setSpeedY((float) -2 * (getPosY() + 30 - targetY)
                    / (diagonalLine(getPosX() + 75, getPosY() + 30, targetX, targetY)));
            bullet.setTeamType(getTeamType());
            getGameWorld().bulletManager.addObject(bullet);
        }
    }

    public float diagonalLine(float gunPlaneX, float gunPlaneY, float targetX, float targetY) {
        int x = (int) Math.abs(gunPlaneX - targetX);
        int y = (int) Math.abs(gunPlaneY - targetY);
        diagonalLine = (float) Math.sqrt(x * x + y * y);
        return diagonalLine;
    }

    public Rectangle rectangle() {
        Rectangle rec = new Rectangle();
        rec.x = (int) (getPosX() - getWidth());
        rec.y = (int) (getPosY() - getHeight());
        rec.height = (int) getHeight();
        rec.width = (int) getWidth();
        return rec;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        return rect;
    }

}
