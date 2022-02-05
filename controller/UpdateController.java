package controller;

import bo.BoFactory;
import bo.custom.MangeStudentBO;
import dto.CourseDTO;
import dto.CustomDTO;
import dto.StudentCoursesDTO;
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

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class UpdateController {
    public TextField txtOLResult;
    public TextField txtAlResult;
    public TextField txtStudentId;
    public TextField txtPrentName;
    public TextField txtDateBirth;
    public String gender;
    public TextField txtNIC;
    public TextField txtStudentName;
    public TextField txtAddress;
    public TextField txtMobileNumber;
    public TextField txtEmail;
    public TableView<CourseModelTwo> courseTable;
    public ComboBox cmbCourseName;
    public TextField txtGender;
    public TableColumn colCourseName;
    public TableColumn colCourseRemove;
    public AnchorPane updateContext;

    private MangeStudentBO manageStudentBO =(MangeStudentBO) BoFactory.getBoFactory().
            getBO(BoFactory.BoTypes.STUDENT);
    private List<CourseDTO> allCourses = manageStudentBO.getAllCourse();

    public void initialize(){


        ObservableList<String> courseNames= FXCollections.observableArrayList();
        for (CourseDTO allCourses : allCourses) {
            courseNames.add(allCourses.getProgramme());
        }
        cmbCourseName.setItems(courseNames);

        courseTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("programme"));
        courseTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("remove"));

        /*tblCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Button remove = newValue.getRemove();
            remove.setOnAction(event -> {

            });*/
    }

    public void loadCourseTable(List<CustomDTO>courseFromStudent){
        for (CustomDTO customDTO : courseFromStudent) {
            Button remove = new Button("Remove");
            System.out.println(customDTO.getProgramme());
            courseTable.getItems().add(new CourseModelTwo(customDTO.getProgramme(),remove));
        }

    }

    public void studentUpdateOnAction(ActionEvent actionEvent) {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(txtStudentId.getText());
        studentDTO.setNic(txtNIC.getText());
        studentDTO.setName(txtStudentName.getText());
        studentDTO.setDateOfBirth(txtDateBirth.getText());
        studentDTO.setGender(txtGender.getText());
        studentDTO.setParentName(txtPrentName.getText());
        studentDTO.setMobile(txtMobileNumber.getText());
        studentDTO.setAddress(txtAddress.getText());
        studentDTO.setOl_result(txtOLResult.getText());
        studentDTO.setAl_result(txtAlResult.getText());
        studentDTO.setEmail(txtEmail.getText());

        manageStudentBO.updateStudent(studentDTO);
        new Alert(Alert.AlertType.CONFIRMATION,"Student Updated").showAndWait();
        clear();
    }

    public void clear(){
        txtStudentId.clear();
        txtStudentName.clear();
        txtNIC.clear();
        txtDateBirth.clear();
        txtPrentName.clear();
        txtMobileNumber.clear();
        txtAddress.clear();
        txtOLResult.clear();
        txtAlResult.clear();
        txtEmail.clear();
        cmbCourseName.setValue("");
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void addCourse(ActionEvent actionEvent) {
        for (CourseDTO course : allCourses) {
            if (cmbCourseName.getValue().equals(course.getProgramme())){

                List<CourseModelTwo> items = courseTable.getItems();

                if (courseTable.getItems().isEmpty()){
                    courseTable.getItems().add(new CourseModelTwo(course.getProgramme(),new Button("remove")));
                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO.setId(txtStudentId.getText());
                    studentDTO.setNic(txtNIC.getText());
                    studentDTO.setName(txtStudentName.getText());
                    studentDTO.setDateOfBirth(txtDateBirth.getText());
                    studentDTO.setGender(txtGender.getText());
                    studentDTO.setParentName(txtPrentName.getText());
                    studentDTO.setMobile(txtMobileNumber.getText());
                    studentDTO.setAddress(txtAddress.getText());
                    studentDTO.setOl_result(txtOLResult.getText());
                    studentDTO.setAl_result(txtAlResult.getText());
                    studentDTO.setEmail(txtEmail.getText());

                    CourseDTO courseDTO = new CourseDTO();
                    courseDTO.setProgrammeId(course.getProgrammeId());
                    courseDTO.setProgramme(course.getProgramme());
                    courseDTO.setDuration(course.getDuration());
                    courseDTO.setFree(course.getFree());

                    StudentCoursesDTO studentCoursesDTO = new StudentCoursesDTO(studentDTO,courseDTO);
                    manageStudentBO.addCourseToStudent(studentCoursesDTO);


                }else {
                    for (CourseModelTwo item : items) {
                        if (item.getProgramme() == course.getProgramme()) {
                            new Alert(Alert.AlertType.WARNING, "Data Duplicate").showAndWait();
                        } else {
                            courseTable.getItems().add(new CourseModelTwo(course.getProgramme(),new Button("remove")));

                            StudentDTO studentDTO = new StudentDTO();
                            studentDTO.setId(txtStudentId.getText());
                            studentDTO.setNic(txtNIC.getText());
                            studentDTO.setName(txtStudentName.getText());
                            studentDTO.setDateOfBirth(txtDateBirth.getText());
                            studentDTO.setGender(txtGender.getText());
                            studentDTO.setParentName(txtPrentName.getText());
                            studentDTO.setMobile(txtMobileNumber.getText());
                            studentDTO.setAddress(txtAddress.getText());
                            studentDTO.setOl_result(txtOLResult.getText());
                            studentDTO.setAl_result(txtAlResult.getText());
                            studentDTO.setEmail(txtEmail.getText());

                            CourseDTO courseDTO = new CourseDTO();
                            courseDTO.setProgrammeId(course.getProgrammeId());
                            courseDTO.setProgramme(course.getProgramme());
                            courseDTO.setDuration(course.getDuration());
                            courseDTO.setFree(course.getFree());

                            StudentCoursesDTO studentCoursesDTO = new StudentCoursesDTO(studentDTO,courseDTO);
                            manageStudentBO.addCourseToStudent(studentCoursesDTO);

                        }
                    }
                }
            }
        }
    }

    public void tblCourseRomeOnAction(MouseEvent mouseEvent) {
        if (!courseTable.getItems().isEmpty()){
            Button remove = courseTable.getSelectionModel().getSelectedItem().getRemove();
            remove.setOnAction(event -> {
                for (CourseDTO course : allCourses) {
                    if (courseTable.getSelectionModel().getSelectedItem().getProgramme().equals(course.getProgramme())){


                        StudentDTO studentDTO = new StudentDTO();
                        studentDTO.setId(txtStudentId.getText());
                        studentDTO.setNic(txtNIC.getText());
                        studentDTO.setName(txtStudentName.getText());
                        studentDTO.setDateOfBirth(txtDateBirth.getText());
                        studentDTO.setGender(txtGender.getText());
                        studentDTO.setParentName(txtPrentName.getText());
                        studentDTO.setMobile(txtMobileNumber.getText());
                        studentDTO.setAddress(txtAddress.getText());
                        studentDTO.setOl_result(txtOLResult.getText());
                        studentDTO.setAl_result(txtAlResult.getText());
                        studentDTO.setEmail(txtEmail.getText());

                        CourseDTO courseDTO = new CourseDTO();
                        courseDTO.setProgrammeId(course.getProgrammeId());
                        courseDTO.setProgramme(course.getProgramme());
                        courseDTO.setDuration(course.getDuration());
                        courseDTO.setFree(course.getFree());

                        boolean b = manageStudentBO.deleteStudentCourse(studentDTO,courseDTO);
                    }
                }
                courseTable.getItems().remove(courseTable.getSelectionModel().getSelectedItem());
            });
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) updateContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/HomePage.fxml"))));
    }
}
