package dao.custom.impl;

import dao.custom.QueryDAO;
import dto.CourseDTO;
import dto.CustomDTO;
import dto.StudentDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<StudentDTO> getStudentFromCourseId(String id) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="select s.id,s.nic,s.name,s.dateOfBirth,s.gender,s.parentName,s.mobile,s.address,s.ol_result,s.al_result,s.email from Student s inner join StudentCourses sc on s.id=sc.student.id where sc.course.id=:courseId";
        Query query = session.createQuery(hql);
        query.setParameter("courseId",id);
        List<Object[]> list = query.list();


        List<StudentDTO> customDTOS=new ArrayList<>();
        for (Object[] objects : list) {
            customDTOS.add(new StudentDTO(String.valueOf(objects[0]),String.valueOf(objects[1]),String.valueOf(objects[2]),String.valueOf(objects[3]),String.valueOf(objects[4]),String.valueOf(objects[5]),String.valueOf(objects[6]),String.valueOf(objects[7]),String.valueOf(objects[8]),String.valueOf(objects[9]),String.valueOf(objects[10])));
        }

        for (StudentDTO customDTO : customDTOS) {
            System.out.println(customDTO.toString());
        }

        transaction.commit();
        session.close();

        return customDTOS;
    }

    @Override
    public List<CustomDTO> getCourseFromCourseId(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="select c.id,c.programme from Course c inner join StudentCourses sc on c.id=sc.course.id WHERE sc.student.id=:studentId";
        Query query = session.createQuery(hql);
        query.setParameter("studentId",id);

        List<Object[]> list = query.list();
        List<CustomDTO> customDTOList=new ArrayList<>();
        for (Object[] objects : list) {
            customDTOList.add(new CustomDTO(String.valueOf(objects[0]),String.valueOf(objects[1])));
        }




        transaction.commit();
        session.close();

        return customDTOList;

    }
}
