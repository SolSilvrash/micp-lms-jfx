package app.local.UI.controller;

import app.MainApp;
import app.local.obj.Account;
import app.util.PBLoader;
import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class SplashController {

    private MainApp mainApp;
    private Account user;

    @FXML
    private JFXProgressBar progress;
    @FXML
    private Label userLbl;
    @FXML
    private Label status;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setUser(Account user){
        this.user = user;
    }

    public void access(){

        userLbl.setText("Account Name : " + user.getAccount_id());
        animateLabel();

        PBLoader.load(
                () -> {
                    System.out.println("Accessing Main Interface ... ");
                    mainApp.initMainInterface();
                    System.exit(0);
                },
                progress,
                8
        );
    }

    private void animateLabel(){

        final Timeline tl = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        ae -> {
                            String stat = status.getText();
                            status.setText(("Granting Access . . . .".equals(stat)) ? "Granting Access ." : stat + " .");
                        }
                ),
                new KeyFrame(
                        Duration.millis(1000)
                )
        );

        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }
}
