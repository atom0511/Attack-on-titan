package Run;

import Effect.CacheDataLoader;
import Effect.FrameImage;
import Object.GameWorld;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
;
import javax.swing.JPanel;
import javax.swing.JPanel;



public class PanelAOT extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener, ActionListener {

    public static boolean isRunning;
    public static Thread thread;
    public static float num;
    GameWorld gameWorld;
    InputManager inputManager;
    private FrameImage frameBG, tree, bush1, signArrow, stone, skeleton, grass1, grass2, cactus1, cactus2, cactus3, crate, name;

    public PanelAOT() {
        gameWorld = new GameWorld();
        inputManager = new InputManager(gameWorld);

        frameBG = CacheDataLoader.getInstance().getFrameImage("backGround");
        tree = CacheDataLoader.getInstance().getFrameImage("tree");
        bush1 = CacheDataLoader.getInstance().getFrameImage("bush1");
        signArrow = CacheDataLoader.getInstance().getFrameImage("signArrow");
        stone = CacheDataLoader.getInstance().getFrameImage("stone");
        skeleton = CacheDataLoader.getInstance().getFrameImage("skeleton");
        grass1 = CacheDataLoader.getInstance().getFrameImage("grass1");
        grass2 = CacheDataLoader.getInstance().getFrameImage("grass2");
        cactus1 = CacheDataLoader.getInstance().getFrameImage("cactus1");
        cactus2 = CacheDataLoader.getInstance().getFrameImage("cactus2");
        cactus3 = CacheDataLoader.getInstance().getFrameImage("cactus3");
        crate = CacheDataLoader.getInstance().getFrameImage("crate");
        name = CacheDataLoader.getInstance().getFrameImage("name");
    }

    public void paint(Graphics g) {
        g.drawImage(frameBG.getImage(), 0, 0, null);
        tree.draw(g, 880, 520);
        bush1.draw(g, 840, 605);
        signArrow.draw(g, 370, 605);
        stone.draw(g, 500, 615);
        skeleton.draw(g, 430, 630);
        grass2.draw(g, 1170, 525);
        cactus1.draw(g, 1450, 610);
        cactus2.draw(g, 200, 530);
        cactus3.draw(g, 1600, 450);
        crate.draw(g, 1000, 300);
        grass1.draw(g, 50, 330);
        grass1.draw(g, 80, 330);
        name.draw(g, 355, 593);
        gameWorld.draw(g);
    }

    @Override
    public void run() {
        long FPS = 80;
        long period = 1000 * 1000000 / FPS;
        long beginTime;
        long sleepTime;
        long deltaTime;

        beginTime = System.nanoTime();
        while (isRunning) {

            Update();
            repaint();

            if (inputManager.left == true && inputManager.up == true && gameWorld.soldier.getIsJumping() == true) {
                gameWorld.soldier.setSpeedX(-3);
                gameWorld.soldier.setSpeedY(-4);
            }

            if (inputManager.right == true && inputManager.up == true && gameWorld.soldier.getIsJumping() == true) {
                gameWorld.soldier.setSpeedX(3);
                gameWorld.soldier.setSpeedY(-4);
            }

            deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;

            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime / 1000000);
                } else {
                    Thread.sleep(period / 2000000);
                }
            } catch (InterruptedException ex) {
            }

            beginTime = System.nanoTime();
        }
    }

    public void Update() {
        gameWorld.Update();
        int time = 10000000;
        if (System.nanoTime() % 10000 == 0 ) {
            time = 1000000;
        }
        if (System.nanoTime() - gameWorld.plane.startTimeToShoot > 100 * time) {
            if (gameWorld.plane.getPosX() > gameWorld.soldier.getPosX() && gameWorld.plane.getDirection() == 1) {
                gameWorld.plane.attack(gameWorld.soldier.getPosX() + 35, gameWorld.soldier.getPosY() + 35);
            } else if (gameWorld.plane.getPosX() <= gameWorld.soldier.getPosX() && gameWorld.plane.getDirection() == 1) {
                gameWorld.plane.attack(gameWorld.soldier.getPosX() + 70, gameWorld.soldier.getPosY() + 35);
            } else if (gameWorld.plane.getPosX() <= gameWorld.soldier.getPosX() && gameWorld.plane.getDirection() == 0) {
                gameWorld.plane.attack(gameWorld.soldier.getPosX() - 35, gameWorld.soldier.getPosY() + 35);
            } else if (gameWorld.plane.getPosX() > gameWorld.soldier.getPosX() && gameWorld.plane.getDirection() == 0) {
                gameWorld.plane.attack(gameWorld.soldier.getPosX() - 70, gameWorld.soldier.getPosY() + 35);
            }
            gameWorld.plane.startTimeToShoot = System.nanoTime();
        }

    }

    public void startGame() {
        if (thread == null) {
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        inputManager.keyPressed(ke.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        inputManager.keyReleased(ke.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {
        inputManager.mouseRealeased(me.getX(), me.getY());
    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseMoved(MouseEvent me) {
        int x = (int) gameWorld.soldier.getPosX() - 7;
        int y = (int) gameWorld.soldier.getPosY() - 25;
        gameWorld.soldier.angle = gameWorld.soldier.getAngle(me.getX(), me.getY(), x, y);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
