package com.firstspring.springbootdemo.dao;

import com.firstspring.springbootdemo.model.AttemptAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptAnswerRepository extends JpaRepository<AttemptAnswer, Integer> {

}