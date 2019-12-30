
package Object;

import java.awt.Color;
import java.awt.Graphics;

public class SoldierBullet extends Bullet {

    public SoldierBullet(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 10, 10, 0.1f, 1, gameWorld);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) getPosX() , (int) getPosY(), (int) getHeight(), (int) getWidth());
    }
    
}
