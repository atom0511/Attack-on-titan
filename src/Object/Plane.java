package Object;

import Effect.Animation;
import Effect.CacheDataLoader;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Plane extends ParticularObject {

    private boolean direction = true;
    public long startTimeToShoot;
    private Animation moveFoward, moveBack, moveFowardAndAttack, moveBackAndAttack;
  
    private float diagonalLine;
    public boolean isShooting = false;

    public Plane(float posX, float posY, float width, float height, float speedX, float speedY,
            int blood, GameWorld gameWorld) {

        super(posX, posY, width, height, 0.1f, blood, gameWorld);
        setSpeedX(speedX);
        setSpeedY(speedY);

        startTimeToShoot = 0;
        setTimeForNoBeHurt(100 * 100000);

        moveFoward = CacheDataLoader.getInstance().getAnimation("movePlane");
        moveBack = CacheDataLoader.getInstance().getAnimation("movePlane");
        moveBack.flipAllImage();
        
        moveFowardAndAttack = CacheDataLoader.getInstance().getAnimation("moveAndShootPlane");
        moveBackAndAttack = CacheDataLoader.getInstance().getAnimation("moveAndShootPlane");
        moveBackAndAttack.flipAllImage();
        
        behurtBackAnim = CacheDataLoader.getInstance().getAnimation("explosion");
    }

    @Override
    public void Update() {
        super.Update();
        moveFoward.Update(System.nanoTime());
        moveBack.Update(System.nanoTime());
        moveFowardAndAttack.Update(System.nanoTime());
        moveBackAndAttack.Update(System.nanoTime());
        behurtBackAnim.Update(System.nanoTime());
        switch (getState()) {
            case ALIVE:
                //shoot random
                int time = 10000000;
                if (System.nanoTime() % 10000 == 0) {
                    time = 1000000;
                }
                if (System.nanoTime() - startTimeToShoot > 100 * time) {
                    isShooting = false;
                }
                //move
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
                break;

            case BEHURT:
                state = NOBEHURT;
                startTimeNoBeHurt = System.nanoTime();
                if (getBlood() == 0) {
                    state = DEATH;
                }
                break;
            case NOBEHURT:
                if (System.nanoTime() - startTimeNoBeHurt > timeForNoBeHurt) {
                    state = ALIVE;
                }
                break;
            case FEY:
                break;
            case DEATH:
                break;

        }
    }

    @Override
    public void draw(Graphics g) {
       
        switch (getState()) {
            case ALIVE:
                if (isShooting == false) {
                    if (getDirection() == LEFT_DIR) {
                        moveFoward.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                    } else {
                        moveBack.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                    }
                } else if (isShooting == true) {
                    if (getDirection() == LEFT_DIR) {
                        moveFowardAndAttack.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                    } else {
                        moveBackAndAttack.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                    }
                }
                
                break;
            case NOBEHURT:
                break;
            case BEHURT:
                if (isShooting == false) {
                    if (getDirection() == LEFT_DIR) {
                        moveFoward.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                        behurtBackAnim.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                    } else {
                        moveBack.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                        behurtBackAnim.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                    }
                } else {
                    if (getDirection() == LEFT_DIR) {
                        moveFowardAndAttack.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                        behurtBackAnim.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                    } else {
                        moveBackAndAttack.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                        behurtBackAnim.draw(g, (int) (getPosX()), (int) getPosY() + 10);
                    }
                }
                break;
            case FEY:
                break;

        }
    }

    public void attack(float targetX, float targetY) {
        isShooting = true;
        if (getDirection() == LEFT_DIR) {
            Bullet bullet = new PlaneBullet(getPosX() + 50, getPosY() + 30, getGameWorld());
            bullet.setSpeedX((float) -2 * (getPosX() - targetX)
                    / (diagonalLine(getPosX() , getPosY() + 30, targetX, targetY)));
            bullet.setSpeedY((float) -2 * (getPosY() + 30 - targetY)
                    / (diagonalLine(getPosX() , getPosY() + 30, targetX, targetY)));
            bullet.setTeamType(getTeamType());
            getGameWorld().bulletManager.addObject(bullet);
        } else if (getDirection() == RIGHT_DIR) {
            Bullet bullet = new PlaneBullet(getPosX() - 45, getPosY() + 30, getGameWorld());
            bullet.setSpeedX((float) -2 * (getPosX()  - targetX)
                    / (diagonalLine(getPosX() , getPosY() + 30, targetX, targetY)));
            bullet.setSpeedY((float) -2 * (getPosY() + 30 - targetY)
                    / (diagonalLine(getPosX() , getPosY() + 30, targetX, targetY)));
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
