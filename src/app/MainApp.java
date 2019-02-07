package app;

import app.core.config.StageConf;
import app.local.UI.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage loginStage;
    @SuppressWarnings("FieldCanBeLocal")
    private AnchorPane loginRoot;

    @Override
    public void start(Stage primaryStage) {

        loginStage = primaryStage;
        StageConf.setProperties(loginStage);
        initLogin();
    }

    private void initLogin(){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("local/UI/fxml/Login.fxml"));

            loginRoot = (AnchorPane) loader.load();
            Scene scene = new Scene(loginRoot);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().add("local/UI/css/theme.css");

            loginStage.setScene(scene);

            LoginController controller = loader.getController();
            controller.setMainApp(this);
//            controller.getUserTextField().requestFocus();

            loginStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
