package Object;

import Run.FrameAOT;
import java.awt.Graphics;

public class GameWorld {

    Plane plane;
    public Soldier soldier;
    PhysicalMap physicalMap;
    public Camera camera;

    public GameWorld() {
        camera = new Camera(0, 0, FrameAOT.SCREEN_WIDTH, FrameAOT.SCREEN_HEIGHT, this);
        soldier = new Soldier(400, 200, 50, 50, 0.1f, 20, this);
        plane = new Plane(1100, 165, 100, 50, 2, 2, 0, 10, this);
        physicalMap = new PhysicalMap(0, 0, this);
       
    }

    public void draw(Graphics g) {
        soldier.draw(g);
        plane.draw(g);
        camera.draw(g);
        physicalMap.draw(g);
    }
    
       public void Update() {
        soldier.Update();
        plane.Update();
        camera.Update();
    }


}
