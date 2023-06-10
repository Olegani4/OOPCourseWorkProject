package cw.qq.controller;

import cw.qq.models.Quiz;
import cw.qq.repository.QuizRepository;
import cw.qq.repository.UserRepository;
import cw.qq.resource.QuizRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuizController {

    private final QuizRepository quizRepository;
    private UserRepository userRepository;

    public QuizController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    /**
     * GET endpoint to retrieve all quizzes.
     *
     * @return ResponseEntity with the list of quizzes and HTTP status 200 (OK)
     */
    @GetMapping("/quizzes-all")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(this.quizRepository.findAll());
    }

    /**
     * POST endpoint to create a new quiz.
     *
     * @param quizRequest the request body containing the quiz details
     * @return ResponseEntity with the created quiz and HTTP status 201 (Created)
     */
    @PostMapping("/quiz")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizRequest quizRequest) {
        var quiz = new Quiz();  // Create a new Quiz instance
        quiz.setTopic_title(quizRequest.getTopic_title());  // Set the topic title from the request
        quiz.setName(quizRequest.getName());  // Set the quiz name from the request
        quiz.setQuestions(quizRequest.getQuestions());  // Set the list of questions from the request
        quiz.setQuestions_num(quizRequest.getQuestions_num());  // Set the number of questions from the request
        quiz.setLikes(quizRequest.getLikes());  // Set the number of likes from the request
        quiz.setAuthor_name(quizRequest.getAuthor_name());  // Set the author name from the request
        quiz.setAuthor_email(quizRequest.getAuthor_email());  // Set the author email from the request
        quiz.setBackground_hex(quizRequest.getBackground_hex());  // Set the background hex color from the request
        quiz.setText_color(quizRequest.getText_color());  // Set the text color from the request
        quiz.setTime_limit_sec(quizRequest.getTime_limit_sec());  // Set the time limit in seconds from the request

        // Save the quiz to the repository and return the response with the created quiz
        return ResponseEntity.status(201).body(this.quizRepository.save(quiz));
    }
}
