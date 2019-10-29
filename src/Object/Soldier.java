package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Soldier extends Human{

    public Soldier(float posX, float posY, float width, float height, float mass, int blood, GameWorld gameWorld) {
        super(posX, posY, width, height, mass, blood, gameWorld);
    }

    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int) (getPosX() - getWidth() / 2), (int) (getPosY() - getHeight() / 2), (int) getHeight(), (int) getWidth());
    }

    @Override
    public void Update(){
        super.Update();
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
       Rectangle rect = getBoundForCollisionWithMap();
       return rect;
    }
}
