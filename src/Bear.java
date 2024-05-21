import java.awt.*;
import java.awt.image.BufferedImage;

public class Bear extends Enemy{
    public Bear(Point location) {
        super(location);

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
