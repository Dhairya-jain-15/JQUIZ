package com.firstspring.springbootdemo.service;

import com.firstspring.springbootdemo.dao.*;
import com.firstspring.springbootdemo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class Quizservice {

    @Autowired
    Quizdao quizdao;

    @Autowired
    Questiondao questiondao;

    //for attempt function
    @Autowired
    QuizAttemptRepository quizAttemptRepository;

    @Autowired
    AttemptAnswerRepository attemptAnswerRepository;
    @Autowired
    Userdao userdao;

    public Quiz getQuizById(Integer id) {
        return quizdao.findById(id).orElseThrow();
    }

    public void saveSelectedQuestions(Integer quizId, List<Integer> selectedIds) {

        Quiz quiz = quizdao.findById(quizId).orElseThrow();
        List<Question> questions = questiondao.findAllById(selectedIds);
        quiz.setQuestions(questions);
        quizdao.save(quiz);
    }

    public Object createQuiz(String quizname, String category, String description, Integer timelimit) {
        Quiz quiz = new Quiz();
        quiz.setQuizname(quizname);
        quiz.setCategory(category);
        quiz.setDescription(description);
        quiz.setTime(timelimit);
        quizdao.save(quiz);
        return quiz;
    }

    public Quiz addservice(Quiz quiz) {
        quizdao.save(quiz);
        return quiz;
    }

    public List<Quiz> getAllQuizzes() {
        return quizdao.findAll();
    }

    public String deleteservice(int id) {
        quizdao.removeQuestionFromQuiz(id);
        quizdao.deleteById(id);
        return "deleted quiz";
    }

    //onclick start attempt
    public QuizAttempt startQuiz(Integer quizId, String email) {
        // Find quiz
        Quiz quiz = quizdao.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        // Find logged-in user
        User user = userdao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create attempt
        QuizAttempt attempt = new QuizAttempt();

        attempt.setQuiz(quiz);
        attempt.setUser(user);

        // Start time
        LocalDateTime startTime = LocalDateTime.now();
        attempt.setStartTime(startTime);

        // End time = start time + quiz duration
        LocalDateTime endTime =
                startTime.plusMinutes(quiz.getTime());

        attempt.setEndTime(endTime);

        // Initial values
        attempt.setScore(0);
        attempt.setTotalQuestions(quiz.getQuestions().size());
        attempt.setStatus("IN_PROGRESS");

        // Save attempt
        return quizAttemptRepository.save(attempt);
    }

    public void submitQuiz(Integer attemptId, Map<String, String> answers) {

        QuizAttempt attempt =
                quizAttemptRepository.findById(attemptId)
                        .orElseThrow(() ->
                                new RuntimeException("Attempt not found"));

        if (LocalDateTime.now().isAfter(attempt.getEndTime())) {
            attempt.setStatus("EXPIRED");
            quizAttemptRepository.save(attempt);
            return;
        }
        Quiz quiz = attempt.getQuiz();

        int score = 0;

        for (Question question : quiz.getQuestions()) {

            String key = "question_" + question.getId();

            String selectedAnswer = answers.get(key);

            if (selectedAnswer == null) {
                continue;
            }

            boolean correct =
                    selectedAnswer.equals(question.getRight_answer());

            if (correct) {
                score++;
            }

            AttemptAnswer attemptAnswer = new AttemptAnswer();

            attemptAnswer.setAttempt(attempt);
            attemptAnswer.setQuestion(question);
            attemptAnswer.setSelectedAnswer(selectedAnswer);
            attemptAnswer.setCorrect(correct);

            attemptAnswerRepository.save(attemptAnswer);
        }

        attempt.setScore(score);
        attempt.setStatus("COMPLETED");
        attempt.setEndTime(LocalDateTime.now());

        quizAttemptRepository.save(attempt);
    }

    public QuizAttempt getAttemptById(Integer attemptId) {
        return quizAttemptRepository.findById(attemptId)
                .orElseThrow(() ->
                        new RuntimeException("Attempt not found"));

    }
}