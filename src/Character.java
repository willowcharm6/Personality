import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Character extends Sprite {
    private String type = "";
    private BufferedImage fullbody;
    private BufferedImage image, attack;
    private Point location;

    public Character(Point location, BufferedImage[] walkUp, BufferedImage[] walkDown,
                     BufferedImage[] walkLeft, BufferedImage[] walkRight, String type,
                     BufferedImage fullbody, BufferedImage image, BufferedImage attack) {
        super(location, image);
        this.walkUp = walkUp;
        this.walkDown = walkDown;
        this.walkLeft = walkLeft;
        this.walkRight = walkRight;
        this.type = type;
        this.fullbody = fullbody;
        this.attack = attack;
    }

    @Override
    protected void setImages() {
    }

    public String getType() {
        return type;
    }

    public BufferedImage getAttack() {
        return attack;
    }

    public BufferedImage getFullbody() {
        return fullbody;
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
