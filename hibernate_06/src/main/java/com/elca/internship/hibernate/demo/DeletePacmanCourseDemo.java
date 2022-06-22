package com.elca.internship.hibernate.demo;

import com.elca.internship.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeletePacmanCourseDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try{
            // start a transaction
            session.beginTransaction();

            // get the pacman course from db
            int theCourseId = 10;
            Course course = session.get(Course.class, theCourseId);

            // delete the course from db
            System.out.println("Deleting course: " + course);
            session.delete(course);
            System.out.println("Deleted course...gitn");
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
