package bo.custom.impl;

import bo.custom.MangeStudentBO;
import dao.DAOFactory;
import dao.custom.CourseDAO;
import dao.custom.QueryDAO;
import dao.custom.StudentCoursesDAO;
import dao.custom.StudentDAO;
import dto.CourseDTO;
import dto.CustomDTO;
import dto.StudentCoursesDTO;
import dto.StudentDTO;
import entity.Course;
import entity.Student;
import entity.StudentCourses;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageStudentBOImpl implements MangeStudentBO {

    StudentDAO studentDAO=(StudentDAO)DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Student);
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Course);
    StudentCoursesDAO studentCoursesDAO = (StudentCoursesDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.StudentsCourses);
    QueryDAO queryDAO = (QueryDAO)DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Query);


    @Override
    public void addStudent(StudentDTO studentDTO) {

        try {

            Student student=new Student(studentDTO.getId(),studentDTO.getNic(),studentDTO.getName(),
                    studentDTO.getDateOfBirth(),studentDTO.getGender(),studentDTO.getParentName()
                    ,studentDTO.getMobile(),studentDTO.getAddress(),studentDTO.getOl_result(),
                    studentDTO.getAl_result(),studentDTO.getEmail());


            CourseDTO courseDTO = studentDTO.getCourseDTO();
            Course course = new Course(courseDTO.getProgrammeId(), courseDTO.getProgramme(), courseDTO.getDuration(), courseDTO.getFree());

            StudentCourses studentCourses=new StudentCourses();
            studentCourses.setStudent(student);
            studentCourses.setCourse(course);

            studentDAO.add(student);
            studentCoursesDAO.add(studentCourses);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<CourseDTO> getAllCourse() {
        try {
            List<Course> all =courseDAO.getAll();
            List<CourseDTO> allCourse = new ArrayList<>();
            for (Course course : all) {
                allCourse.add(new CourseDTO(course.getProgrammeId(),course.getProgramme(), course.getDuration(), course.getFree()));
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
    public List<StudentDTO> getAllStudent() {
        try {
            List<Student> all = studentDAO.getAll();

            List<StudentDTO>studentDTOList= new ArrayList<>();

            for (Student student:all) {
                studentDTOList.add(new StudentDTO(student.getId(),student.getNic(),student.getName(),student.getDateOfBirth(),
                        student.getGender(),student.getParentName(),student.getMobile(),student.getAddress(),student.getOl_result(),
                        student.getAl_result(),student.getEmail()));
            }
            return studentDTOList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<StudentDTO> getStudentFromCourse(String courseId) {
        return queryDAO.getStudentFromCourseId(courseId);

    }

    @Override
    public List<CustomDTO> getCourseFromStudent(String StudentId) {
        List<CustomDTO> couse = queryDAO.getCourseFromCourseId(StudentId);
          return couse;
    }


    @Override
    public boolean deleteStudentCourse(StudentDTO studentDTO,CourseDTO courseDTO) {
        try {
            Student student = new Student(studentDTO.getId(), studentDTO.getNic(), studentDTO.getName(), studentDTO.getDateOfBirth(), studentDTO.getGender(), studentDTO.getParentName(), studentDTO.getMobile(), studentDTO.getAddress(), studentDTO.getOl_result(), studentDTO.getAl_result(), studentDTO.getEmail());
            Course course = new Course(courseDTO.getProgrammeId(),courseDTO.getProgramme(),courseDTO.getDuration(),courseDTO.getFree());
            StudentCourses studentCourses = new StudentCourses(student,course);
            studentCoursesDAO.delete(studentCourses);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) {
       try {

        boolean b = studentCoursesDAO.DeleteAllStudentCourses(studentDTO.getId());
        if (b) {
            Student student = new Student(studentDTO.getId(), studentDTO.getNic(), studentDTO.getName(), studentDTO.getDateOfBirth(), studentDTO.getGender(), studentDTO.getParentName(), studentDTO.getMobile(), studentDTO.getAddress(), studentDTO.getOl_result(), studentDTO.getAl_result(), studentDTO.getEmail());
            studentDAO.delete(student);
        }



            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        return false;
    }

    @Override
    public void addCourseToStudent(StudentCoursesDTO dto) {

    }

    @Override
    public void updateStudent(StudentDTO studentDTO) {
        try {
            studentDAO.update(new Student(studentDTO.getId(),studentDTO.getNic(),studentDTO.getName(),studentDTO.getDateOfBirth(),studentDTO.getGender(),studentDTO.getParentName(),studentDTO.getMobile(),studentDTO.getAddress(),studentDTO.getOl_result(),studentDTO.getAl_result(),studentDTO.getEmail()));

        } catch (SQLException throwables) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
