import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Sprite {
    private BufferedImage[] walkUp, walkDown, walkLeft, walkRight;
    private int frameIndex = 0;
    private long lastFrameTime = 0;
    private String currentDirection = "DOWN";
    private boolean moving = false;

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

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(getCurrentFrame(), getX(), getY(), null);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        moving = (dx != 0 || dy != 0);
        if (moving) {
            updateDirection(dx, dy);
            updateAnimationFrame();
        } else {
            frameIndex = 0;  // Reset to standing frame when not moving
        }
    }

    private void updateDirection(int dx, int dy) {
        if (dx < 0) currentDirection = "LEFT";
        else if (dx > 0) currentDirection = "RIGHT";
        else if (dy < 0) currentDirection = "UP";
        else if (dy > 0) currentDirection = "DOWN";
    }


    private void updateAnimationFrame() {
        long now = System.currentTimeMillis();
        if (now - lastFrameTime >= 200) {  // Adjust the frame duration as needed
            frameIndex = (frameIndex + 1) % 4;
            lastFrameTime = now;
        }
    }

    private BufferedImage getCurrentFrame() {
        switch (currentDirection) {
            case "UP": return walkUp[frameIndex];
            case "DOWN": return walkDown[frameIndex];
            case "LEFT": return walkLeft[frameIndex];
            case "RIGHT": return walkRight[frameIndex];
            default: return walkDown[frameIndex];
        }
    }
}
