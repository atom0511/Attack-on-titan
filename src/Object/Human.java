package Object;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Human extends ParticularObject {

    private boolean isJumping;

    public Human(float posX, float posY, float width, float height, float mass, int blood, GameWorld gameWorld) {
        super(posX, posY, width, height, mass, blood, gameWorld);
    }

    @Override
    public void Update() {
        Rectangle check = getBoundForCollisionWithMap();
        check.y += getSpeedY();
        Rectangle check2 = getGameWorld().physicalMap.haveCollisionWithLand(check);

        if (check2 != null) {
            isJumping = true;
            setPosY(check2.y - getHeight() / 2);
        } else {
            isJumping = false;
            setPosY(getPosY() + getSpeedY());
            setSpeedY(getSpeedY() + getMass());
        }

        Rectangle check3 = getBoundForCollisionWithMap();
        check3.x += getSpeedX();
        Rectangle check4 = getGameWorld().physicalMap.haveCollisionWithLeftWall(check3);

        if (check4 != null) {
            setPosX(check4.x + 3 * getWidth() / 2);
        } else {
            setPosX(getPosX() + getSpeedX());
        }

        Rectangle check5 = getBoundForCollisionWithMap();
        check5.x += getSpeedX();
        Rectangle check6 = getGameWorld().physicalMap.haveCollisionWithRightWall(check5);

        if (check6 != null) {
            setPosX(check6.x - getWidth() / 2);
        } 
        // chung setPosX(getPosX() + getSpeedX()); với haveCollisionWithLeftWall
    }

   

    public boolean getIsJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }



}
