package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends ParticularObject{

    public float speedX;
    public float speedY;
    private int radius;
    public boolean isShooting = false;

    public Bullet(float posX, float posY, float width, float height, float mass, int damage, int radius, GameWorld gameWorld) {
        super(posX, posY, width, height, mass, damage, gameWorld);
        setDamage(damage);
        this.radius = radius;
    }
    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isIsShooting() {
        return isShooting;
    }

    public void setIsShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }
    
    @Override
    public void Update(){
        
    }
    
    public void draw(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect((int)getPosX(),(int) getPosY(), radius, radius);
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        return rect;
    }

}
