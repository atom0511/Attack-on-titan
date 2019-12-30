/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ParticularObjectManager {

    protected List<ParticularObject> particularObjects;

    private GameWorld gameWorld;

    public ParticularObjectManager(GameWorld gameWorld) {
        particularObjects = Collections.synchronizedList(new LinkedList<ParticularObject>());
        this.gameWorld = gameWorld;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public void addObject(ParticularObject particularObject) {
        synchronized (particularObjects) {
            particularObjects.add(particularObject);
        }
    }

    public void RemoveObject(ParticularObject particularObject) {
        synchronized (particularObjects) {
            for (int i = 0; i < particularObjects.size(); i++) {
                ParticularObject object = particularObjects.get(i);
                if (object == particularObject) {
                    particularObjects.remove(i);
                }
            }
        }
    }

    public ParticularObject getCollisionWithEnemyObject(ParticularObject object) {
        synchronized (particularObjects) {
            for (int i = 0; i < particularObjects.size(); i++) {
                ParticularObject objectInList = particularObjects.get(i);
                if (object.getTeamType() != objectInList.getTeamType()
                        && object.getBoundForCollisionWithEnemy().intersects(objectInList.getBoundForCollisionWithEnemy())) {
                    return objectInList;
                }
            }
        }
        return null;
    }

    public void UpdateObjects() {
        synchronized (particularObjects) {
            for (int i = 0; i < particularObjects.size(); i++) {
                ParticularObject object = particularObjects.get(i);
                if (!object.isObjectOutOfCameraView()) {
                    object.Update();
                }
                if (object.getState() == ParticularObject.DEATH) {
                    particularObjects.remove(i);
                }
            }
        }
    }
    
    public void draw(Graphics g){
        synchronized(particularObjects){
            for(ParticularObject object: particularObjects){
                if(!object.isObjectOutOfCameraView()) object.draw(g);
            }
        }
    }
}
