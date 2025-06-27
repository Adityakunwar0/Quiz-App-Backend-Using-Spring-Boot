# Quiz Application (Spring Boot)

This is a Quiz App built using Spring Boot, designed to manage quizzes and questions efficiently. The application supports functionalities for both Admin and User roles.

## Technologies Used
  - Java 17+
  - Spring Boot
  - Spring Data JPA
  - MySQL / H2 (for development/testing)
  - Spring Web
  - Lombok
  - Postman (for API testing)

## Features
  ### Admin:
   - Add new questions
   - Update existing questions using id
   - Delete specific questions using id
   - Create quizzes by selecting questions with categories
   -  Every quiz is assigned a unique ID automatically when created.  
  ### User:
   - View available quizzes
   - Attempt quizzes by answering the listed questions
   - Get score instantly after submission

## API Endpoints

  ### Admin Endpoints 

   - Add a new question
     - Method : POST
     - EndPoint : http://localhost:8080/question/add 

   - Get all questions 
     - Method : GET 
     - EndPoint : http://localhost:8080/question/allQuestions  

   - Update a question by ID
     - Method : PUT    
     - EndPoint :  http://localhost:8080/question/update/{id}  

   - Delete a question by ID
     - Method : DELETE
     - EndPoint : http://localhost:8080/question/delete/{id}

   - Create a new quiz 
     - Method : POST
     - EndPoint : http://localhost:8080/quiz/create?category=java&numQ=5&title=JQuiz

   - Get all quizzes
     - Method : GET 
     - EndPoint : http://localhost:8080/quiz/get/{id}


    
   ### User Endpoints
   
   - View questions of a quiz (options only, not correct answer)
      Method : GET
      EndPoint : http://localhost:8080/quiz/get/{id}

   - Submit quiz and receive score
      Method : POST 
      EndPoint : http://localhost:8080/quiz/submit/{id} 
 
## Summary Of Project

 - Admin adds questions into the system.
 - Admin creates a quiz by choosing a category and number of questions.
 - System generates a quiz with a unique ID.
 - User accesses the quiz using that ID, views questions and options only.
 - After submission:

       System compares user responses with correct answers and also score the marks for corrected answer.
       User receives score.





