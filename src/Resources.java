import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Resources {
    // to add an image to the environment:
    // 1. put the file into the res folder.
    // 2. Declare a variable before the static block.
    // 3. Initialize the variable by copying and pasting and modifying the
    //    ImageIO line.


    public static BufferedImage playerFront, titleCard, playerBStill, playerBWalk1, playerBWalk2,
            playerFWalk1, playerFWalk2, playerLeft, playerLWalk, playerRight, playerRWalk, enemyBack,
            enemyBWalk1, enemyBWalk2, enemyFront, enemyFWalk1, enemyFWalk2, enemyLeft, enemyLWalk,
            enemyRight, enemyRWalk, bearBack, bearBWalk1, bearBWalk2, bearFront, bearFWalk1,
            bearFWalk2, bearLeft, bearLWalk, bearRight, bearRWalk;




    static{
        try{
            titleCard = ImageIO.read(new File("./res/TitleCard.png"));
            // player images
            {
                playerFront = ImageIO.read(new File("./res/Player/Player_sprite_front.png"));
                playerBStill = ImageIO.read(new File("./res/Player/Player_sprite_back.png"));
                playerBWalk1 = ImageIO.read(new File("./res/Player/Player_sprite_back_walk1.png"));
                playerBWalk2 = ImageIO.read(new File("./res/Player/Player_sprite_back_walk2.png"));
                playerFWalk1 = ImageIO.read(new File("./res/Player/Player_sprite_front_walk1.png"));
                playerFWalk2 = ImageIO.read(new File("./res/Player/Player_sprite_front_walk2.png"));
                playerLeft = ImageIO.read(new File("./res/Player/Player_sprite_left.png"));
                playerLWalk = ImageIO.read(new File("./res/Player/Player_sprite_left_walk.png"));
                playerRight = ImageIO.read(new File("./res/Player/Player_sprite_right.png"));
                playerRWalk = ImageIO.read(new File("./res/Player/Player_sprite_right_walk.png"));
            }
            //enemy images
            {
                enemyBack = ImageIO.read(new File("./res/Enemy/Enemy_sprite_back.png"));
                enemyBWalk1 = ImageIO.read(new File("./res/Enemy/Enemy_sprite_back_walk1.png"));
                enemyBWalk2 = ImageIO.read(new File("./res/Enemy/Enemy_sprite_back_walk2.png"));
                enemyFront = ImageIO.read(new File("./res/Enemy/Enemy_sprite_front.png"));
                enemyFWalk1 = ImageIO.read(new File("./res/Enemy/Enemy_sprite_front_walk1.png"));
                enemyFWalk2 = ImageIO.read(new File("./res/Enemy/Enemy_sprite_front_walk2.png"));
                enemyLeft = ImageIO.read(new File("./res/Enemy/Enemy_sprite_left.png"));
                enemyLWalk = ImageIO.read(new File("./res/Enemy/Enemy_sprite_left_walk.png"));
                enemyRight = ImageIO.read(new File("./res/Enemy/Enemy_sprite_right.png"));
                enemyRWalk = ImageIO.read(new File("./res/Enemy/Enemy_sprite_right_walk.png"));
            }
            //bear images
            {
                bearBack = ImageIO.read(new File("./res/Bear/Enemy_sprite_back.png"));
                bearBWalk1 = ImageIO.read(new File("./res/Bear/Enemy_sprite_back_walk1.png"));
                bearBWalk2 = ImageIO.read(new File("./res/Bear/Enemy_sprite_back_walk2.png"));
                bearFront = ImageIO.read(new File("./res/Bear/Enemy_sprite_front.png"));
                bearFWalk1 = ImageIO.read(new File("./res/Bear/Enemy_sprite_front_walk1.png"));
                bearFWalk2 = ImageIO.read(new File("./res/Bear/Enemy_sprite_front_walk2.png"));
                bearLeft = ImageIO.read(new File("./res/Bear/Enemy_sprite_left.png"));
                bearLWalk = ImageIO.read(new File("./res/Bear/Enemy_sprite_left_walk.png"));
                bearRight = ImageIO.read(new File("./res/Bear/Enemy_sprite_right.png"));
                bearRWalk = ImageIO.read(new File("./res/Bear/Enemy_sprite_right_walk.png"));
            }


        }catch(Exception e){e.printStackTrace();}
    }
}