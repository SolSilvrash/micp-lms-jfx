package app;

import app.local.obj.Account;
import app.util.Page;
import app.util.config.StageConf;
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
    private Account user;

    @Override
    public void start(Stage primaryStage){

        loginStage = primaryStage;
        StageConf.setProperties(loginStage);
        initLogin();
        Page.rootStage = loginStage;
    }

    private void initLogin(){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("local/UI/fxml/Login.fxml"));

            loginRoot = loader.load();
            Scene scene = new Scene(loginRoot);
            scene.setFill(Color.TRANSPARENT);

            loginStage.setScene(scene);

            LoginController controller = loader.getController();
            controller.setMainApp(this);
            controller.getUserTextField().requestFocus();

            loginStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getLoginStage(){
        return loginStage;
    }

    public void setUser(Account user){
        this.user = user;
    }
}
