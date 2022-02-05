package dao.custom;

import dao.CrudDAO;
import entity.Course;

public interface CourseDAO extends CrudDAO <Course,String> {
    boolean ifExistCourse(String id);
}
