package com.aditya.quizApp.service;

import com.aditya.quizApp.dao.QuestionDao;
import com.aditya.quizApp.dao.QuizDao;
import com.aditya.quizApp.model.Question;
import com.aditya.quizApp.model.QuestionWrapper;
import com.aditya.quizApp.model.Quiz;
import com.aditya.quizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for(Question q : questionFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

//    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
//        Quiz quiz = quizDao.findById(id).get();
//        List<Question> questions = quiz.getQuestions();
//
//        int right = 0;
//        int i =0;
//        for(Response response : responses){
//            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
//              right++;
//            i++;
//        }
//        return new ResponseEntity<>(right, HttpStatus.OK);
//
//    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quizOptional = quizDao.findById(id);
        if (!quizOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Quiz quiz = quizOptional.get();
        List<Question> questions = quiz.getQuestions();

        // Map question ID to correct answer
        Map<Integer, String> answerMap = new HashMap<>();
        for (Question q : questions) {
            answerMap.put(q.getId(), q.getRightAnswer().trim().toLowerCase());
        }

        int right = 0;
        for (Response response : responses) {
            String submittedAnswer = response.getResponse().trim().toLowerCase();
            String correctAnswer = answerMap.get(response.getId());

            if (correctAnswer != null && correctAnswer.equals(submittedAnswer)) {
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }


}
