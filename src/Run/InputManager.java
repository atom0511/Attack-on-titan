package Run;

import Object.GameWorld;
import java.awt.event.KeyEvent;

public class InputManager {

    GameWorld gameWorld;
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
                if (gameWorld.soldier.getIsJumping() == true) {
                    gameWorld.soldier.setSpeedY(-4);
                }
                break;
            case KeyEvent.VK_DOWN:

                break;
            case KeyEvent.VK_LEFT:
                left = true;
                gameWorld.soldier.setDirection(gameWorld.soldier.LEFT_DIR);
                gameWorld.soldier.run();
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                gameWorld.soldier.setDirection(gameWorld.soldier.RIGHT_DIR);
                gameWorld.soldier.run();
                break;
            case KeyEvent.VK_ENTER:
                   if (gameWorld.state == gameWorld.INIT_GAME) {
                    if (gameWorld.previousState == gameWorld.GAMEPLAY) {
                        gameWorld.switchState(gameWorld.GAMEPLAY);
                    } else {
                        gameWorld.switchState(gameWorld.TUTORIAL);
                    }

                    gameWorld.bgMusic.loop();
                    gameWorld.bgMusic.play();
                       System.out.println("playms");
                }
                if (gameWorld.state == gameWorld.TUTORIAL && gameWorld.storyTutorial >= 1) {
                    if (gameWorld.storyTutorial <= 3) {
                        gameWorld.storyTutorial++;
                        gameWorld.currentSize = 1;
                        gameWorld.textTutorial = gameWorld.texts1[gameWorld.storyTutorial - 1];
                    } else {
                        gameWorld.switchState(gameWorld.GAMEPLAY);
                    }
                }
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
                gameWorld.soldier.stopRun();
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                gameWorld.soldier.stopRun();
                break;
            case KeyEvent.VK_SPACE://higher jump
                if (gameWorld.soldier.getIsJumping() == true) {
                    gameWorld.soldier.setSpeedY(-10);
                }
                break;
        }
    }

    public void mousePressed(float mouseX, float mouseY) {
        gameWorld.soldier.attack(mouseX, mouseY);

    }

    public void mouseClicked(float mouseX, float mouseY) {
        gameWorld.soldier.attack(mouseX, mouseY);

    }

    public void mouseRealeased(float mouseX, float mouseY) {
        gameWorld.soldier.attack(mouseX, mouseY);

    }

}
