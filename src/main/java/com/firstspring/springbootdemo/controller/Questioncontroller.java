package com.firstspring.springbootdemo.controller;
//        Add question          //checked
//        Edit question         //checked
//        Delete question       //checked
//        View a question       //checked
import com.firstspring.springbootdemo.dao.Userdao;
import com.firstspring.springbootdemo.model.Question;
import com.firstspring.springbootdemo.model.Quiz;
import com.firstspring.springbootdemo.service.Questionservice;
import com.firstspring.springbootdemo.service.Quizservice;
import com.firstspring.springbootdemo.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class Questioncontroller {
    @Autowired
    private Questionservice questionservice;
    @Autowired
    private Userservice userservice;
    @Autowired
    private Userdao user;
    @Autowired
    private Quizservice quizservice;
    //developer function

    // Display all questions
    @GetMapping("/quizapp/allquestion")
    public String getAllQuestions(Model model) {
        List<Question> questions = questionservice.getallquestions();
        model.addAttribute("questions", questions);
        return "questionlist";   // questionlist.html
    }

    // Display questions by category
    @GetMapping("/quizapp/category/{category}")
    public String getQuestionsByCategory(@PathVariable String category, Model model) {
        List<Question> questions = questionservice.getquestionbycategory(category);
        model.addAttribute("questions", questions);
        model.addAttribute("category", category);
        return "questionlist";   // questionlist.html //http://localhost:8080/quizapp/category/java
    }

    // Show Add Question Form
    @GetMapping("/quizapp/add")
    public String showAddForm(Model model) {
        model.addAttribute("question", new Question());
        return "add-question";  // add-question.html
    }

    // Save Question
    @PostMapping("/quizapp/add")
    public String addQuestion(@ModelAttribute Question question) {
        questionservice.addservice(question);
        return "redirect:/quizapp/Quizdeveloper";
    }
    //show delete form
    @GetMapping("/quizapp/delete")
    public String showdeleteform(Model model){
        model.addAttribute("question", new Question());
        return "remove-question";
    }
    // delete question
    @PostMapping("/quizapp/delete")
    public String deletequestion(@RequestParam int id){
        System.out.println("deleted");
        questionservice.deleteservice(id);
        return "redirect:/quizapp/Quizdeveloper";
    }
    //show delete quiz
    @GetMapping("/quizapp/deletequiz")
    public String showdeletequiz(Model model){
        model.addAttribute("quiz", new Quiz());
        return "remove-quiz";
    }
    //delete quiz
    @PostMapping("/quizapp/deletequiz")
    public String deletequiz(@RequestParam int id){
        System.out.println("deleted");
        quizservice.deleteservice(id);
        return "redirect:/quizapp/Quizdeveloper";
    }
}