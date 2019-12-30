/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author Admin
 */
public class BulletManager extends ParticularObjectManager {

    public BulletManager(GameWorld gameWorld) {
        super(gameWorld);
    }

    @Override
    public void UpdateObjects() {
        super.UpdateObjects();
        synchronized (particularObjects) {
            for (int i = 0; i < particularObjects.size(); i++) {
                ParticularObject object = particularObjects.get(i);
                if (!object.isObjectOutOfCameraView()) {
                    object.Update();
                }
                if (object.getState() == ParticularObject.DEATH || object.isObjectOutOfCameraView()) {
                    particularObjects.remove(i);
                }
            }
        }
    }
}
