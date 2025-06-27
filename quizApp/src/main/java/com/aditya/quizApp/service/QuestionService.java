package com.aditya.quizApp.service;

import com.aditya.quizApp.dao.QuestionDao;
import com.aditya.quizApp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
       try{
           return new ResponseEntity<>( questionDao.findByCategory(category), HttpStatus.OK);
       } catch (Exception e) {
           e.printStackTrace();
       }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
       try{
           return new ResponseEntity<>( "success", HttpStatus.CREATED);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return new ResponseEntity<>("Failed to add Questions", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
        if (questionDao.existsById(id)) {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question newQuestionData) {
        return questionDao.findById(id).map(question -> {
            question.setQuestionTitle(newQuestionData.getQuestionTitle());
            question.setOption1(newQuestionData.getOption1());
            question.setOption2(newQuestionData.getOption2());
            question.setOption3(newQuestionData.getOption3());
            question.setOption4(newQuestionData.getOption4());
            question.setRightAnswer(newQuestionData.getRightAnswer());
            question.setDifficultyLevel(newQuestionData.getDifficultyLevel());
            question.setCategory(newQuestionData.getCategory());
            questionDao.save(question);
            return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
        }).orElse(new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND));
    }
}
