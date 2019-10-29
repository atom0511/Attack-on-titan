package Run;


import Object.GameWorld;
import Object.PhysicalMap;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class PanelAOT extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    public static boolean isRunning;
    public static Thread thread;
    public static float num;
    GameWorld gameWorld;
    InputManager inputManager;
    private float count = 2;

    public PanelAOT() {
        gameWorld = new GameWorld();
        inputManager = new InputManager(gameWorld);
       
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1200, 800);
        gameWorld.draw(g);
        
        g.setColor(Color.RED);
        g.drawLine((int) gameWorld.camera.getPosX(), (int) gameWorld.soldier.getPosY(),
                (int) gameWorld.soldier.getPosX(), (int) gameWorld.soldier.getPosY());
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

//        inputManager.mouseClicked();
//          ArrayList 
//        bullet.setPosX((int) gameWorld.soldier.getPosX() + (int) gameWorld.soldier.getWidth() / 2 - (int) bullet.getRadius());
//        bullet.setPosY((int) gameWorld.soldier.getPosY());
//        a = (gameWorld.soldier.getPosY() - me.getY()) / (gameWorld.soldier.getPosX() + gameWorld.soldier.getWidth() / 2 - me.getX() - 15);
//        b = me.getY() - me.getX() * a;
//        bullet.setSpeedX(2);
//        bullet.setSpeedY(-2);
        inputManager.mousePressed();

    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

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

    }

}
