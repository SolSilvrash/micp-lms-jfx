package app;

import app.local.UI.controller.RequestController;
import app.local.UI.controller.SplashController;
import app.local.obj.Account;
import app.util.Page;
import app.util.config.StageConf;
import app.local.UI.controller.LoginController;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage loginStage;
    private AnchorPane loginRoot;
    private Account user;

    @Override
    public void start(Stage primaryStage) {

        loginStage = primaryStage;
        StageConf.setProperties(loginStage);
        initLogin();
        Page.rootStage = loginStage;
    }
// ================================================= Login =================================================
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
// ================================================= Splash =================================================
    public void loadSplash(){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("local/UI/fxml/Splash.fxml"));

            AnchorPane pane = loader.load();

            loginRoot.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            fadeIn.play();

            SplashController controller = loader.getController();
            controller.setUser(user);
            controller.setMainApp(this);
            controller.access();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// ================================================= Request =================================================
    public void loadRequest(){

        FXMLLoader requestFXML;

        loginRoot.setEffect(new GaussianBlur(3));
        Page.rootStage = loginStage;

        requestFXML = Page.modal(
                "local/UI/fxml/Request.fxml"
        );

        if (requestFXML != null) {
            RequestController controller = requestFXML.getController();
            controller.setStage(Page.dialogStage);
            controller.setMainApp(this);
        } else {
            System.out.println("Alert: Request FXML is null.");
        }

    }

// ================================================= Main Interface =================================================
    public void initMainInterface(){

        switch(user.getAccount_type()){
            case "0" :
                System.out.println("Account Type : Accounts Access Administrator");
                break;
            case "1":
                System.out.println("Account Type: Administrator");
                break;
            default:
                System.out.println("Account Type: Student");
                break;
        }
    }
}
