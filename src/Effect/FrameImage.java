/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Effect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Admin
 */
public class FrameImage {

    private String name;
    private BufferedImage image;

    public FrameImage(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }

    public FrameImage(FrameImage frameImage) {
        name = frameImage.name;
        image = new BufferedImage(frameImage.getImageWidth(),
                frameImage.getImageHeight(), frameImage.image.getType());
        Graphics g = image.getGraphics();
        g.drawImage(frameImage.image, 0, 0, null);
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2, null);
    }

    public void rotateImage(Graphics2D g2, BufferedImage imgage, int x, int y, double angle, int deltaX, int deltaY) {

        g2.translate(x, y);
        g2.rotate(angle, image.getWidth() - deltaX, image.getHeight() - deltaY);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

    }

    public FrameImage() {
        this.name = null;
        image = null;

    }

    public int getImageWidth() {
        return image.getWidth();
    }

    public int getImageHeight() {
        return image.getHeight();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
