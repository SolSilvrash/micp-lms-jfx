package app.local.UI.controller;

import app.MainApp;
import app.local.server.Request;
import app.util.PBLoader;
import app.util.Page;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;

public class RequestController {

    private MainApp mainApp;
    private Stage mainStage;

    private double xOffset;
    private double yOffset;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setStage(Stage mainStage){
        this.mainStage = mainStage;
    }

    @FXML
    private JFXTextField fnameTF;
    @FXML
    private JFXTextField mnameTF;
    @FXML
    private JFXTextField lnameTF;
    @FXML
    private JFXTextField xnameTF;
    @FXML
    private JFXTextField addressTF;
    @FXML
    private JFXTextField contactTF;
    @FXML
    private JFXTextArea remarksTA;
    @FXML
    private JFXButton submitBtn;
    @FXML
    private JFXProgressBar progress;
    @FXML
    private JFXProgressBar progress_success;
    @FXML
    private AnchorPane form;
    @FXML
    private AnchorPane mainContainer;

    @FXML
    private void initialize(){
    }

    @FXML
    private void handlePressWindow(MouseEvent e){
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }

    @FXML
    private void handleDragWindow(MouseEvent e){
        Page.dialogStage.setX(e.getScreenX() - xOffset);
        Page.dialogStage.setY(e.getScreenY() - yOffset);
    }

    @FXML
    private void handleExit(){
        mainApp.getLoginStage().getScene().getRoot().setEffect(null);
        Page.dialogStage.close();
    }

    @FXML
    private void handleMin(){
        fnameTF.requestFocus();
        mainApp.getLoginStage().setIconified(true);
    }


    @FXML
    private void submitRequest(){

        disableFieldStatus();
        handleRequest();
    }

    private void disableFieldStatus(){

        fnameTF.setDisable(true);
        lnameTF.setDisable(true);
        mnameTF.setDisable(true);
        xnameTF.setDisable(true);
        addressTF.setDisable(true);
        contactTF.setDisable(true);
        remarksTA.setDisable(true);
        submitBtn.setDisable(true);
    }

    private void enableFieldStatus(){

        fnameTF.setDisable(false);
        lnameTF.setDisable(false);
        mnameTF.setDisable(false);
        xnameTF.setDisable(false);
        addressTF.setDisable(false);
        contactTF.setDisable(false);
        remarksTA.setDisable(false);
        submitBtn.setDisable(false);
    }

    private boolean isValid(){

        String msg = "";

        if(fnameTF.getText().equals("") || fnameTF.getText().length() == 0)
            msg += "- First Name Field must not be empty. Please supply appropriate information for first time. \n";
        if(lnameTF.getText().equals("") || lnameTF.getText().length() == 0)
            msg += "- Last Name Field must not be empty. Please supply appropriate information for last name. \n";
        if(addressTF.getText().equals("") || addressTF.getText().length() == 0)
            msg += "- Address Field must not be empty. Please supply appropriate information for address.";

        if(msg.length() > 0 || !msg.equals("")){
            Page.rootStage = mainStage;
            Page.error(
                    "VALIDATION ERROR",
                    "Request not successfully sent",
                    msg
            );
            return false;
        }
        return true;
    }

    private void handleRequest(){

        System.out.println("Pending Request ... ");

        PBLoader.load(
                () -> {
                    if(isValid()) {
                        System.out.println("Sending ... ");
                        try {
                            Request.send(
                                    fnameTF.getText(),
                                    mnameTF.getText(),
                                    lnameTF.getText(),
                                    xnameTF.getText(),
                                    addressTF.getText(),
                                    contactTF.getText(),
                                    remarksTA.getText()
                            );
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    showSuccess();
                    enableFieldStatus();
                },
                progress,
                1
        );
    }

    private void showSuccess(){

        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), form);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setCycleCount(1);

        fade.play();

        PBLoader.load(
                this::handleExit,
                progress_success,
                5
        );


    }



}
