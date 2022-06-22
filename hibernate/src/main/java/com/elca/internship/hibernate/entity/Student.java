package com.elca.internship.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="first_name")
    private String firstName;
    
    private String lastName;
    private String email;

}
