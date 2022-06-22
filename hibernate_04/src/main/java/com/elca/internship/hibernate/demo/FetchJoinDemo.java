package com.elca.internship.hibernate.demo;

import com.elca.internship.hibernate.entity.Course;
import com.elca.internship.hibernate.entity.Instructor;
import com.elca.internship.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try{
            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            int theId = 1;

            Query<Instructor> query = session.createQuery("select i from Instructor i"
                                                            + " JOIN FETCH i.courses"
                                                            + " where i.id=:theInstructorId",
                                                            Instructor.class);
            // set parameter on query
            query.setParameter("theInstructorId", theId);
            // execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();


            System.out.println("Instructor: " + tempInstructor);
            System.out.println("Courses: " + tempInstructor.getCourses());

            // commit transaction
            session.getTransaction().commit();
            // close the connection
            session.close();
            System.out.println("---------- The session is now closed! ----------");
            System.out.println("Courses: " + tempInstructor.getCourses());

            // get course for the instructor

        } catch (Exception ex){

            ex.printStackTrace();
        }finally {
            factory.close();
        }
    }
}
