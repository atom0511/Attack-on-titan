package Object;

import java.awt.Graphics;

public class GameWorld {

    Plane plane;
    public Soldier soldier;
    PhysicalMap physicalMap;
   
    

    public GameWorld() {
        
        physicalMap = new PhysicalMap(0, 0);
        soldier = new Soldier(300, 300, 50, 50, 0.1f);
        plane = new Plane(1100, 165, 100, 50, 2, 2);
        
        
    }

    public void Update() {
        soldier.Update();
        plane.Update();
        
    }

    public void draw(Graphics g) {
        physicalMap.draw(g);
        soldier.draw(g);
        plane.draw(g);
   
        
    }

}
