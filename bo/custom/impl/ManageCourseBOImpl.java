package bo.custom.impl;

import bo.custom.ManageCourseBO;
import bo.custom.MangeStudentBO;
import dao.DAOFactory;
import dao.custom.CourseDAO;
import dto.CourseDTO;
import dto.StudentDTO;
import entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageCourseBOImpl implements ManageCourseBO {
    CourseDAO courseDAO= (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Course);

    @Override
    public void addCourse(CourseDTO courseDTO) {
        try {
            courseDAO.add(new Course(courseDTO.getProgrammeId(),courseDTO.getProgramme(),courseDTO.getDuration(),courseDTO.getFree()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean ifExistsCourse(String id) {
        return courseDAO.ifExistCourse(id);
    }

    @Override
    public List<CourseDTO> getAllCourse() {

        try {
            List<Course>all=courseDAO.getAll();
            List<CourseDTO>allCourse=new ArrayList<>();

            for (Course course : all){
                allCourse.add(new CourseDTO(course.getProgrammeId(),course.getProgramme(),course.getDuration(),course.getFree()));
            }
            return allCourse;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCourse(CourseDTO courseDTO) {
        try {
            courseDAO.update(new Course(courseDTO.getProgrammeId(),courseDTO.getProgramme(),courseDTO.getDuration(),courseDTO.getFree()));



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteCourse(String id) {

        return false;
    }

}
