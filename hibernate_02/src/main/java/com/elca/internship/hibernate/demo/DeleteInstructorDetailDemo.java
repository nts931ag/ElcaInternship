package com.elca.internship.hibernate.demo;

import com.elca.internship.hibernate.entity.Instructor;
import com.elca.internship.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try{
            // start a transaction
            session.beginTransaction();

            // get the instructor detail object
            int theId = 2;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);

            // print the instructor detail
            System.out.println("tempInstructorDetails: " + tempInstructorDetail);
            // print the associated instructor
            System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
            // commit transaction
            session.getTransaction().commit();
            System.out.println("----------Done----------");
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
