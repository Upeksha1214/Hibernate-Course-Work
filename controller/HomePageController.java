package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class HomePageController {

    public StackPane loadStackPane;
    public Text txtDate;
    public Text txtTime;

    public void initialize() {
        loadDateAndTime();
    }

    private void loadDateAndTime() {
        //loadDate
        Date date= new Date();
        SimpleDateFormat f= new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(f.format(date));

        //loadTime
        Timeline time =new Timeline(new KeyFrame(Duration.ZERO, e ->{
            LocalTime currentTime =LocalTime.now();
            txtTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()

            );
        } ),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void studentAddOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/studentAdd.fxml");
        Parent load = FXMLLoader.load(resource);
        loadStackPane.getChildren().clear();
        loadStackPane.getChildren().add(load);
    }

    public void viewStudentOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageStudent.fxml");
        Parent load = FXMLLoader.load(resource);
        loadStackPane.getChildren().clear();
        loadStackPane.getChildren().add(load);
    }

    public void updateStudentOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/UpdateStudent.fxml");
        Parent load = FXMLLoader.load(resource);
        loadStackPane.getChildren().clear();
        loadStackPane.getChildren().add(load);
    }

    public void DeleteStudentOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/DeleteStudent.fxml");
        Parent load = FXMLLoader.load(resource);
        loadStackPane.getChildren().clear();
        loadStackPane.getChildren().add(load);
    }

    public void ManageCourseStudent(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageCourse.fxml");
        Parent load = FXMLLoader.load(resource);
        loadStackPane.getChildren().clear();
        loadStackPane.getChildren().add(load);
    }
}
