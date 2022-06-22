package com.elca.internship.hibernate.demo;

import com.elca.internship.hibernate.entity.Instructor;
import com.elca.internship.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
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

            // get instructor by primary key / id
            int theId = 4;
            Instructor tempInstructor = session.get(Instructor.class, theId);
            System.out.println("Found instructor: " + tempInstructor);
            // delete the instructors
            if( tempInstructor != null){
                System.out.println("Deleting: " + tempInstructor);
                session.delete(tempInstructor);
            }

            // commit transaction
            session.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
