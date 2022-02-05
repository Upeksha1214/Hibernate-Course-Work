package bo.custom;

import bo.SuperBo;
import dto.CourseDTO;

import java.util.List;

public interface ManageCourseBO extends SuperBo {
    void addCourse(CourseDTO courseDTO);
    boolean ifExistsCourse(String id);
    List<CourseDTO>getAllCourse();
    void updateCourse(CourseDTO courseDTO);
    boolean deleteCourse(String id);
}
