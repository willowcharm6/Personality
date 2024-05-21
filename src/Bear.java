import java.awt.*;
import java.awt.image.BufferedImage;

public class Bear extends Enemy{
    public Bear(Point location) {
        super(location);

        walkUp = new BufferedImage[]{Resources.bearBack, Resources.bearBWalk1};
        walkDown = new BufferedImage[]{Resources.bearFront, Resources.bearFWalk1};
        walkLeft = new BufferedImage[]{Resources.bearLeft, Resources.bearLWalk};
        walkRight = new BufferedImage[]{Resources.bearRight, Resources.bearRWalk};
    }

    @Override
    protected int getAnimationFrameCount() {
        return 2;
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
