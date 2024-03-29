package dto;

import entity.Course;
import entity.Student;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class StudentCoursesDTO {
    private StudentDTO student;

    private CourseDTO course;

    public StudentCoursesDTO() {
    }

    public StudentCoursesDTO(StudentDTO student, CourseDTO course) {
        this.student = student;
        this.course = course;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}
