package dao;

import dao.custom.impl.CourseDAOImpl;
import dao.custom.impl.QueryDAOImpl;
import dao.custom.impl.StudentCoursesDAOImpl;
import dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
         return (daoFactory==null) ? daoFactory=new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        Student,Course,StudentsCourses,Query
    }


    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case Student:return new StudentDAOImpl();
            case Course:return new CourseDAOImpl();
            case StudentsCourses:return new StudentCoursesDAOImpl();
            case Query:return new QueryDAOImpl();
            default:return null;
        }
    }
}
