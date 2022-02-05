package bo.custom;

import bo.SuperBo;
import dto.CourseDTO;
import dto.CustomDTO;
import dto.StudentCoursesDTO;
import dto.StudentDTO;
import entity.StudentCourses;

import javax.jnlp.ClipboardService;
import java.util.List;

public interface MangeStudentBO extends SuperBo {
    void addStudent(StudentDTO studentDTO);
    List<CourseDTO> getAllCourse();
    List<StudentDTO>getAllStudent();
    List<StudentDTO>getStudentFromCourse(String courseId);
    List<CustomDTO>getCourseFromStudent(String StudentId);
    boolean deleteStudentCourse(StudentDTO studentDTO,CourseDTO courseDTO);
    boolean deleteStudent(StudentDTO studentDTO);
    void addCourseToStudent(StudentCoursesDTO studentCoursesDTO);
    void updateStudent(StudentDTO studentDTO);


}
