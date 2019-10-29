package Object;

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
    
    @Override
    public void Update() {
        switch(state){
            case ALIVE: break;
            case BEHURT: break;
            case FEY: break;
            case DEATH: break;
            case NOBEHURT: break;
        }
    }

    public Rectangle getBoundForCollisionWithMap() {
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth() / 2));
        bound.y = (int) (getPosY() - (getHeight() / 2));
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }
    
    public void beHurt(int damageEat){
        setBlood(getBlood() - damageEat);
        state = BEHURT;
        //hurting call back
    }
    
//
    public void drawBoundForCollisionWithMap(Graphics g) {
        Rectangle rect = getBoundForCollisionWithMap();
        g.setColor(Color.BLUE);
        g.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }
//
    public void drawBoundForCollisionWithEnemy(Graphics g) {
        Rectangle rect = getBoundForCollisionWithEnemy();
        g.setColor(Color.RED);
        g.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }
    
    public abstract Rectangle getBoundForCollisionWithEnemy();
    
    public abstract void draw(Graphics g);
    
//    public void hurtingCallback(){};
}
