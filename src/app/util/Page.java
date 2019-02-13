package app.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class Page {

    public static int errCode;
    public static Stage rootStage;

    public static void error(String title, String header, String message){

        System.out.println("(error) Root Stage: " + rootStage);

        rootStage.getScene().getRoot().setEffect(new GaussianBlur(3));

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:src/resources/_alert_error_icon.png"));

        alert.show();

        alert.setOnHidden(
                ae -> rootStage.getScene().getRoot().setEffect(null)
        );
    }

    public static void confirm(String title, String header, String message, Runnable func){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:src/resources/_alert_conf_icon.png"));

        Optional<ButtonType> res = alert.showAndWait();

        if(res.get() == ButtonType.OK){
            func.run();
        }

        rootStage.getScene().getRoot().setEffect(null);
        alert.close();

    }

}
