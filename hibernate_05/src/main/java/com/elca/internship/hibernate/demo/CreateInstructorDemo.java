package com.elca.internship.hibernate.demo;

import com.elca.internship.hibernate.entity.Course;
import com.elca.internship.hibernate.entity.Instructor;
import com.elca.internship.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
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
            // create the objects
            Instructor tempInstructor = new Instructor("Thai Son", "Nguyen", "ntson@gmail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com"
                                                                            ,"video games");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            // commit transaction
            session.getTransaction().commit();
        } catch (Exception ex){

            ex.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
    }
}
