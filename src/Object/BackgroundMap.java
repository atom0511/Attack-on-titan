/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import Effect.CacheDataLoader;
import Run.FrameAOT;
import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author phamn
 */
public class BackgroundMap extends GameObject {

    public int[][] map;
    private int tileSize;

    public BackgroundMap(float x, float y, GameWorld gameWorld) {
        super(x, y, gameWorld);
        map = CacheDataLoader.getInstance().getBackgroundMap();
        tileSize = 50;
    }

    @Override
    public void Update() {
    }

    public void draw(Graphics g) {

//        Camera camera = getGameWorld().camera;

        g.setColor(Color.RED);
        for (int i = 0; i < CacheDataLoader.getInstance().getBackgroundMap().length; i++) {
            for (int j = 0; j < CacheDataLoader.getInstance().getBackgroundMap()[0].length; j++) {
//                if (map[i][j] != 0 && j * tileSize - camera.getPosX() > -30 && j * tileSize - camera.getPosX() < FrameAOT.SCREEN_WIDTH
//                        && i * tileSize - camera.getPosY() > -30 && i * tileSize - camera.getPosY() < FrameAOT.SCREEN_HEIGHT) {
//                    g2.drawImage(CacheDataLoader.getInstance().getFrameImage("tiled" + map[i][j]).getImage(), (int) getPosX() + j * tileSize - (int) camera.getPosX(),
//                            (int) getPosY() + i * tileSize - (int) camera.getPosY(), null);
//                }
                if (map[i][j] != 0 && j * tileSize > -50 && j * tileSize < FrameAOT.SCREEN_WIDTH
                        && i * tileSize > -50 && i * tileSize < FrameAOT.SCREEN_HEIGHT) {
                    g.drawImage(CacheDataLoader.getInstance().getFrameImage("bg" + map[i][j]).getImage(), (int) getPosX() + j * tileSize,
                            (int) getPosY() + i * tileSize, null);
                }
            }
        }

    }

}
