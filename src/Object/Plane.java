package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Plane extends ParticularObject {

    private boolean direction = true;

    public Plane(float posX, float posY, float width, float height, float speedX, float speedY,
            float mass, int blood, GameWorld gameWorld) {
        
        super(posX, posY, width, height, mass, blood, gameWorld);
        setSpeedX(speedX);
        setSpeedY(speedY);
        
    }

    @Override
    public void Update() {
        super.Update();
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

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) getPosX(), (int) getPosY(), (int) getWidth(), (int) getHeight());
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
