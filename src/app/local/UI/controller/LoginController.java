package app.local.UI.controller;

import app.MainApp;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;


public class LoginController {

    @SuppressWarnings("FieldCanBeLocal")
    private MainApp mainApp;
    private double xOffset;
    private double yOffset;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public JFXTextField getUserTextField() {
        return null;
    }

    @FXML
    private void handlePressWindow(MouseEvent e){
        xOffset = e.getSceneX();
        yOffset = e.getSceneY();
    }
}
