package com.elca.internship.hibernate.demo;

import com.elca.internship.hibernate.entity.Course;
import com.elca.internship.hibernate.entity.Instructor;
import com.elca.internship.hibernate.entity.InstructorDetail;
import com.elca.internship.hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateCourseAndReviewDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try{
            // start a transaction
            session.beginTransaction();
            // create a course
            Course tempCourse = new Course("Pacman - How To Score One Million Points");
            // add some reviews
            tempCourse.addReview(new Review("Great course ... loved it!"));
            tempCourse.addReview(new Review("Poor course ... hated it!"));
            tempCourse.addReview(new Review("No comment"));
            // save the course...and leverage the cascade all
            System.out.println("Saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());
            session.save(tempCourse);
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
