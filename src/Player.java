import javax.naming.TimeLimitExceededException;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Sprite{
    final int NUM_FRAMES = 4;
    final int PAUSE_BETWEEN_FRAMES = 5;

    public Player(BufferedImage image, Point location) {
        super(image, location);
    }

    public void rightWalk(){
        ImageView imageView = new ImageView();
        Timeline timeline = new Timeline();

        List Images = new List(resources.player)

    }


//    ImageView imageView = new ImageView();
//    Timeline timeline = new Timeline();
//    List<Image> images = List.of(/** initialize your images here **/);
//
//for (int i = 0; i < NUM_FRAMES; i++) {
//        timeline.getKeyFrames().add(
//                new KeyFrame(
//                        Duration.seconds(i * PAUSE_BETWEEN_FRAMES),
//                        new KeyValue(imageView.imageProperty(), images.get(i))
//                )
//        );
//    }
//
//timeline.setCycleCount(Timeline.INDEFINITE);
//timeline.play();
}
