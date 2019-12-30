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
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (object != null && object.getState() == ALIVE) {
            setBlood(0);
            object.beHurt(getDamage()); 
            System.out.println("Bullet set behurt for enemy");
        }
    }

    public abstract void draw(Graphics g);

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        return rect;
    }

}
