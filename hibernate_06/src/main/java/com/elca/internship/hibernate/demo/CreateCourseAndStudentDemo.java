package com.elca.internship.hibernate.demo;

import com.elca.internship.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {
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
            // create a course
            Course tempCourse = new Course("Pacman - How To Score Ten Million Points");
            System.out.println("saving the course");
            session.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse);
            // create the students
            Student stu1 = new Student("John","Doe","john@luv2code.com");
            Student stu2 = new Student("John","Depp","johndepp@luv2code.com");
            Student stu3 = new Student("John","stone","johnstone@luv2code.com");
            // add students to the course
            tempCourse.addStudent(stu1);
            tempCourse.addStudent(stu2);
            tempCourse.addStudent(stu3);
            // save the students
            System.out.println("Saving students...");
            session.save(stu1);
            session.save(stu2);
            session.save(stu3);
            System.out.println("Saved students: " + tempCourse.getStudents());
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
