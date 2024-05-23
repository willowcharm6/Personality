import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile{

    private Point location;
    private int speed;
    private int maxFrames, frameCount;

    public Projectile(Point location) {
        super();
        this.location = location;
    }

    public void draw2(Graphics2D g2, BufferedImage image){
        g2.drawImage(image, location.x, location.y, null);
    }
}
