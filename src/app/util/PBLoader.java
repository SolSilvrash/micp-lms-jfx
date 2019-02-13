package app.util;

import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class PBLoader {

    public static void load(Runnable func, JFXProgressBar pb, double delay){

        pb.setVisible(true);

        Timeline tl = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(pb.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(delay),
                        ae -> {
                            pb.setVisible(false);
                            func.run();
                        },
                        new KeyValue(pb.progressProperty(), 1)
                )
        );

        tl.play();
    }

}
