import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    private BufferedImage image;
    private Point location;

    public Sprite(BufferedImage image, Point location) {
        this.image = image;
        this.location = location;
    }
    public void draw(Graphics2D g2){
        g2.drawImage(image, location.x, location.y, null);
    }
    public void move(int dx, int dy){
        location.translate(dx, dy);
    }

    public int getX(){return location.x;}
    public int getY(){return location.y;}
}
