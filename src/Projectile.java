import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile{

    private Point location;
    private int speed;
    private BufferedImage image;
    private int maxFrames, frameCount;
    private double angle;

    public Projectile(Point location, int speed, BufferedImage image, Point mouse) {
        this.location = location;
        this.speed = speed;
        this.image = image;
        this.angle = Math.atan2(mouse.getY() - location.getY(), mouse.getX() - location.getX());
    }

    public void move(int dx, int dy) {
        int imageSize = 32;
        double newX = location.getX() + dx;
        double newY = location.getY() + dy;
        location = new Point((int)newX, (int)newY);
    }

    public void followMouse(int speed) {
//        double mouseX = mouse.getX();
//        double mouseY = mouse.getY();
//        System.out.println(mouseX + ", " + mouseY);
//        double distance = location.distance(mouse);
//
//        if (distance > 0) {
            // Move character only if it is further than the desired distance from the player
//            double angle = Math.atan2(mouse.getY() - location.getY(), mouse.getX() - location.getX());

            int dx = (int) (Math.cos(angle) * speed); // Adjust the speed of char movement
            int dy = (int) (Math.sin(angle) * speed); // Adjust the speed of char movement
            move(dx, dy);
//        } else {
//            // Stop character movement if within the desired distance
//            move(0, 0);
//        }
    }

    public void draw2(Graphics2D g2, BufferedImage image){
        g2.drawImage(image, location.x, location.y, null);
    }
}
