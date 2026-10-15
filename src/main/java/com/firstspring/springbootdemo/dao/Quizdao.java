package com.firstspring.springbootdemo.dao;

import com.firstspring.springbootdemo.model.Quiz;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface Quizdao extends JpaRepository<Quiz,Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM quiz_questions WHERE questions_id = :id",
            nativeQuery = true
    )
    void removeQuestionFromQuiz(@Param("id") int id);

}
