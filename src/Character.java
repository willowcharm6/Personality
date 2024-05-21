import java.awt.*;
import java.awt.image.BufferedImage;

public class Character extends Sprite {

    public Character(Point location, BufferedImage[] walkUp, BufferedImage[] walkDown,
                     BufferedImage[] walkLeft, BufferedImage[] walkRight) {
        super(location);
        this.walkUp = walkUp;
        this.walkDown = walkDown;
        this.walkLeft = walkLeft;
        this.walkRight = walkRight;
        setImages();
    }


    @Override
    protected void setImages() {
    }

    public void followPlayer(Sprite player, int targetDistance) {
        Point playerPosition = player.getLocation();
        Point charPosition = getLocation();
        double distance = playerPosition.distance(charPosition);

        if (distance > targetDistance) {
            // Move character only if it is further than the desired distance from the player
            double angle = Math.atan2(playerPosition.getY() - charPosition.getY(), playerPosition.getX() - charPosition.getX());
            int dx = (int) (Math.cos(angle) * 2); // Adjust the speed of char movement
            int dy = (int) (Math.sin(angle) * 2); // Adjust the speed of char movement
            move(dx, dy);
        } else {
            // Stop character movement if within the desired distance
            move(0, 0);
        }
    }
}