package dao.custom.impl;

import dao.custom.StudentDAO;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public String generateStudentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void add(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Student student) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(student);

        transaction.commit();
        session.close();

    }


    @Override
    public void update(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();

    }

    @Override
    public Student search(Student student) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="from Student ";
        Query query = session.createQuery(hql);
        List<Student> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
