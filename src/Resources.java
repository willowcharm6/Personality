import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Resources {
    // to add an image to the environment:
    // 1. put the file into the res folder.
    // 2. Declare a variable before the static block.
    // 3. Initialize the variable by copying and pasting and modifying the
    //    ImageIO line.


    public static BufferedImage playerFront, titleCard, playerBStill, playerBWalk1, playerBWalk2, playerFWalk1, playerFWalk2, playerLeft, playerLWalk, playerRight, playerRWalk;




    static{
        try{
            playerFront = ImageIO.read(new File("./res/Player_sprite_front.png"));
            titleCard = ImageIO.read(new File("./res/TitleCard.png"));
            playerBStill = ImageIO.read(new File("./res/Player_sprite_back.png"));
            playerBWalk1 = ImageIO.read(new File("./res/Player_sprite_back_walk1.png"));
            playerBWalk2 = ImageIO.read(new File("./res/Player_sprite_walk2.png"));
            playerFWalk1 = ImageIO.read(new File("./res/Player_sprite_front_walk1.png"));
            playerFWalk2 = ImageIO.read(new File("./res/Player_sprite_front_walk2.png"));
            playerLeft = ImageIO.read(new File("./res/Player_sprite_left.png"));
            playerLWalk = ImageIO.read(new File("./res/Player_sprite_left_walk.png"));
            playerRight = ImageIO.read(new File("./res/Player_sprite_right.png"));
            playerRWalk = ImageIO.read(new File("./res/Player_sprite_right_walk.png"));


        }catch(Exception e){e.printStackTrace();}
    }
}