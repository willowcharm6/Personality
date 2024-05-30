import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Resources {
    // to add an image to the environment:
    // 1. put the file into the res folder.
    // 2. Declare a variable before the static block.
    // 3. Initialize the variable by copying and pasting and modifying the
    //    ImageIO line.

    public static Clip musicNormal, musicAuction, door,door2, boing, coin;

    public static BufferedImage playerFront, titleCard, loseScreen, playerBStill, playerBWalk1, playerBWalk2,
            playerFWalk1, playerFWalk2, playerLeft, playerLWalk, playerRight, playerRWalk, enemyBack,
            enemyBWalk1, enemyBWalk2, enemyFront, enemyFWalk1, enemyFWalk2, enemyLeft, enemyLWalk,
            enemyRight, enemyRWalk, bearBack, bearBWalk1, bearFront, bearFWalk1, bearLeft, bearLWalk,
            bearRight, bearRWalk, aminaBack, aminaBWalk1, aminaFront, aminaFWalk1, aminaLeft, aminaLWalk,
            aminaRight, aminaRWalk, aminaIdle, auctionHouse, aminaPlants, aminaSwirl, amina,
            cave, auctionHouse2, lair, inAuctionHouse, caspianBack, caspianBWalk1, caspianBWalk2, caspianFront,
            caspianFWalk1, caspianFWalk2, caspianLeft, caspianLWalk, caspianRight, caspianRWalk, caspianIdle,
            caspianRain, caspianWave1, caspian, lair2, melonieBack, melonieBWalk1, melonieBWalk2, melonieFront,
            melonieFWalk1, melonieFWalk2, melonieLeft, melonieLWalk, melonieRight, melonieRWalk, melonieIdle,
            melonieMelonSeed, melonieMelon, melonie, violaBack, violaBWalk1, violaBWalk2, violaFront,
            violaFWalk1, violaFWalk2, violaLeft, violaLWalk, violaRight, violaRWalk, violaIdle,
            violaGuitar, violaNote, viola, tutorial, details, details2, arrows;




    static{
        try{

            /// music
            File soundFile = new File("./res/Personality_music.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            musicNormal = AudioSystem.getClip();

            musicNormal.open(audioIn);

            File soundFile2 = new File("./res/Personality_musicElevator.wav");
            AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(soundFile2);
            musicAuction = AudioSystem.getClip();

            musicAuction.open(audioIn2);

            File soundFile3 = new File("./res/door.wav");
            AudioInputStream audioIn3 = AudioSystem.getAudioInputStream(soundFile3);
            door = AudioSystem.getClip();

            door.open(audioIn3);

            File soundFile4 = new File("./res/door2.wav");
            AudioInputStream audioIn4 = AudioSystem.getAudioInputStream(soundFile4);
            door2 = AudioSystem.getClip();

            door2.open(audioIn4);

            File soundFile5 = new File("./res/coin.wav");
            AudioInputStream audioIn5 = AudioSystem.getAudioInputStream(soundFile5);
            coin = AudioSystem.getClip();

            coin.open(audioIn5);

            //random stuff

            auctionHouse = ImageIO.read(new File("./res/AuctionHouse.png"));
            auctionHouse2 = ImageIO.read(new File("./res/AuctionHouse2.png"));
            cave = ImageIO.read(new File("./res/cave.png"));
            lair = ImageIO.read(new File("./res/lair.png"));
            lair2 = ImageIO.read(new File("./res/lair2.png"));

            arrows = ImageIO.read(new File("./res/arrows.png"));
            details = ImageIO.read(new File("./res/details.png"));
            details2 = ImageIO.read(new File("./res/details2.png"));
            tutorial = ImageIO.read(new File("./res/tutorial.png"));
            titleCard = ImageIO.read(new File("./res/TitleCard.png"));
            loseScreen = ImageIO.read(new File("./res/lose.png"));
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
            //Melonie images
            {
                melonieBack = ImageIO.read(new File("./res/Melonie/Melonie_sprite_back.png"));
                melonieBWalk1 = ImageIO.read(new File("./res/Melonie/Melonie_sprite_back_walk1.png"));
                melonieBWalk2 = ImageIO.read(new File("./res/Melonie/Melonie_sprite_back_walk2.png"));
                melonieFront = ImageIO.read(new File("./res/Melonie/Melonie_sprite_front.png"));
                melonieFWalk1 = ImageIO.read(new File("./res/Melonie/Melonie_sprite_front_walk1.png"));
                melonieFWalk2 = ImageIO.read(new File("./res/Melonie/Melonie_sprite_front_walk2.png"));
                melonieLeft = ImageIO.read(new File("./res/Melonie/Melonie_sprite_left.png"));
                melonieLWalk = ImageIO.read(new File("./res/Melonie/Melonie_sprite_left_walk.png"));
                melonieRight = ImageIO.read(new File("./res/Melonie/Melonie_sprite_right.png"));
                melonieRWalk = ImageIO.read(new File("./res/Melonie/Melonie_sprite_right_walk.png"));
                melonieIdle = ImageIO.read(new File("./res/Melonie/Melonie_sprite_idle.png"));
                melonieMelon = ImageIO.read(new File("./res/Attacks/Melonie_WatermelonHelmet.png"));
                melonieMelonSeed = ImageIO.read(new File("./res/Attacks/Melonie_WatermelonSeed.png"));
                melonie = ImageIO.read(new File("./res/Melonie/Melonie.png"));
            }
            //Viola images
            {
                violaBack = ImageIO.read(new File("./res/Viola/Viola_sprite_back.png"));
                violaBWalk1 = ImageIO.read(new File("./res/Viola/Viola_sprite_back_walk1.png"));
                violaBWalk2 = ImageIO.read(new File("./res/Viola/Viola_sprite_back_walk2.png"));
                violaFront = ImageIO.read(new File("./res/Viola/Viola_sprite_front.png"));
                violaFWalk1 = ImageIO.read(new File("./res/Viola/Viola_sprite_front_walk1.png"));
                violaFWalk2 = ImageIO.read(new File("./res/Viola/Viola_sprite_front_walk2.png"));
                violaLeft = ImageIO.read(new File("./res/Viola/Viola_sprite_left.png"));
                violaLWalk = ImageIO.read(new File("./res/Viola/Viola_sprite_left_walk.png"));
                violaRight = ImageIO.read(new File("./res/Viola/Viola_sprite_right.png"));
                violaRWalk = ImageIO.read(new File("./res/Viola/Viola_sprite_right_walk.png"));
                violaIdle = ImageIO.read(new File("./res/Viola/Viola_sprite_idle.png"));
                violaGuitar = ImageIO.read(new File("./res/Attacks/Viola_GuitarSmash.png"));
                violaNote = ImageIO.read(new File("./res/Attacks/Viola_MusicNote1.png"));
                viola = ImageIO.read(new File("./res/Viola/Viola.png"));
            }


        }catch(Exception e){e.printStackTrace();}
    }
}