package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Soldier{

    private float posX;
    private float posY;

    private float height;
    private float width;
    private float mass;

    private float speedX;
    private float speedY;

    public boolean checkJump;

    PhysicalMap physicalMap = new PhysicalMap(0, 0);

    public Soldier(float posX, float posY, float height, float width, float mass) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.mass = mass;

    }

    public void Update() {
        Rectangle check = rectangle();
        check.y += getSpeedY();
        Rectangle check2 = physicalMap.haveCollisionWithLand(check);

        if (check2 != null) {
            checkJump = true;
            setPosY(check2.y - getHeight() / 2);
        } else {
            checkJump = false;
            setPosY(getPosY() + getSpeedY());
            setSpeedY(getSpeedY() + getMass());
        }

        Rectangle check3 = rectangle();
        check3.x += getSpeedX();
        Rectangle check4 = physicalMap.haveCollisionWithLeftWall(check3);

        if (check4 != null) {
            setPosX(check4.x + 3*getWidth()/2);
        } else {
            setPosX(getPosX() + getSpeedX());
        }

        Rectangle check5 = rectangle();
        check5.x += getSpeedX();
        Rectangle check6 = physicalMap.haveCollisionWithRightWall(check5);

        if (check6 != null) {
            setPosX(check6.x - getWidth() / 2);
        }

    }

    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int) (posX - getWidth() / 2), (int) (posY - getHeight() / 2), (int) height, (int) width);
    }

    public Rectangle rectangle() {
        Rectangle rec = new Rectangle();
        rec.x = (int) (posX - getWidth() / 2);
        rec.y = (int) (posY - getHeight() / 2);
        rec.height = (int) getHeight();
        rec.width = (int) getWidth();
        return rec;
    }

    public float getPosX() {
        return posX;
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

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
}
