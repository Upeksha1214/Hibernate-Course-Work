package controller;

import bo.BoFactory;
import bo.SuperBo;
import bo.custom.ManageCourseBO;
import bo.custom.MangeStudentBO;
import dto.CourseDTO;
import dto.CustomDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.CourseModelTwo;
import view.tm.StudentTm;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ManageStudentController {
    public TableView <StudentTm>tblStudent;
    public TextField txtStudentId;
    public ComboBox cmbCourses;
    public RadioButton nameRadio;
    public RadioButton idRadio;
    public AnchorPane manageFromContext;

    ManageCourseBO manageCourseBO = (ManageCourseBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.Course);
    MangeStudentBO mangeStudentBO = (MangeStudentBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.STUDENT);
    List<CourseDTO> allCourses =mangeStudentBO.getAllCourse();
    public void initialize(){



        ObservableList<String> course= FXCollections.observableArrayList();
        course.add("All");
        for (CourseDTO allCourse : allCourses) {
            course.add(allCourse.getProgramme());
        }


        cmbCourses.setItems(course);

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("parentName"));
        tblStudent.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tblStudent.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("ol_result"));
        tblStudent.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("al_result"));
        tblStudent.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("email"));


    }

    private void loadAllStudentToTable(List<StudentDTO>studentList){
        tblStudent.getItems().clear();

        if (!(studentList==null)) {


            for (StudentDTO studentDTO : studentList) {

                tblStudent.getItems().add(new StudentTm(studentDTO.getId(), studentDTO.getNic(), studentDTO.getName(), studentDTO.getDateOfBirth(),
                        studentDTO.getGender(), studentDTO.getParentName(), studentDTO.getMobile(), studentDTO.getAddress(),
                        studentDTO.getOl_result(), studentDTO.getAl_result(), studentDTO.getEmail()));
            }
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        if (cmbCourses.getSelectionModel().getSelectedItem()=="All"){
            loadAllStudentToTable(mangeStudentBO.getAllStudent());
        }else {
            for (CourseDTO course :allCourses) {
                if (course.getProgramme()==cmbCourses.getSelectionModel().getSelectedItem()){
                    List<StudentDTO> studentDTOList = mangeStudentBO.getStudentFromCourse(course.getProgrammeId());
                    loadAllStudentToTable(studentDTOList);
                }
            }
        }
    }

    public void updateOnActuion(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/UpdateStudent.fxml"));
        Parent parent = fxmlLoader.load();
        UpdateController controller = fxmlLoader.<UpdateController>getController();
        StudentTm selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        controller.txtOLResult.setText(selectedItem.getOl_result());
        controller.txtAddress.setText(selectedItem.getAddress());
        controller.txtAlResult.setText(selectedItem.getAl_result());
        controller.txtDateBirth.setText(selectedItem.getDateOfBirth());
        controller.txtEmail.setText(selectedItem.getEmail());
        controller.txtMobileNumber.setText(selectedItem.getMobile());
        controller.txtNIC.setText(selectedItem.getNic());
        controller.txtStudentName.setText(selectedItem.getName());
        controller.txtStudentId.setText(selectedItem.getId());
        controller.txtPrentName.setText(selectedItem.getParentName());
        controller.txtGender.setText(selectedItem.getGender());

        List<CustomDTO> courseFromStudent = mangeStudentBO.getCourseFromStudent(selectedItem.getId());
        controller.loadCourseTable(courseFromStudent);


        manageFromContext.getChildren().clear();
        manageFromContext.getChildren().add(parent);
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        StudentTm selectedItem = tblStudent.getSelectionModel().getSelectedItem();

        mangeStudentBO.deleteStudent(new StudentDTO(selectedItem.getId(),selectedItem.getNic(),selectedItem.getName(),selectedItem.getDateOfBirth(),selectedItem.getGender(),selectedItem.getParentName(),selectedItem.getMobile(),selectedItem.getAddress(),selectedItem.getOl_result(),selectedItem.getAl_result(),selectedItem.getEmail()));
        new Alert(Alert.AlertType.CONFIRMATION,"deleted student").showAndWait();
        tblStudent.getItems().remove(selectedItem);
    }

    public void idRadioMouseClickOnAction(MouseEvent mouseEvent) {
        if (idRadio.isSelected()){
            nameRadio.setSelected(false);
        }
    }

    public void nameRadioMouseClickOnAction(MouseEvent mouseEvent) {
        if (nameRadio.isSelected()){
            idRadio.setSelected(false);
        }
    }
}
