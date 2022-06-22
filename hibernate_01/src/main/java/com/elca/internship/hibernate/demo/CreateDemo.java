package com.elca.internship.hibernate.crud;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateDemo {
    public static void main(String[] args) {
        var jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
        var user = "root";
        var pass = "09032001Ts!";
        try{
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);
            System.out.println("Connection successful!");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
