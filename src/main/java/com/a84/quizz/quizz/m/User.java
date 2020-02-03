package com.a84.quizz.quizz.m;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    private String name;
    private String hashedPassword;
    private String salt;

}
