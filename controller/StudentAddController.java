package controller;

import bo.BoFactory;
import bo.SuperBo;
import bo.custom.MangeStudentBO;
import com.jfoenix.controls.JFXButton;
import dto.CourseDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import util.Validation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class StudentAddController {
    public TextField txtStudentName;
    public TextField txtNIC;
    public TextField txtDateBirth;
    public TextField txtPrentName;
    public TextField txtAddress;
    public TextField txtEmail;
    public TextField txtAlResult;
    public TextField txtOLResult;
    public TextField txtMetaData;
    public TextField txtMobileNumber;
    public TextField txtStudentId;
    public ComboBox cmbGender;
    public ComboBox cmbCourseName;
    public JFXButton studentAddId;


    MangeStudentBO mangeStudentBO = (MangeStudentBO) BoFactory.getBoFactory().getBO(BoFactory.BoTypes.STUDENT);
    List<CourseDTO> allCourses = mangeStudentBO.getAllCourse();


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^[0-9]{1,4}$");
    Pattern namePattern = Pattern.compile("[A-z .]{1,}");
    Pattern nicPattern = Pattern.compile("^[0-9]{9}[V,v]|[0-9]{12}$");
    Pattern dateOfBirthPattern = Pattern.compile("^[0-9]{4}[-|/][0-9]{2}[-|/][0-9]{2}$");
    Pattern parentNamePattern = Pattern.compile("[A-z .]{1,}");
    Pattern phoneNumberPattern = Pattern.compile("^[0]{1}[7][0|1|2|4|5|6|7|8|0][0-9]{7}$");
    Pattern addressPattern = Pattern.compile("[A-z, [0-9]]{3,}");
    Pattern olPattern = Pattern.compile("^[A-z, .0-9/]*$");
    Pattern alPattern = Pattern.compile("^[A-z, .0-9/]*$");
    Pattern emailPattern = Pattern.compile("[A-z0-9]{3,}[@][a-z]{3,}[.](com|lk|[a-z]{2,})");


    public void initialize(){
        /*ObservableList<String> course= FXCollections.observableArrayList();
        course.add("Motor Mechanics");
        course.add("Quantity Surveying");
        course.add("Electronics");
        course.add("Foreign languages - English");
        course.add("Computer Hardware");
        cmbCourseName.setItems(course);*/

        ObservableList<String> courseNames=FXCollections.observableArrayList();
        for (CourseDTO allCourses : allCourses) {
            courseNames.add(allCourses.getProgramme());
        }
        cmbCourseName.setItems(courseNames);

        ObservableList<String> Gender= FXCollections.observableArrayList();
        Gender.add("Male");
        Gender.add("Female");
        Gender.add("Other");
        cmbGender.setItems(Gender);

        StudentValidations();
    }

    private void StudentValidations() {
        map.put(txtStudentId, idPattern);
        map.put(txtStudentName, namePattern);
        map.put(txtNIC, nicPattern);
        map.put(txtDateBirth, dateOfBirthPattern);
        map.put(txtPrentName, parentNamePattern);
        map.put(txtMobileNumber, phoneNumberPattern);
        map.put(txtAddress, addressPattern);
        map.put(txtOLResult, olPattern);
        map.put(txtAlResult, alPattern);
        map.put(txtEmail, emailPattern);
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
        cmbGender.setValue("");
        cmbCourseName.setValue("");
    }
    public void studentAddOnAction(ActionEvent actionEvent) {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(txtStudentId.getText());
        studentDTO.setName(txtStudentName.getText());
        studentDTO.setNic(txtNIC.getText());
        studentDTO.setDateOfBirth(txtDateBirth.getText());
        studentDTO.setGender((String) cmbGender.getValue());
        studentDTO.setParentName(txtPrentName.getText());
        studentDTO.setMobile(txtMobileNumber.getText());
        studentDTO.setAddress(txtAddress.getText());
        studentDTO.setOl_result(txtOLResult.getText());
        studentDTO.setAl_result(txtAlResult.getText());
        studentDTO.setEmail(txtEmail.getText());

        for (CourseDTO course : allCourses) {
            if (cmbCourseName.getValue().equals(course.getProgramme())){
                studentDTO.setCourseDTO(course);
                break;
            }
        }


        if (txtStudentId.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Fail").showAndWait();
            clear();
        }else {
            mangeStudentBO.addStudent(studentDTO);
            new Alert(Alert.AlertType.CONFIRMATION,"Student Regiter Complete").showAndWait();
            clear();
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtEmail.clear();
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = Validation.validate(map,studentAddId);
        if (keyEvent.getCode()== KeyCode.ENTER) {
            if(response instanceof TextField){
                TextField textField= (TextField) response;
                textField.requestFocus();
            }
        }

    }

}
