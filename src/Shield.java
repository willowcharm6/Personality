import java.awt.*;
import java.awt.image.BufferedImage;


public class Shield{
    private Point location;
    private int speed;
    private BufferedImage image;
    private int maxFrames, frameCount;
    private double angle;
    public Shield(Point location, int speed, BufferedImage image) {
        this.location = location;
        this.speed = speed;
        this.image = image;
    }

    public boolean intersects(Sprite other){
        Rectangle hitBox = new Rectangle(location.x, location.y, image.getWidth(), image.getHeight());
        Rectangle otherHitBox = new Rectangle(other.getX(), other.getY(), other.getImageWidth(), other.getImageHeight());
        return hitBox.intersects(otherHitBox);
    }

}
