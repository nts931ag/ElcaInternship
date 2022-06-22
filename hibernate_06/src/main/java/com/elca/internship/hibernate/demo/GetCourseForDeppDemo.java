package com.elca.internship.hibernate.demo;

import com.elca.internship.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseForDeppDemo {
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

            // get the student depp from db
            int theStudentId = 2;
            Student tempStudent = session.get(Student.class, theStudentId);

            System.out.println("Loaded student: " + tempStudent);
            System.out.println("Course: " + tempStudent.getCourses());

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
