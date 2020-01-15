package Object;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Bullet extends ParticularObject {

    private int speedX = 0;
    private int speedY = -5;

    public Bullet(float posX, float posY, float width, float height, float mass, int damage, GameWorld gameWorld) {
        super(posX, posY, width, height, mass, 1, gameWorld);
        setDamage(damage);
    }

    @Override
    public void Update() {
        super.Update();
        if(getPosX() >= 1750 || getPosX() <= 10 || getPosY() <= 10 || getPosY() >= 789){
            setState(DEATH);
        }
        Rectangle check = getBoundForCollisionWithMap();
        check.y += getSpeedY();
        Rectangle check2 = getGameWorld().physicalMap.haveCollisionWithLand(check);

        if (check2 != null) {
            setState(DEATH);
        } else {
            setPosX(getPosX() + getSpeedX());
            setPosY(getPosY() + getSpeedY());
        }

        Rectangle check3 = getBoundForCollisionWithMap();
        check3.x += getSpeedX();
        Rectangle check4 = getGameWorld().physicalMap.haveCollisionWithLeftWall(check3);

        if (check4 != null) {
            setState(DEATH);
        }

        Rectangle check5 = getBoundForCollisionWithMap();
        check5.x += getSpeedX();
        Rectangle check6 = getGameWorld().physicalMap.haveCollisionWithRightWall(check5);

        if (check6 != null) {
            setState(DEATH);
        }
        // chung setPosX(getPosX() + getSpeedX()); với haveCollisionWithLeftWall
        Rectangle check7 = getBoundForCollisionWithMap();
        check7.y += getSpeedY();
        Rectangle check8 = getGameWorld().physicalMap.haveCollisionWithTop(check7);

        if (check8 != null) {
            setState(DEATH);
        }
    }

    @Override
    public abstract void draw(Graphics g);

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        return rect;
    }

}
