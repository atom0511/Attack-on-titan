package Run;

import Object.GameWorld;
import Object.ParticularObject;
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
            case KeyEvent.VK_W:
                up = true;
                if (gameWorld.soldier.getIsJumping() == true) {
                    gameWorld.soldier.setSpeedY(-4);
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:

                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                left = true;
                gameWorld.soldier.setDirection(gameWorld.soldier.LEFT_DIR);
                gameWorld.soldier.run();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
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
                }
                if (gameWorld.state == gameWorld.TUTORIAL && gameWorld.storyTutorial >= 1) {
                    if (gameWorld.storyTutorial <= 4) {
                        gameWorld.storyTutorial++;
                        gameWorld.currentSize = 1;
                        gameWorld.textTutorial = gameWorld.texts1[gameWorld.storyTutorial - 1];
                    } else {
                        gameWorld.switchState(gameWorld.GAMEPLAY);
                    }

                    break;
                }
             case KeyEvent.VK_P:
                 if(gameWorld.state == gameWorld.GAMEPLAY){
                     gameWorld.state = gameWorld.PAUSEGAME;
                 }
//                 if(gameWorld.state == gameWorld.PAUSEGAME){
//                     gameWorld.state = gameWorld.GAMEPLAY;
//                     System.out.println("bbbbbbbbbbbbbbbb");
//                 }
                 break;
        }
        

    }

    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                up = false;
                if (gameWorld.soldier.getIsJumping() == true) {
                    gameWorld.soldier.setSpeedY(-4);
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                left = false;
                gameWorld.soldier.stopRun();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                right = false;
                gameWorld.soldier.stopRun();
                break;
            case KeyEvent.VK_SPACE://higher jump
                if (gameWorld.soldier.getIsJumping() == true && gameWorld.soldier.getPosY() > 400) {
                    gameWorld.soldier.setSpeedY(-10);
                }
                break;
            case KeyEvent.VK_ENTER:
                if (gameWorld.state == gameWorld.GAMEOVER || gameWorld.state == gameWorld.GAMEWIN) {
                    gameWorld.numberOfLife = 2;
                    gameWorld.soldier.setBlood(1);
                    gameWorld.plane.setState(ParticularObject.ALIVE);
                    gameWorld.plane.setBlood(1);
                    gameWorld.switchState(gameWorld.INIT_GAME);
                } else if (gameWorld.state == gameWorld.PAUSEGAME) {
                    gameWorld.state = gameWorld.GAMEPLAY;
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
