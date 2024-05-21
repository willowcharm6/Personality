import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Sprite {
//    private BufferedImage[] walkUp, walkDown, walkLeft, walkRight;
//    private int frameIndex = 0;
//    private long lastFrameTime = 0;
//    private String currentDirection = "DOWN";
//    private boolean moving = false;

    public Player(Point location) {
        super(location);
    }
    @Override
    protected void setImages() {
        // Initialize the animation frames using images from Resources
        walkUp = new BufferedImage[]{Resources.playerBStill, Resources.playerBWalk1, Resources.playerBStill, Resources.playerBWalk2};
        walkDown = new BufferedImage[]{Resources.playerFront, Resources.playerFWalk1, Resources.playerFront, Resources.playerFWalk2};
        walkLeft = new BufferedImage[]{Resources.playerLeft, Resources.playerLWalk, Resources.playerLeft, Resources.playerLWalk};
        walkRight = new BufferedImage[]{Resources.playerRight, Resources.playerRWalk, Resources.playerRight, Resources.playerRWalk};
    }

}
