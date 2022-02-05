package dao.custom.impl;

import dao.custom.StudentCoursesDAO;
import dao.custom.StudentDAO;
import entity.Student;
import entity.StudentCourses;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import javax.persistence.Id;
import java.sql.SQLException;
import java.util.List;

public class StudentCoursesDAOImpl implements StudentCoursesDAO {

    @Override
    public void add(StudentCourses studentCourses) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(studentCourses);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(StudentCourses studentCourses) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(studentCourses);

        transaction.commit();
        session.close();
    }


    @Override
    public void update(StudentCourses studentCourses) throws SQLException, ClassNotFoundException {

    }

    @Override
    public StudentCourses search(StudentCourses studentCourses) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public List<StudentCourses> getAll() throws SQLException, ClassNotFoundException {
    return null;
    }



    @Override
    public boolean DeleteAllStudentCourses(String student_id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="DELETE FROM StudentCourses sc where sc.student.id=:studentId";
        Query query = session.createQuery(hql);
        query.setParameter("studentId",student_id);
        if(query.executeUpdate()>0){
            return true;
        }

        transaction.commit();
        session.close();
        return false;
    }
}
