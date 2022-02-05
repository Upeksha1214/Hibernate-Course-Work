package dao.custom;

import dao.SuperDAO;
import dto.CourseDTO;
import dto.CustomDTO;
import dto.StudentDTO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<StudentDTO> getStudentFromCourseId(String id);
    List<CustomDTO> getCourseFromCourseId(String id);

}
