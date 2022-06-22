package com.elca.internship.hibernate;

import com.elca.internship.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
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
            // start a transaction
            session.beginTransaction();
            // query student
            List<Student> theStudents = session.createQuery("from Student").getResultList();
            // display the students
            displayStudents(theStudents);
            System.out.println("Query all student done!");

            // query students: lastName = "Long"
            theStudents = session.createQuery("from Student s where s.lastName='Cat'").getResultList();
            displayStudents(theStudents);
            System.out.println("Query a specified student done!");

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
