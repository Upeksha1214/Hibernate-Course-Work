package entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Student_course")
public class StudentCourses implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_Id")
    private
    Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "programme_Id")
    private
    Course course;

    @CreationTimestamp
    private
    Date registerDate;

    public StudentCourses() {
    }

    public StudentCourses(Student student, Course course) {

        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }


}
