package app.local.UI.controller;

import app.MainApp;
import app.core.util.PBLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;


public class LoginController {

    private MainApp mainApp;
    private double xOffset;
    private double yOffset;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public JFXTextField getUserTextField() {
        return userTF;
    }

    @FXML
    private JFXTextField userTF;
    @FXML
    private JFXPasswordField passPF;
    @FXML
    private JFXProgressBar loginPB;
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXButton requestBtn;

    @FXML
    private void handlePressWindow(MouseEvent e){
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }

    @FXML
    private void handleDragWindow(MouseEvent e){
        mainApp.getLoginStage().setX(e.getScreenX() - xOffset);
        mainApp.getLoginStage().setY(e.getScreenY() - yOffset);
    }

    @FXML
    private void handleExit(){
        System.exit(0);
    }

    @FXML
    private void handleMin(){
        userTF.requestFocus();
        mainApp.getLoginStage().setIconified(true);
    }

    @FXML
    private void handleLogin(){

        disableFieldStatus();

        PBLoader.load(
                () -> {
                    System.out.println("logging in and authenticating ...");
                    restoreFieldStatus();
                },
                loginPB,
                1
        );

    }

    @FXML
    private void handleRequest(){

        disableFieldStatus();

        PBLoader.load(
                () -> {
                    System.out.println("initiating request ...");
                    restoreFieldStatus();
                },
                loginPB,
                1
        );

    }

    @FXML
    private void initialize(){
        System.out.println("MICP - LMS Application Initialized...");
    }

    private void disableFieldStatus(){
        userTF.setDisable(true);
        passPF.setDisable(true);
        loginBtn.setDisable(true);
        requestBtn.setDisable(true);
    }

    private void restoreFieldStatus(){

        loginPB.setProgress(0);

        userTF.setDisable(false);
        userTF.requestFocus();

        passPF.setDisable(false);
        passPF.setText("");

        loginBtn.setDisable(false);
        requestBtn.setDisable(false);
    }
}
