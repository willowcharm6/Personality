import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Resources {
    // to add an image to the environment:
    // 1. put the file into the res folder.
    // 2. Declare a variable before the static block.
    // 3. Initialize the variable by copying and pasting and modifying the
    //    ImageIO line.
    public BufferedImage getFromString(String in) {
        switch (in) {
            case "playerFront":
                return playerFront;
        }

        return null;
    }

    public static BufferedImage playerFront, titleCard, playerBStill, playerBWalk1, playerBWalk2,
            playerFWalk1, playerFWalk2, playerLeft, playerLWalk, playerRight, playerRWalk, enemyBack,
            enemyBWalk1, enemyBWalk2, enemyFront, enemyFWalk1, enemyFWalk2, enemyLeft, enemyLWalk,
            enemyRight, enemyRWalk, bearBack, bearBWalk1, bearFront, bearFWalk1, bearLeft, bearLWalk,
            bearRight, bearRWalk, aminaBack, aminaBWalk1, aminaFront, aminaFWalk1, aminaLeft, aminaLWalk,
            aminaRight, aminaRWalk, aminaIdle, auctionHouse, aminaPlants, aminaSwirl, amina,
            cave, auctionHouse2, lair, inAuctionHouse, caspianBack, caspianBWalk1, caspianBWalk2, caspianFront, caspianFWalk1, caspianFWalk2, caspianLeft, caspianLWalk,
    caspianRight, caspianRWalk, caspianIdle, caspianRain, caspianWave1, caspian;


    static{
        try{

            //random stuff

            auctionHouse = ImageIO.read(new File("./res/AuctionHouse.png"));
            auctionHouse2 = ImageIO.read(new File("./res/AuctionHouse2.png"));
            cave = ImageIO.read(new File("./res/cave.png"));
            lair = ImageIO.read(new File("./res/lair.png"));

            titleCard = ImageIO.read(new File("./res/TitleCard.png"));
            inAuctionHouse = ImageIO.read(new File("./res/pause_box.png"));

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
                aminaPlants = ImageIO.read(new File("./res/Attacks/Amina_Plants.png"));
                aminaSwirl = ImageIO.read(new File("./res/Attacks/Amina_Swirl.png"));
                amina = ImageIO.read(new File("./res/Amina/Amina.png"));
            }
            //Caspian images
            {
                caspianBack = ImageIO.read(new File("./res/Caspian/Caspian_sprite_back.png"));
                caspianBWalk1 = ImageIO.read(new File("./res/Caspian/Caspian_sprite_back_walk1.png"));
                caspianBWalk2 = ImageIO.read(new File("./res/Caspian/Caspian_sprite_back_walk2.png"));
                caspianFront = ImageIO.read(new File("./res/Caspian/Caspian_sprite_front.png"));
                caspianFWalk1 = ImageIO.read(new File("./res/Caspian/Caspian_sprite_front_walk1.png"));
                caspianFWalk2 = ImageIO.read(new File("./res/Caspian/Caspian_sprite_front_walk2.png"));
                caspianLeft = ImageIO.read(new File("./res/Caspian/Caspian_sprite_left.png"));
                caspianLWalk = ImageIO.read(new File("./res/Caspian/Caspian_sprite_left_walk.png"));
                caspianRight = ImageIO.read(new File("./res/Caspian/Caspian_sprite_right.png"));
                caspianRWalk = ImageIO.read(new File("./res/Caspian/Caspian_sprite_right_walk.png"));
                caspianIdle = ImageIO.read(new File("./res/Caspian/Caspian_sprite_idle.png"));
                caspianRain = ImageIO.read(new File("./res/Attacks/Caspian_Rain.png"));
                caspianWave1 = ImageIO.read(new File("./res/Attacks/Caspian_Wave1.png"));
                caspian = ImageIO.read(new File("./res/Caspian/Caspian.png"));
            }


        }catch(Exception e){e.printStackTrace();}
    }
}