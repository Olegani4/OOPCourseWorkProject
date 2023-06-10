package cw.qq.controller.webpages;

import cw.qq.models.Quiz;
import cw.qq.models.Topic;
import cw.qq.models.User;
import cw.qq.repository.QuizRepository;
import cw.qq.repository.TopicRepository;
import cw.qq.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class QuizzesController {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    public QuizzesController(QuizRepository quizRepository, UserRepository userRepository, TopicRepository topicRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    /**
     * Retrieve all quizzes from the quiz repository.
     *
     * @return List of Quiz objects
     */
    public List<Quiz> getAllQuizzes() {
        return this.quizRepository.findAll();
    }

    /**
     * Retrieve all topics from the topic repository.
     *
     * @return List of Topic objects
     */
    public List<Topic> getAllTopics() {
        return this.topicRepository.findAll();
    }

    /**
     * Handler for the "/quizzes" and "/quizzes/" GET requests.
     * Retrieves all quizzes and topics, adds them to the model,
     * and renders the "quizzes" template.
     *
     * @param model The model to be used in the view
     * @return The name of the view template to render
     */
    @GetMapping({"/quizzes", "/quizzes/"})
    public String quizzes(Model model) {
        model.addAttribute("quizzes", getAllQuizzes());
        model.addAttribute("topics", getAllTopics());
        model.addAttribute("headerType", "headerNotUser");
        model.addAttribute("userId", ' ');
        return "quizzes";
    }

    /**
     * Handler for the "/quizzes/{userId}" GET request.
     * Retrieves all quizzes and topics, adds them to the model,
     * and renders the "quizzes" template. Also checks if the user
     * exists based on the provided user ID.
     *
     * @param model   The model to be used in the view
     * @param userId  The ID of the user
     * @return The name of the view template to render
     */
    @GetMapping("/quizzes/{userId}")
    public String quizzesAuth(Model model, @PathVariable String userId) {
        model.addAttribute("quizzes", getAllQuizzes());
        model.addAttribute("topics", getAllTopics());

        Optional<User> user = this.userRepository.findById(userId);

        if (user.isPresent()) {
            model.addAttribute("headerType", "headerUser");
            model.addAttribute("userId", userId);
        } else {
            model.addAttribute("headerType", "headerNotUser");
            model.addAttribute("userId", ' ');
        }

        return "quizzes";
    }

    /**
     * Handler for the "/delete-quiz" POST request.
     * Deletes a quiz based on the provided quiz ID.
     *
     * @param requestBody The request body containing the quiz ID
     * @return ResponseEntity with the result of the deletion operation
     */
    @PostMapping("/delete-quiz")
    public ResponseEntity<Quiz> deleteQuiz(@RequestBody Map<String, String> requestBody) {
        String quizId = requestBody.get("quiz_id");
        this.quizRepository.deleteById(quizId);

        // Return a ResponseEntity indicating the success or failure of the deletion
        return null;
    }
}
