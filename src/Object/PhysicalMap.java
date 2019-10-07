package Object;

import Effect.CacheDataLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PhysicalMap {

    public int tileSize;
    private float posX, posY;

    public PhysicalMap(float x, float y) {
        this.tileSize = 50;
        this.posX = x;
        this.posY = y;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < CacheDataLoader.getInstance().physMap.length; i++) {
            for (int j = 0; j < CacheDataLoader.getInstance().physMap[0].length; j++) {
                g.setColor(Color.GRAY);
                if (CacheDataLoader.getInstance().physMap[i][j] != 0) {
                    g.fillRect((int) posX + j * tileSize,
                            (int) posY + i * tileSize, tileSize, tileSize);
                }
            }
        }
    }

    public Rectangle haveCollisionWithLand(Rectangle rec) {
        int posX1 = rec.x / tileSize; //lấy ra giá trị của x trong mảng Map
        posX1 -= 2; // tăng vùng quét
        int posX2 = (rec.x + rec.width) / tileSize;
        posX2 += 2;
        int posY1 = (rec.y + rec.height) / tileSize;

        if (posX1 < 0) {
            posX1 = 0;
        }
        if (posX2 > CacheDataLoader.getInstance().physMap[0].length - 1) {
            posX2 = CacheDataLoader.getInstance().physMap[0].length - 1;
        }
        for (int y = posY1; y < CacheDataLoader.getInstance().physMap.length - 1; y++) {
            for (int x = posX1; x < posX2; x++) {
                if (CacheDataLoader.getInstance().physMap[y][x] == 1) {
                    Rectangle r1 = new Rectangle((int) posX + x * tileSize,
                            (int) posY + y * tileSize, tileSize, tileSize);
                    if (rec.intersects(r1)) {
                        return r1;
                    }
                }
            }
        }
        return null;
    }

    public Rectangle haveCollisionWithRightWall(Rectangle rect) {

        int posY1 = rect.y / tileSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / tileSize;
        posY2 += 2;

        int posX1 = (rect.x + rect.width) / tileSize;
        int posX2 = posX1 + 3;
        if (posX2 >= CacheDataLoader.getInstance().physMap[0].length) {
            posX2 = CacheDataLoader.getInstance().physMap[0].length - 1;
        }

        if (posY1 < 0) {
            posY1 = 0;
        }
        if (posY2 >= CacheDataLoader.getInstance().physMap.length) {
            posY2 = CacheDataLoader.getInstance().physMap.length - 1;
        }

        for (int x = posX1; x <= posX2; x++) {
            for (int y = posY1; y <= posY2; y++) {
                if (CacheDataLoader.getInstance().physMap[y][x] == 1) {
                    Rectangle r = new Rectangle((int) posX + x * tileSize, (int) posY + y * tileSize, tileSize, tileSize);
                    
                    if (rect.intersects(r)) {
                       
                        return r;
                    }
                }
            }
        }
        return null;

    }

    public Rectangle haveCollisionWithLeftWall(Rectangle rect) {

        int posY1 = rect.y / tileSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height) / tileSize;
        posY2 += 2;

        int posX1 = rect.x / tileSize;
        int posX2 = posX1 - 3;
        if (posX2 < 0) {
            posX2 = 0;
        }

        if (posY1 < 0) {
            posY1 = 0;
        }
        if (posY2 >= CacheDataLoader.getInstance().physMap.length) {
            posY2 = CacheDataLoader.getInstance().physMap.length - 1;
        }

        for (int x = posX2; x <= posX1; x++) {
            for (int y = posY1; y <= posY2; y++) {
                if (CacheDataLoader.getInstance().physMap[y][x] == 1) {
                    Rectangle r = new Rectangle((int) posX + x * tileSize, (int) posY + y * tileSize, tileSize, tileSize);
                    if ( rect.intersects(r)) {
                        System.out.println("11111");
                        return r;
                    }
                }
            }
        }
        return null;
    }
}
