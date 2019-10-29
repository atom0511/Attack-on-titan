package Run;

import Object.GameWorld;
import java.awt.event.KeyEvent;

public class InputManager {

    GameWorld gameWorld;
    public boolean mouse;
    public boolean left = false;
    public boolean up = false;
    public boolean right = false;

    public InputManager(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public void keyTyped(KeyEvent ke) {

    }

    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:

                break;
            case KeyEvent.VK_LEFT:
                left = true;
                gameWorld.soldier.setSpeedX(-3);
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                gameWorld.soldier.setSpeedX(3);
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }

    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                up = false;
                if (gameWorld.soldier.getIsJumping() == true) {
                    gameWorld.soldier.setSpeedY(-4);
                }
                break;
            case KeyEvent.VK_DOWN:

                break;
            case KeyEvent.VK_LEFT:
                left = false;
                gameWorld.soldier.setSpeedX(0);
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                gameWorld.soldier.setSpeedX(0);
                break;
            case KeyEvent.VK_SPACE://higher jump
                if (gameWorld.soldier.getIsJumping() == true) {
                    gameWorld.soldier.setSpeedY(-10);
                }
                break;
        }
    }

    public void mousePressed() {
        mouse = true;

    }

    public void mouseClicked() {
        mouse = false;
    }

    public void mouseRealeased() {
        mouse = false;
    }

}
