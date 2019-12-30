package Object;

import Effect.Animation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class ParticularObject extends GameObject {

    public static final int LEAGUE_TEAM = 0;
    public static final int ENEMY_TEAM = 1;

    public static final int LEFT_DIR = 0;
    public static final int RIGHT_DIR = 1;

    public static final int ALIVE = 0;
    public static final int BEHURT = 1;
    public static final int FEY = 2; // sắp chết 
    public static final int DEATH = 3;
    public static final int NOBEHURT = 4; // vừa hồi sinh

    private int state = ALIVE;

    private float width;
    private float height;
    private float mass;
    private float speedX;
    private float speedY;
    private int blood;
    private int damage;
    private int direction;
    private int teamType;

    private long startTimeNoBeHurt;
    private long timeForNoBeHurt;

    protected Animation behurtForwardAnim, behurtBackAnim;

    public ParticularObject(float posX, float posY, float width, float height, float mass, int blood, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        this.width = width;
        this.height = height;
        this.mass = mass;
        this.blood = blood;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        if (blood >= 0) {
            this.blood = blood;
        } else {
            this.blood = 0;
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getTeamType() {
        return teamType;
    }

    public void setTeamType(int teamType) {
        this.teamType = teamType;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public long getStartTimeNoBeHurt() {
        return startTimeNoBeHurt;
    }

    public void setStartTimeNoBeHurt(long time) {
        this.startTimeNoBeHurt = time;
    }

    public long getTimeForNoBeHurt() {
        return timeForNoBeHurt;
    }

    public void setTimeForNoBeHurt(long timeForNoBeHurt) {
        this.timeForNoBeHurt = timeForNoBeHurt;
    }

    public Animation getBehurtForwardAnim() {
        return behurtForwardAnim;
    }

    public void setBehurtForwardAnim(Animation behurtForwardAnim) {
        this.behurtForwardAnim = behurtForwardAnim;
    }

    public Animation getBehurtBackAnim() {
        return behurtBackAnim;
    }

    public void setBehurtBackAnim(Animation behurtBackAnim) {
        this.behurtBackAnim = behurtBackAnim;
    }

    @Override
    public void Update() {
        switch (state) {
            case ALIVE:
                break;
            case BEHURT:
                System.out.println("state now = " + getState());

                System.out.println("come to BEHURT");

                System.out.println("BEHURT");
                state = NOBEHURT;
                startTimeNoBeHurt = System.nanoTime();
                if (getBlood() == 0) {
                    state = DEATH;
                }
                break;
            case FEY:
                break;
            case DEATH:
                System.out.println("Come to Death");
                break;
            case NOBEHURT:
                System.out.println("Come to NOBE HURT");
                if (System.nanoTime() - startTimeNoBeHurt > timeForNoBeHurt) {
                    state = ALIVE;
                    System.out.println("Back to Alive");
                }
                break;
        }
    }

    public void beHurt(int damageEat) {
        setBlood(getBlood() - damageEat);
        System.out.println("before behurt = " + getState());
        state = BEHURT;
        System.out.println("after behurt = " + getState());
        System.out.println("soldier Blood = " + getBlood());
        hurtingCallback();
    }

    public Rectangle getBoundForCollisionWithMap() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth() / 2));
        bound.y = (int) (getPosY() - (getHeight() / 2));
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    public void drawBoundForCollisionWithMap(Graphics g) {
        Rectangle rect = getBoundForCollisionWithMap();
        g.setColor(Color.BLUE);
        g.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }

    public void drawBoundForCollisionWithEnemy(Graphics g) {
        Rectangle rect = getBoundForCollisionWithEnemy();
        g.setColor(Color.RED);
        g.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }

    public boolean isObjectOutOfCameraView() {
        if (getPosX() - getGameWorld().camera.getPosX() > getGameWorld().camera.getWidthView()
                || getPosX() - getGameWorld().camera.getPosX() < -50
                || getPosY() - getGameWorld().camera.getPosY() > getGameWorld().camera.getHeightView()
                || getPosY() - getGameWorld().camera.getPosY() < -50) {
            return true;
        } else {
            return false;
        }
    }

    public abstract Rectangle getBoundForCollisionWithEnemy();

    public abstract void draw(Graphics g);

    public void hurtingCallback() {
    }
}
