package com.aditya.quizApp.controller;

import com.aditya.quizApp.model.Question;
import com.aditya.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id){
        return questionService.deleteQuestionById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestionById(@PathVariable Integer id, @RequestBody Question question){
        return questionService.updateQuestion(id, question);
    }
}
