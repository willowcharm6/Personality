import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Sprite{
    private int dx;
    private int dy;
    private Random random;
    private long lastDirectionChangeTime;
    private static final long DIRECTION_CHANGE_INTERVAL = 2000; // 2 seconds
    private BufferedImage image;
    private int attackDistance;

    public Enemy(Point location, BufferedImage image) {
        super(location, image);
        attackDistance = 300;

        random = new Random();
        changeDirection();
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
        walkUp = new BufferedImage[]{Resources.enemyBack, Resources.enemyBWalk1, Resources.enemyBack, Resources.enemyBWalk2};
        walkDown = new BufferedImage[]{Resources.enemyFront, Resources.enemyFWalk1, Resources.enemyFront, Resources.enemyFWalk2};
        walkLeft = new BufferedImage[]{Resources.enemyLeft, Resources.enemyLWalk, Resources.enemyLeft, Resources.enemyLWalk};
        walkRight = new BufferedImage[]{Resources.enemyRight, Resources.enemyRWalk, Resources.enemyRight, Resources.enemyRWalk};
    }


    public void update(){
        move(dx, dy);
        // Change direction at intervals
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDirectionChangeTime > DIRECTION_CHANGE_INTERVAL) {
            changeDirection();
            lastDirectionChangeTime = currentTime;
        }
    }



    private void changeDirection() {
        dx = (random.nextInt(3) - 1); // -1, 0, or 1
        dy = (random.nextInt(3) - 1); // -1, 0, or 1
    }
}
