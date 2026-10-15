package com.firstspring.springbootdemo.dao;

import com.firstspring.springbootdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Userdao extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}

