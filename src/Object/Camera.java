package Object;

import java.awt.Color;
import java.awt.Graphics;

public class Camera extends GameObject {

    private float widthView;
    private float heightView;
    private boolean isLocked = false;

    public Camera(float posX, float posY, float widthView, float heightView, GameWorld gameWorld) {
        super(posX, posY, gameWorld);
        this.widthView = widthView;
        this.heightView = heightView;
    }
    
    public boolean isIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock() {
        isLocked = false;
    }

    public void draw(Graphics g) {
//        g.setColor(Color.red);
//        g.drawRect((int) getPosX(), (int) getPosY(), (int) getWidthView(), (int) getHeightView());
    }

    public void Update() {

//        if (!isLocked) {
//
//            if (getGameWorld().soldier.getPosX() - getPosX() > 500) {
//                setPosX(getGameWorld().soldier.getPosX() - 500);
//                System.out.println("cameraX = " + getPosX());
//                System.out.println("soldierX = " + getGameWorld().soldier.getPosX()); 
//            }
//            if (getGameWorld().soldier.getPosX() - getPosX() < 200) {
//                setPosX(getGameWorld().soldier.getPosX() - 200);
//            }
//
//            if (getGameWorld().soldier.getPosY() - getPosY() > 625) {
//                setPosY(getGameWorld().soldier.getPosY() - 625); // bottom
//            } else if (getGameWorld().soldier.getPosY() - getPosY() < 200) {
//                setPosY(getGameWorld().soldier.getPosY() - 200);// top 
//            }
//        }
    }

    public float getWidthView() {
        return widthView;
    }

    public void setWidthView(float widthView) {
        this.widthView = widthView;
    }

    public float getHeightView() {
        return heightView;
    }

    public void setHeightView(float heightView) {
        this.heightView = heightView;
    }
}

