package com.firstspring.springbootdemo.service;

import com.firstspring.springbootdemo.dao.Questiondao;
import com.firstspring.springbootdemo.dao.Quizdao;
import com.firstspring.springbootdemo.model.Question;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Questionservice {
    @Autowired
    Questiondao questiondao;
    @Autowired
    Quizdao quizdao;

    public List<Question> getallquestions() {
        return questiondao.findAll();
    }

    public List<Question> getquestionbycategory(String category) {
       return questiondao.findByCategory(category);
    }

    public String addservice(Question question) {
        questiondao.save(question);
        return "saved successfully";
    }

    public String deleteservice(int id) {
        quizdao.removeQuestionFromQuiz(id);
        questiondao.deleteById(id);
        return "deleted successfully";
    }
}
