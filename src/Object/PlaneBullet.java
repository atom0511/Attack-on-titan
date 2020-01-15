/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Admin
 */
public class PlaneBullet extends Bullet {

    public PlaneBullet(float posX, float posY, GameWorld gameWorld) {
        super(posX, posY, 10, 10, 0.1f, 1, gameWorld);

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval((int) getPosX(), (int) getPosY(), (int) getHeight(), (int) getWidth());
    }

    @Override
    public void Update() {
        super.Update();
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWithEnemyObject(this);
        if (object != null && object.getState() == ALIVE) {
            setState(DEATH);
            object.beHurt(getDamage());
//            System.out.println("Bullet set behurt for enemy");
        }
    }
}
