package Object;

import Effect.CacheDataLoader;
import Effect.FrameImage;
import Run.FrameAOT;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameWorld {

    public Plane plane;
    public Soldier soldier;
    PhysicalMap physicalMap;
    public Camera camera;
    BulletManager bulletManager;
    public BackgroundMap backgroundMap;
    ParticularObjectManager particularObjectManager;

    public static final int INIT_GAME = 0;
    public static final int TUTORIAL = 1;
    public static final int GAMEPLAY = 2;
    public static final int GAMEOVER = 3;
    public static final int GAMEWIN = 4;
    public static final int PAUSEGAME = 5;

    public static final int INTROGAME = 0;

    public int openIntroGameY = 0;
    public int state = INIT_GAME;
    public int previousState = state;
    public int tutorialState = INTROGAME;

    public int storyTutorial = 0;
    public String[] texts1 = new String[5];

    public String textTutorial;
    public int currentSize = 1;

    private boolean finalbossTrigger = true;
    ParticularObject boss;

    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");
    public int numberOfLife = 3;
    public AudioClip bgMusic;

    int a = 0;

    public GameWorld() {

        texts1[0] = "  We are heros, and our mission is protecting our Home\n  Earth....";
        texts1[1] = "  There was a Big Plane from University on Earth in 10 years\n"
                + "  and we lived in the scare in that 10 years....";
        texts1[2] = "  Now is the time for us, kill it and get freedom!....";
        texts1[3] = "  You need to use:\n"
                + "     '→↑↓←' or 'WASD' to move\n"
                + "     'SPACE' to high jump\n"
                + "     'LEFT MOUSE' to shoot.\n";
        texts1[4] = "      LET'S GO!....";

        textTutorial = texts1[0];
        camera = new Camera(0, 0, FrameAOT.SCREEN_WIDTH, FrameAOT.SCREEN_HEIGHT, this);
        soldier = new Soldier(400, 600, 50, 50, 0.1f, 10, this);
        soldier.setTeamType(ParticularObject.LEAGUE_TEAM);
        plane = new Plane(1100, 165, 150, 50, 4, 4, 100, this);
        plane.setTeamType(ParticularObject.ENEMY_TEAM);
        physicalMap = new PhysicalMap(0, 0, this);
        backgroundMap = new BackgroundMap(0, 0, this);
        bulletManager = new BulletManager(this);

        particularObjectManager = new ParticularObjectManager(this);
        particularObjectManager.addObject(plane);
        particularObjectManager.addObject(soldier);
        bgMusic = CacheDataLoader.getInstance().getSound("backGround");
    }

    public void switchState(int state) {
        previousState = this.state;
        this.state = state;
    }

    private void TutorialUpdate() {
        if (storyTutorial == 0) {
            if (openIntroGameY < 450) {
                openIntroGameY += 4;
            } else {
                storyTutorial++;
            }

        } else {
            if (currentSize < textTutorial.length()) {
                currentSize++;
            }
        }
    }

    private void drawString(Graphics g, String text, int x, int y) {
        for (String str : text.split("\n")) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString(str, x, y += g.getFontMetrics().getHeight());
        }
    }

    private void TutorialDraw(Graphics g) {

        int yMid = FrameAOT.SCREEN_HEIGHT / 2 - 15;
        int y1 = yMid - FrameAOT.SCREEN_HEIGHT / 2 - openIntroGameY / 2;
        int y2 = yMid + openIntroGameY / 2;

        g.setColor(Color.BLACK);
        g.fillRect(0, y1, FrameAOT.SCREEN_WIDTH, FrameAOT.SCREEN_HEIGHT / 2);
        g.fillRect(0, y2, FrameAOT.SCREEN_WIDTH, FrameAOT.SCREEN_HEIGHT / 2);

        if (storyTutorial >= 1) {
            g.drawImage(avatar.getImage(), 300, 350, null);
            g.setColor(Color.orange);
            g.fillRect(600, 370, 550, 150);
            g.setColor(Color.WHITE);
            String text = textTutorial.substring(0, currentSize - 1);
            drawString(g, text, 600, 370);
        }
    }

    public void draw(Graphics g) {
        if (g != null) {
            switch (state) {
                case INIT_GAME:
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, FrameAOT.SCREEN_WIDTH, FrameAOT.SCREEN_HEIGHT);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                    g.drawString("PRESS ENTER TO START GAME", 650, 350);
                    g.drawImage(CacheDataLoader.getInstance().getFrameImage("bgMenu").getImage(), 730, 100, null);
                    break;
                case PAUSEGAME:
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, FrameAOT.SCREEN_WIDTH, FrameAOT.SCREEN_HEIGHT);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
                    g.drawString("PAUSE GAME", 700, 250);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    g.drawString("PRESS ENTER TO COUNTINUE", 720, 300);
                    break;
                case TUTORIAL:
                    //physicalMap.draw(g);
                    backgroundMap.draw(g);
                    TutorialDraw(g);
                    break;
                case GAMEWIN:
                case GAMEPLAY:
                    boolean flag = true;
                    if (state == GAMEWIN) {
                        flag = false;
                        g.drawImage(CacheDataLoader.getInstance().getFrameImage("winGame").getImage(), 600, 0, null);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                        g.setColor(Color.ORANGE);
                        g.drawString("PRESS ENTER TO PLAY AGAIN", 680, 200);
                    }
                    for (int i = 0; i < numberOfLife; i++) {
                        g.drawImage(CacheDataLoader.getInstance().getFrameImage("heart").getImage(), 20 + i * 40, 18, null);
                    }
                    if (soldier.getPosY() < 400 && soldier.getIsJumping() == true && flag == true) {
                        System.out.println("");
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                        g.setColor(Color.ORANGE);
                        g.drawString("You can not jump high here", 680, 200);
                    }
                    backgroundMap.draw(g);
                    bulletManager.draw(g);
                    particularObjectManager.draw(g);

                    break;
                case GAMEOVER:
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, FrameAOT.SCREEN_WIDTH, FrameAOT.SCREEN_HEIGHT);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
                    g.drawString("GAME OVER!", 700, 350);
                    break;
            }
        }
    }

    public void Update() {
        switch (state) {
            case INIT_GAME:
                break;
            case TUTORIAL:
                TutorialUpdate();
                break;
            case GAMEPLAY:
                particularObjectManager.UpdateObjects();
                bulletManager.UpdateObjects();
                physicalMap.Update();
//                camera.Update();
                if (soldier.getState() == ParticularObject.DEATH) {
                    numberOfLife--;
                    if (numberOfLife >= 0) {
                        soldier.setBlood(10);
                        soldier.setPosY(soldier.getPosY() - 50);
                        soldier.setState(ParticularObject.NOBEHURT);
                        particularObjectManager.addObject(soldier);
                    } else {
                        switchState(GAMEOVER);
                        bgMusic.stop();
                    }
                } else if (plane.getBlood() == 0) {
                    plane.setState(ParticularObject.DEATH);
                    switchState(GAMEWIN);
                    bgMusic.stop();
                }
            case PAUSEGAME:
             
                break;
            case GAMEOVER:

                break;
            case GAMEWIN:

                break;
        }

    }

}
