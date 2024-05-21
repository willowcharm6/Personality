import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Sprite{
    private int dx;
    private int dy;
    private Random random;
    private long lastDirectionChangeTime;
    private static final long DIRECTION_CHANGE_INTERVAL = 2000; // 2 seconds

    public Enemy(Point location) {
        super(location);

        walkUp = new BufferedImage[]{Resources.enemyBack, Resources.enemyBWalk1, Resources.enemyBWalk2};
        walkDown = new BufferedImage[]{Resources.enemyFront, Resources.enemyFWalk1, Resources.enemyFWalk2};
        walkLeft = new BufferedImage[]{Resources.enemyLeft, Resources.enemyLWalk, Resources.enemyLeft};
        walkRight = new BufferedImage[]{Resources.enemyRight, Resources.enemyRWalk, Resources.enemyRight};

        random = new Random();
        changeDirection();
    }

    @Override
    protected int getAnimationFrameCount() {
        return 3;
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
