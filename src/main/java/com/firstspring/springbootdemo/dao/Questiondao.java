package com.firstspring.springbootdemo.dao;

import com.firstspring.springbootdemo.model.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Questiondao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    List<Question> findRandomQuestionsByCategory(String category, Integer numQ);

        @Query(value="SELECT * from question q where q.category= :category order by RAND() limit :numQ", nativeQuery = true)
        List<Question> findRandomQuestionByCategory(String category,int numQ);

}
