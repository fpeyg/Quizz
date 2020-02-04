package com.a84.quizz.quizz.m;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserDAO extends JpaRepository<User, Integer> {
    boolean existsByName(String name);
    List<User> findByName(String name);
}

