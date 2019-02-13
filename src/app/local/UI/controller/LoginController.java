package app.local.UI.controller;

import app.MainApp;
import app.local.obj.Account;
import app.local.server.Login;
import app.util.PBLoader;
import app.util.Page;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class LoginController {

    private MainApp mainApp;
    private double xOffset;
    private double yOffset;
    @SuppressWarnings("FieldCanBeLocal")
    private Account user;
    @SuppressWarnings("FieldCanBeLocal")
    private boolean isValid;

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
                    try {
                        loginAction();
                    } catch (NoSuchAlgorithmException | SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
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

    private void loginAction() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {

        System.out.println("Login initializing ... ");

        try {

            user = Login.login(userTF.getText(), passPF.getText());
            isValid = Login.isAccountValid();

            if(isValid){

                System.out.println("Login Established ...");
                System.out.println("Account_UserType : " + user.getAccount_type());
                mainApp.setUser(user);
                System.exit(0);

            } else {
                restoreFieldStatus();
                System.out.println(Page.errCode);
                Page.errCode = 3;
                if(Page.errCode == 3){
                    Page.error(
                            "LOGIN FAILED",
                            "Account Lookup Failed",
                            "No Such Account is found in our database. Please contact the administrator for " +
                                    "your credentials.\n" +
                                    "Check if your password is correct."
                    );
                }
                System.out.println("Error Logging in to Application...");
            }

        } catch (NoSuchAlgorithmException | SQLException | ClassNotFoundException e) {
            restoreFieldStatus();
            System.out.println("An error occurred in the system. Error: " + e);
            throw e;
        }

    }


}
