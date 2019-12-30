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
    public String[] texts1 = new String[4];

    public String textTutorial;
    public int currentSize = 1;

    private boolean finalbossTrigger = true;
    ParticularObject boss;

    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");

    private int numberOfLife = 3;
    public AudioClip bgMusic;

    public GameWorld() {

        texts1[0] = "  We are heros, and our mission is protecting our Home\n  Earth....";
        texts1[1] = "  There was a Big Plane from University on Earth in 10 years\n"
                + "  and we lived in the scare in that 10 years....";
        texts1[2] = "  Now is the time for us, kill it and get freedom!....";
        texts1[3] = "      LET'S GO!.....";

        textTutorial = texts1[0];
        camera = new Camera(0, 0, FrameAOT.SCREEN_WIDTH, FrameAOT.SCREEN_HEIGHT, this);
        soldier = new Soldier(400, 600, 50, 50, 0.1f, 10, this);
        soldier.setTeamType(ParticularObject.LEAGUE_TEAM);
        plane = new Plane(1100, 165, 150, 50, 4, 4, 0, 10, this);
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
            g.setFont(new Font ("TimesRoman", Font.PLAIN, 20));
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
//        physicalMap.draw(g);

        if (g != null) {

            // NOTE: two lines below make the error splash white screen....
            // need to remove this line
            //g2.setColor(Color.WHITE);
            //g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
            //physicalMap.draw(g2);
            switch (state) {
                case INIT_GAME:
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, FrameAOT.SCREEN_WIDTH, FrameAOT.SCREEN_HEIGHT);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font ("TimesRoman", Font.PLAIN, 30));
                    g.drawString("PRESS ENTER TO CONTINUE", 650, 400);
                    break;
                case PAUSEGAME:
                    g.setColor(Color.BLACK);
                    g.fillRect(300, 260, 500, 70);
                    g.setColor(Color.WHITE);
                    g.drawString("PRESS ENTER TO CONTINUE", 400, 300);
                    break;
                case TUTORIAL:
                    backgroundMap.draw(g);
                    TutorialDraw(g);

                    break;
                case GAMEWIN:
                case GAMEPLAY:
                    backgroundMap.draw(g);
                    bulletManager.draw(g);
                    particularObjectManager.draw(g);

                    g.setColor(Color.GRAY);
                    g.fillRect(19, 59, 102, 22);
                    g.setColor(Color.red);
                    g.fillRect(20, 60, soldier.getBlood(), 20);

                    for (int i = 0; i < numberOfLife; i++) {
                        g.drawImage(CacheDataLoader.getInstance().getFrameImage("stoneBlock").getImage(), 20 + i * 40, 18, null);
                    }

                    if (state == GAMEWIN) {
                        g.drawImage(CacheDataLoader.getInstance().getFrameImage("stoneBlock").getImage(), 300, 300, null);
                    }

                    break;
                case GAMEOVER:
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, FrameAOT.SCREEN_WIDTH, FrameAOT.SCREEN_HEIGHT);
                    g.setColor(Color.WHITE);
                    g.drawString("GAME OVER!", 450, 300);
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
                        soldier.setBlood(100);
                        soldier.setPosY(soldier.getPosY() - 50);
                        soldier.setState(ParticularObject.NOBEHURT);
                        particularObjectManager.addObject(soldier);
                    } else {
                        switchState(GAMEOVER);
                        bgMusic.stop();
                    }
                }
            case GAMEOVER:

                break;
            case GAMEWIN:

                break;
        }

    }

}
