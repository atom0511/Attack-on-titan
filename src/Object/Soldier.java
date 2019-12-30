package Object;

import Effect.Animation;
import Effect.CacheDataLoader;
import Effect.FrameImage;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Soldier extends Human {

    public float diagonalLine;
    private Animation runTrack1, runTrack2;
    private FrameImage hull1, gun;
    public double angle;
    private double gunX;
    private double gunY;
    private AudioClip shoot;

    public Soldier(float posX, float posY, float width, float height, float mass, int blood, GameWorld gameWorld) {
        super(posX, posY, width, height, mass, blood, gameWorld);
        setTeamType(LEAGUE_TEAM);

        setTimeForNoBeHurt(200 * 10000000);

        gun = CacheDataLoader.getInstance().getFrameImage("gun");
        hull1 = CacheDataLoader.getInstance().getFrameImage("hull1");

        runTrack1 = CacheDataLoader.getInstance().getAnimation("track");
        runTrack2 = CacheDataLoader.getInstance().getAnimation("track");
        behurtForwardAnim = CacheDataLoader.getInstance().getAnimation("beHurtHull");
//        behurtBackAnim = CacheDataLoader.getInstance().getAnimation("beHurtHull");

        shoot = CacheDataLoader.getInstance().getSound("shootSoldier");
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect((int) (getPosX() - getWidth() / 2), (int) (getPosY() - getHeight() / 2), (int) getHeight(), (int) getWidth());
        switch (getState()) {
            case ALIVE:
            case NOBEHURT:
                if (getState() == NOBEHURT && (System.nanoTime() / 10000000) % 2 != 1) {
                    System.out.println("Plash...");
                } else {
                    runTrack1.draw(g, (int) getPosX() - 16, (int) getPosY());
                    runTrack2.draw(g, (int) getPosX() + 16, (int) getPosY());
                    hull1.draw(g, (int) getPosX(), (int) getPosY());
                    g.setColor(Color.RED);
                    g.drawOval((int) (getPosX() - 35), (int) (getPosY() + 7 - 35), 70, 70);
                    Graphics2D g2 = (Graphics2D) g;
                    gun.rotateImage(g2, gun.getImage(), (int) getPosX() - 7,
                            (int) getPosY() - 25, Math.toRadians(angle), 7, 15);
                }
                break;

            case BEHURT:
                runTrack1.draw(g, (int) getPosX() - 16, (int) getPosY());
                runTrack2.draw(g, (int) getPosX() + 16, (int) getPosY());
                behurtForwardAnim.draw(g, (int) getPosX(), (int) getPosY());
                g.setColor(Color.RED);
                g.drawOval((int) (getPosX() - 35), (int) (getPosY() + 7 - 35), 70, 70);
                Graphics2D g3 = (Graphics2D) g;
                gun.rotateImage(g3, gun.getImage(), (int) getPosX() - 7,
                        (int) getPosY() - 25, Math.toRadians(angle), 7, 15);

                break;
            case FEY:

                break;

        }

    }

    @Override
    public void Update() {
        super.Update();
        runTrack1.Update(System.nanoTime());
        behurtForwardAnim.Update(System.nanoTime());
    }

    public double getPositionX(double angle) {
        if (angle <= 90) {
            gunX = getPosX() + Math.sin(Math.toRadians(angle)) * 41;
            return gunX;
        } else if (angle > 90 && angle <= 180) {
            gunX = getPosX() + Math.cos(Math.toRadians(angle - 90)) * 41;
            return gunX;
        } else if (angle > 180 && angle <= 270) {
            gunX = getPosX() - Math.sin(Math.toRadians(angle - 180)) * 41;
            return gunX;
        } else if (angle > 270 && angle < 360) {
            gunX = getPosX() - Math.cos(Math.toRadians(angle - 270)) * 41;
            return gunX;
        } else if (angle > 360) {
            gunX = getPosX();
            return gunX;
        }
        return 0;
    }

    public double getPositionY(double angle) {
        if (angle <= 90) {
            gunY = getPosY() + 7 - Math.cos(Math.toRadians(angle)) * 41;
            return gunY;
        } else if (angle > 90 && angle <= 180) {
            gunY = getPosY() + 7 + Math.sin(Math.toRadians(angle - 90)) * 41;
            return gunY;
        } else if (angle > 180 && angle <= 270) {
            gunY = getPosY() + 7 + Math.cos(Math.toRadians(angle - 180)) * 41;
            return gunY;
        } else if (angle > 270 && angle <= 360) {
            gunY = getPosY() + 7 - Math.sin(Math.toRadians(angle - 270)) * 41;
            return gunY;
        }
        return 0;
    }

    public double getAngle(float mouseMoveX, float mouseMoveY, int x, int y) {

        if (mouseMoveY <= (int) getPosY() + 8 && mouseMoveX >= (int) getPosX()) {
            double angle = Math.toDegrees(Math.asin((mouseMoveX - x) / diagonalLine(mouseMoveX, mouseMoveY, x, y))); // sin
            return angle;
        } else if (mouseMoveY > (int) getPosY() + 8 && mouseMoveX > (int) getPosX()) {
            double angle = Math.toDegrees(Math.acos((mouseMoveX - x) / diagonalLine(mouseMoveX, mouseMoveY, x, y))) + 90; // cos
            return angle;
        } else if (mouseMoveY >= (int) getPosY() + 8 && mouseMoveX <= (int) getPosX()) {
            double angle = -Math.toDegrees(Math.asin((mouseMoveX - x) / diagonalLine(mouseMoveX, mouseMoveY, x, y))) + 180;// sin
            return angle;
        } else if (mouseMoveY < (int) getPosY() + 8 && mouseMoveX < (int) getPosX()) {
            double angle = -Math.toDegrees(Math.acos((mouseMoveX - x) / diagonalLine(mouseMoveX, mouseMoveY, x, y))) + 450; // sin
            return angle;
        }
        return 0;
    }

    public void attack(float mouseX, float mouseY) {
        shoot.play();
        Bullet bullet = new SoldierBullet((float) getPositionX(angle) - 5, (float) getPositionY(angle) - 5, getGameWorld());
        bullet.setSpeedX((float) -2 * (getPosX() - mouseX) / (diagonalLine(mouseX, mouseY, getPosX(), getPosY())));
        bullet.setSpeedY((float) -2 * (getPosY() - mouseY) / (diagonalLine(mouseX, mouseY, getPosX(), getPosY())));
        bullet.setTeamType(getTeamType());
        getGameWorld().bulletManager.addObject(bullet);
    }

    public float diagonalLine(float mouseX, float mouseY, float posX, float posY) {
        int x = (int) Math.abs(mouseX);
        int y = (int) Math.abs(mouseY);
        diagonalLine = (float) Math.sqrt((x - posX) * (x - posX) + (y - posY) * (y - posY));
        return diagonalLine;
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        return rect;
    }

    public void run() {
        if (getDirection() == LEFT_DIR) {
            setSpeedX(-3);
        } else {
            setSpeedX(3);
        }
    }

    public void stopRun() {
        setSpeedX(0);
    }

    @Override
    public void hurtingCallback() {
//        System.out.println("Call back hurting");
//        hurtingSound.play();
    }
}
