import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;


public class Bear extends Enemy{

    private int attackDistance;

    public Bear(Point location, BufferedImage image) {
        super(location, image);
        attackDistance = 100;
    }

    public void followPlayer(Sprite player) {
        Point playerPosition = player.getLocation();
        Point enemyPosition = getLocation();
        double distance = playerPosition.distance(enemyPosition);

        if (distance < attackDistance) {
            // Move character only if it is further than the desired distance from the player
            double angle = Math.atan2(playerPosition.getY() - enemyPosition.getY(), playerPosition.getX() - enemyPosition.getX());
            int dx = (int) (Math.cos(angle) * 2); // Adjust the speed of char movement
            int dy = (int) (Math.sin(angle) * 2); // Adjust the speed of char movement
            move(dx, dy);
        }
    }

    public void attackPlayer(Sprite player) {
        Point playerPosition = player.getLocation();
        Point enemyPosition = getLocation();
        double distance = playerPosition.distance(enemyPosition);

        // Move character only if it is further than the desired distance from the player
        double angle = Math.atan2(playerPosition.getY() - enemyPosition.getY(), playerPosition.getX() - enemyPosition.getX());
        int dx = (int) (Math.cos(angle) * 2); // Adjust the speed of char movement
        int dy = (int) (Math.sin(angle) * 2); // Adjust the speed of char movement
        move(dx, dy);
    }

    @Override
    protected void setImages() {
        // Initialize the animation frames using images from Resources
        walkUp = new BufferedImage[]{Resources.bearBack, Resources.bearBWalk1, Resources.bearBack, Resources.bearBWalk1};
        walkDown = new BufferedImage[]{Resources.bearFront, Resources.bearFWalk1, Resources.bearFront, Resources.bearFWalk1};
        walkLeft = new BufferedImage[]{Resources.bearLeft, Resources.bearLWalk, Resources.bearLeft, Resources.bearLWalk};
        walkRight = new BufferedImage[]{Resources.bearRight, Resources.bearRWalk, Resources.bearRight, Resources.bearRWalk};
    }

}
