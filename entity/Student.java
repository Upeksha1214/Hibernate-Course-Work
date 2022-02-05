package entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Student")
public class Student {
    @Id
    @Column(name="student_Id")
    private String id;
    private String nic;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String parentName;
    private String mobile;
    private String address;
    private String ol_result;
    private String al_result;
    private String email;


    @OneToMany(mappedBy ="student",cascade = CascadeType.ALL)
    private Set<StudentCourses> studentCourses=new HashSet<>();

    public Student() {
    }

    public Student(String id, String nic, String name, String dateOfBirth, String gender, String parentName, String mobile, String address, String ol_result, String al_result, String email) {
        this.id = id;
        this.nic = nic;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.parentName = parentName;
        this.mobile = mobile;
        this.address = address;
        this.ol_result = ol_result;
        this.al_result = al_result;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOl_result() {
        return ol_result;
    }

    public void setOl_result(String ol_result) {
        this.ol_result = ol_result;
    }

    public String getAl_result() {
        return al_result;
    }

    public void setAl_result(String al_result) {
        this.al_result = al_result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<StudentCourses> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourses> studentCourses) {
        this.studentCourses = studentCourses;
    }
}
