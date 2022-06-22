package com.elca.internship.hibernate;

import com.elca.internship.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
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
            var stu1 = new Student("Thai Son", "Nguyen", "soukechan0903@gmail.com");
            var stu2 = new Student("Thai Duy", "Nguyen", "soukechan0903@gmail.com");
            var stu3 = new Student("Thai Bao", "Nguyen", "soukechan0903@gmail.com");
            // start a transaction
            session.beginTransaction();
            // save the student
            System.out.println("Saving the student...");
            session.save(stu1);
            session.save(stu2);
            session.save(stu3);
            // commit transaction
            session.getTransaction().commit();
        }finally {
            factory.close();
        }
    }
}
