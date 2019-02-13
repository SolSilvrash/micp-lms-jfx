package app.util.config;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageConf {

    public static void setProperties(Stage stage){
        stage.setTitle("");
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("file:src/resources/_logo.png"));
    }

}
