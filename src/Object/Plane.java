package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Plane {

    private float posX;
    private float posY;

    private float height;
    private float width;

    private float speedX;
    private float speedY;

    private boolean direction = true;

    public Plane(float posX, float posY, float width, float height, float speedX, float speedY) {
        Random rd = new Random();
        this.posX = rd.nextInt((int) posX) + getWidth();
        this.posY = rd.nextInt((int) posY) + getHeight();
        this.height = height;
        this.width = width;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void Update() {
        if (getPosX() > 0 && direction == true) {
            setPosX(getPosX() - getSpeedX());
        }
        if (getPosX() < 1100 && direction == false) {
            setPosX(getPosX() + getSpeedX());
        }
        if (getPosX() <= 0) {
            direction = false;
        }
        if (getPosX() >= 1100) {
            direction = true;
        }

    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) posX, (int) posY, (int) width, (int) height);
    }

    public Rectangle rectangle() {
        Rectangle rec = new Rectangle();
        rec.x = (int) (posX - getWidth());
        rec.y = (int) (posY - getHeight());
        rec.height = (int) height;
        rec.width = (int) width;
        return rec;
    }

    public float getPosX() {
        return posX;
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

}
