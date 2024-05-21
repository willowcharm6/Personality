import java.awt.*;
import java.awt.image.BufferedImage;

public class Bear extends Enemy{
    public Bear(Point location) {
        super(location);

        walkUp = new BufferedImage[]{Resources.enemyBack, Resources.enemyBWalk1, Resources.enemyBWalk2};
        walkDown = new BufferedImage[]{Resources.enemyFront, Resources.enemyFWalk1, Resources.enemyFWalk2};
        walkLeft = new BufferedImage[]{Resources.enemyLeft, Resources.enemyLWalk, Resources.enemyLeft};
        walkRight = new BufferedImage[]{Resources.enemyRight, Resources.enemyRWalk, Resources.enemyRight};
    }

//    @Override
//    private void updateAnimationFrame() {
//        long now = System.currentTimeMillis();
//        if (now - lastFrameTime >= 200) {  // Adjust the frame duration as needed
//            frameIndex = (frameIndex + 1) % 3;
//            lastFrameTime = now;
//        }
//    }
}
