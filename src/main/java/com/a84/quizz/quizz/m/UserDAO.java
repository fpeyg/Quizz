package com.a84.quizz.quizz.m;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
    boolean existsByName(String name);
}
