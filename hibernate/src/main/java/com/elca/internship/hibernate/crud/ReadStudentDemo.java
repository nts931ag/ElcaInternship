package com.elca.internship.hibernate;

import com.elca.internship.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args) {
        /*var jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
        var user = "root";
        var pass = "09032001Ts!";
        try{
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);
            System.out.println("Connection successful!");
        }catch(Exception ex){
            ex.printStackTrace();
        }*/

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try{
            // use the session object to save java object
            System.out.println("Creating new student object...");
            // create a student object
            var stu = new Student("Trieu Tu Long", "Nguyen", "soukechan0903@gmail.com");
            // start a transaction
            session.beginTransaction();
            // save the student
            System.out.println("Saving the student...");
            session.save(stu);

            // commit transaction
            session.getTransaction().commit();

            // find out the student's id: primary key
            System.out.println("Saved student. Generated id: " + stu.getId());

            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            var getStudent = session.get(Student.class, stu.getId());
            // retrive student based on the id: primary key
            System.out.println("Getting student with id: " + stu.getId());
            System.out.println("Get complete: " + getStudent);
            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }finally {
            factory.close();
        }
    }
}
