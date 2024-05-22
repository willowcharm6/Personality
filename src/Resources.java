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
            enemyRight, enemyRWalk, bearBack, bearBWalk1, bearFront, bearFWalk1, bearLeft, bearLWalk,
            bearRight, bearRWalk, aminaBack, aminaBWalk1, aminaFront, aminaFWalk1, aminaLeft, aminaLWalk,
            aminaRight, aminaRWalk, aminaIdle, auctionHouse;




    static{
        try{

            //random stuff

            auctionHouse = ImageIO.read(new File("./res/AuctionHouse.png"));
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
                bearBack = ImageIO.read(new File("./res/Bear/Bear_sprite_back.png"));
                bearBWalk1 = ImageIO.read(new File("./res/Bear/Bear_sprite_back_walk.png"));
                bearFront = ImageIO.read(new File("./res/Bear/Bear_sprite_front.png"));
                bearFWalk1 = ImageIO.read(new File("./res/Bear/Bear_sprite_front_walk.png"));
                bearLeft = ImageIO.read(new File("./res/Bear/Bear_sprite_left.png"));
                bearLWalk = ImageIO.read(new File("./res/Bear/Bear_sprite_left_walk.png"));
                bearRight = ImageIO.read(new File("./res/Bear/Bear_sprite_right.png"));
                bearRWalk = ImageIO.read(new File("./res/Bear/Bear_sprite_right_walk.png"));
            }
            //Amina images
            {
                aminaBack = ImageIO.read(new File("./res/Amina/Amina_sprite_back.png"));
                aminaBWalk1 = ImageIO.read(new File("./res/Amina/Amina_sprite_back_walk.png"));
                aminaFront = ImageIO.read(new File("./res/Amina/Amina_sprite_front.png"));
                aminaFWalk1 = ImageIO.read(new File("./res/Amina/Amina_sprite_front_walk.png"));
                aminaLeft = ImageIO.read(new File("./res/Amina/Amina_sprite_left.png"));
                aminaLWalk = ImageIO.read(new File("./res/Amina/Amina_sprite__left_walk.png"));
                aminaRight = ImageIO.read(new File("./res/Amina/Amina_sprite_right.png"));
                aminaRWalk = ImageIO.read(new File("./res/Amina/Amina_sprite_right_walk.png"));
                aminaIdle = ImageIO.read(new File("./res/Amina/Amina_sprite_idle.png"));
            }


        }catch(Exception e){e.printStackTrace();}
    }
}