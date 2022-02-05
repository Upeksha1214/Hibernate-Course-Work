package controller;

import bo.BoFactory;
import bo.custom.ManageCourseBO;
import dto.CourseDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.ManageCourseTm;

import java.util.List;
import java.util.Optional;

public class ManageCourseController {
    public TextField txtProgrammeId;
    public TextField txtProgramme;
    public TextField txtDuration;
    public TextField txtFree;
    public TableView <ManageCourseTm> tblCourse;

    ManageCourseBO manageCourseBO= (ManageCourseBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.Course);

    public void initialize(){
        tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("programmeId"));
        tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("programme"));
        tblCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblCourse.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("free"));

        loadAllCourse();


    }

    private void loadAllCourse(){
        tblCourse.getItems().clear();
        List<CourseDTO> allCourses=manageCourseBO.getAllCourse();
        for (CourseDTO allCourse:allCourses) {
            tblCourse.getItems().add(new ManageCourseTm(allCourse.getProgrammeId(),allCourse.getProgramme(),allCourse.getDuration(),allCourse.getFree()));
        }
    }

    public void addOnAction(ActionEvent actionEvent) {

        if(manageCourseBO.ifExistsCourse(txtProgrammeId.getText())){
            new Alert(Alert.AlertType.WARNING,"Course All ready").showAndWait();
        }else {
            manageCourseBO.addCourse(new CourseDTO(txtProgrammeId.getText(),txtProgramme.getText(),txtDuration.getText(),txtFree.getText()));
            loadAllCourse();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        manageCourseBO.updateCourse(new CourseDTO(txtProgrammeId.getText(),txtProgramme.getText(),txtDuration.getText(),txtFree.getText()));

        new Alert(Alert.AlertType.CONFIRMATION,"updated").show();
        loadAllCourse();
    }

    public void selectOnAction(ActionEvent actionEvent) {
        ManageCourseTm selectedItem = tblCourse.getSelectionModel().getSelectedItem();

        ButtonType ok = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"are you sure select", ok, cancel);

        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.orElse(ok)==ok){
            txtProgrammeId.setText(selectedItem.getProgrammeId());
            txtProgramme.setText(selectedItem.getProgramme());
            txtDuration.setText(selectedItem.getDuration());
            txtFree.setText(String.valueOf(selectedItem.getFree()));
        }
    }
}
