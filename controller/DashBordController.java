package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBordController {
    public PasswordField pswPasswordFild;
    public TextField txtUserName;
    public AnchorPane backgroundContex;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
         /* URL resource= getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window=(Stage) backgroundContex.getScene().getWindow();
        window.setScene(new Scene(load));*/


        if (txtUserName.getText().equalsIgnoreCase("admin") && pswPasswordFild.getText().equalsIgnoreCase("abcd")) {
            Stage window = (Stage) backgroundContex.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"))));
            window.centerOnScreen();
            window.setTitle("Manager DashBoard");
        }
        else {

            new Alert(Alert.AlertType.WARNING, "Wrong Username or Password", ButtonType.CLOSE).show();

        }
    }
}
