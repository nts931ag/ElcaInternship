package com.elca.internship.hibernate.demo;

import com.elca.internship.hibernate.entity.Course;
import com.elca.internship.hibernate.entity.Instructor;
import com.elca.internship.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {
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

            // get a course
            int theId = 12;
            Course tempCourse = session.get(Course.class, theId);

            // delete course
            System.out.println("Deleting course: " + tempCourse);
            session.delete(tempCourse);
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
