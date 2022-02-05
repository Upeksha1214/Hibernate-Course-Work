package dao.custom.impl;

import dao.custom.CourseDAO;
import entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO{
    @Override
    public void add(Course course) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(course);

        transaction.commit();

        session.close();
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void update(Course course) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(course);

        transaction.commit();
        session.close();
    }

    @Override
    public Course search(String s) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Course c where c.programme=:programme";

        Query query=session.createQuery(hql);
        query.setParameter("programme",s);

        List<Course> list = query.list();

        for (Course course:list) {
            return course;
        }

        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public List<Course> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Course";
        Query query = session.createQuery(hql);

        List list = query.list();

        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean ifExistCourse(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, id);
        if (course !=null){
            return true;
        }

        transaction.commit();
        session.close();
        return false;
    }
}
