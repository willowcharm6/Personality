import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Sprite {
    protected BufferedImage[] walkUp, walkDown, walkLeft, walkRight;
    private int frameIndex = 0;
    private long lastFrameTime = 0;
    private String currentDirection = "DOWN";
    private boolean moving = false;
    private Point location;

    public Sprite(Point location) {
        this.location = location;
        walkUp = new BufferedImage[]{Resources.playerBStill, Resources.playerBWalk1, Resources.playerBWalk2};
        walkDown = new BufferedImage[]{Resources.playerFront, Resources.playerFWalk1, Resources.playerFWalk2};
        walkLeft = new BufferedImage[]{Resources.playerLeft, Resources.playerLWalk, Resources.playerLeft};
        walkRight = new BufferedImage[]{Resources.playerRight, Resources.playerRWalk, Resources.playerRight};
    }
    public void draw(Graphics2D g2){
        g2.drawImage(getCurrentFrame(), getX(), getY(), null);
    }

    public void move(int dx, int dy) {
        int imageSize = 32;
        int newX = getX() + dx;
        int newY = getY() + dy;

        // Constrain movement to 800x800 area
        if (newX >= 0 && newX <= 800 - imageSize && newY >= 0 && newY <= 800 - imageSize*2) {
            moving = (dx != 0 || dy != 0);
            if (moving) {
                updateDirection(dx, dy);
                updateAnimationFrame();
                location.translate(dx, dy);
            } else {
                frameIndex = 0; // Reset to standing frame when not moving
            }
        }
    }

    private void updateDirection(int dx, int dy) {
        if (dx < 0) currentDirection = "LEFT";
        else if (dx > 0) currentDirection = "RIGHT";
        else if (dy < 0) currentDirection = "UP";
        else if (dy > 0) currentDirection = "DOWN";
    }

    protected abstract int getAnimationFrameCount();

    private void updateAnimationFrame() {
        long now = System.currentTimeMillis();
        if (now - lastFrameTime >= 200) {  // Adjust the frame duration as needed
            frameIndex = (frameIndex + 1) % getAnimationFrameCount();
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

    public int getX(){return location.x;}
    public int getY(){return location.y;}

    public void setLocation(Point location) {
        this.location = location;
    }
}
