package com.elca.internship.hibernate;

import com.elca.internship.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UpdateStudentDemo {
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
            int studentId = 7;

            /*// start a transaction
            session.beginTransaction();
            // retrieve student based on the id: primary key
            System.out.println("Getting student with id: " + studentId);

            Student myStudent = session.get(Student.class, studentId);
            myStudent.setFirstName("Thai Duy");
            System.out.println("Get complete: " + myStudent);
            
            // commit transaction
            session.getTransaction().commit();*/

            // start a transaction
            session.beginTransaction();
            // retrieve student based on the id: primary key

            session.createQuery("update Student s set s.firstName = 'soukechan@gmail.com'")
                    .executeUpdate();

            // commit transaction
            session.getTransaction().commit();
        }finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for(Student s : theStudents){
            System.out.println(s);
        }
    }
}
