package com.firstspring.springbootdemo.controller;

import com.firstspring.springbootdemo.model.Question;
import com.firstspring.springbootdemo.model.Quiz;
import com.firstspring.springbootdemo.model.QuizAttempt;
import com.firstspring.springbootdemo.service.Questionservice;
import com.firstspring.springbootdemo.service.Quizservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//Create quiz                                           //checked
//Edit quiz details (title, category, description)        //not needed
//To Delete quiz                                           //checked
//View quiz                                             //checked
//Browse quizzes
//Start a quiz                                          //checked
@Controller
@RequestMapping("quizapp")
public class Quizcontroller {
    @Autowired
    private Questionservice questionservice;
    @Autowired
    Quizservice quizservice;
    @PostMapping("/create")
    public Object createQuiz(@RequestParam String quizname,@RequestParam String category ,@RequestParam String description,@RequestParam Integer timelimit){
        return quizservice.createQuiz(quizname,category,description,timelimit);
    }
    @GetMapping("/{id}/select-question")
    public String selectQuestion(@PathVariable Integer id,
                                 Model model) {

        Quiz quiz = quizservice.getQuizById(id);

        List<Question> questions =
                questionservice.getquestionbycategory(
                        quiz.getCategory());

        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);

        return "select-question";
    }
    @PostMapping("/{id}/select-question")
    public String saveQuestions(@PathVariable Integer id,
                                @RequestParam List<Integer> selectedIds) {

        quizservice.saveSelectedQuestions(id, selectedIds);

        return "redirect:/quizapp";
    }
    @GetMapping("/start/{id}")
    public String startQuiz(
            @PathVariable Integer id,
            Authentication authentication,
            Model model) {

        OAuth2User oauth2User =
                (OAuth2User) authentication.getPrincipal();

        String email =
                oauth2User.getAttribute("email");

        QuizAttempt attempt =
                quizservice.startQuiz(id, email);

        Quiz quiz = attempt.getQuiz();

        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", quiz.getQuestions());
        model.addAttribute("attempt", attempt);

        return "startquiz";
    }
    @PostMapping("/submit")
    public String submitQuiz(
            @RequestParam Integer attemptId,
            @RequestParam Map<String, String> answers) {

        quizservice.submitQuiz(attemptId, answers);

        return "redirect:/quizapp/result/" + attemptId;
    }
    @GetMapping("/result/{attemptId}")
    public String showResult(
            @PathVariable Integer attemptId,
            Model model) {

        QuizAttempt attempt =
                quizservice.getAttemptById(attemptId);

        model.addAttribute("attempt", attempt);

        return "result";
    }
}
