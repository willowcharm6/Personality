import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Sprite {
    private boolean isShooting;
    private BufferedImage image;
    private int coins;

    public Player(Point location, BufferedImage image) {
        super(location, image);
        isShooting = false;
        coins = 0;
        loseHealth(-10);
    }

    public int getCoins(){
        return coins;
    }

    public void changeCoins(int amount){
        coins = coins + amount;
        if (coins > 999){
            coins = 999;
        }
    }

    public void shoot(){
        isShooting = true;
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
