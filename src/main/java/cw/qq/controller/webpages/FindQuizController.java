package cw.qq.controller.webpages;

import cw.qq.models.Quiz;
import cw.qq.models.Topic;
import cw.qq.models.User;
import cw.qq.repository.QuizRepository;
import cw.qq.repository.TopicRepository;
import cw.qq.repository.UserRepository;
import cw.qq.resource.FindQuizRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FindQuizController {
    // Dependencies
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final TopicRepository topicRepository;

    // Variables to store search criteria
    private String desiredQuizName = "";
    private String desiredAuthorName = "";

    // Constructor injection of dependencies
    public FindQuizController(UserRepository userRepository, QuizRepository quizRepository, TopicRepository topicRepository) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.topicRepository = topicRepository;
    }

    // Get all quizzes from the repository
    public List<Quiz> getAllQuizzes() {
        return this.quizRepository.findAll();
    }

    // Get all topics from the repository
    public List<Topic> getAllTopics() {
        return this.topicRepository.findAll();
    }

    // Handler for GET request to "/find-quiz" or "/find-quiz/"
    @GetMapping({"/find-quiz", "/find-quiz/"})
    public String findQuiz(Model model) {
        // Add model attributes for the view
        model.addAttribute("headerType", "headerNotUser");
        model.addAttribute("userId", ' ');

        // Return the name of the view to render
        return "find-quiz";
    }

    // Handler for GET request to "/find-quiz/{userId}"
    @GetMapping("/find-quiz/{userId}")
    public String findQuizAuth(Model model, @PathVariable String userId) {
        // Find the user by ID in the repository
        Optional<User> user = this.userRepository.findById(userId);

        // Add model attributes based on user existence
        if (user.isPresent()) {
            model.addAttribute("headerType", "headerUser");
            model.addAttribute("userId", userId);
        } else {
            model.addAttribute("headerType", "headerNotUser");
            model.addAttribute("userId", ' ');
        }

        // Return the name of the view to render
        return "find-quiz";
    }

    // Handler for GET request to "/found-quiz" or "/found-quiz/"
    @GetMapping({"/found-quiz", "/found-quiz/"})
    public String foundQuiz(Model model) {
        // Add model attributes for the view
        model.addAttribute("headerType", "headerNotUser");
        model.addAttribute("userId", ' ');

        // Get all quizzes
        var quizzes_temp = getAllQuizzes();

        // Filter quizzes based on desired quiz name and author name
        var quizzes = new ArrayList<Quiz>();
        for (var quiz : quizzes_temp) {
            if ((quiz.getName().equals(desiredQuizName) || quiz.getAuthor_name().equals(desiredAuthorName)) && !quizzes.contains(quiz)) {
                quizzes.add(quiz);
            }
        }

        // Add model attributes for the view
        model.addAttribute("topics", getAllTopics());
        model.addAttribute("quizzes", quizzes);

        // Return the name of the view to render
        return "quizzes";
    }

    // Handler for GET request to "/found-quiz/{userId}"
    @GetMapping("/found-quiz/{userId}")
    public String foundQuizAuth(Model model, @PathVariable String userId) {
        // Find the user by ID in the repository
        Optional<User> user = this.userRepository.findById(userId);

        // Get all quizzes
        var quizzes_temp = getAllQuizzes();

        // Filter quizzes based on desired quiz name and author name
        var quizzes = new ArrayList<Quiz>();
        for (var quiz : quizzes_temp) {
            if ((quiz.getName().equals(desiredQuizName) || quiz.getAuthor_name().equals(desiredAuthorName)) && !quizzes.contains(quiz)) {
                quizzes.add(quiz);
            }
        }

        // Add model attributes for the view
        model.addAttribute("topics", getAllTopics());
        model.addAttribute("quizzes", quizzes);

        // Add model attributes based on user existence
        if (user.isPresent()) {
            model.addAttribute("headerType", "headerUser");
            model.addAttribute("userId", userId);
        } else {
            model.addAttribute("headerType", "headerNotUser");
            model.addAttribute("userId", ' ');
        }

        // Return the name of the view to render
        return "quizzes";
    }

    // Handler for POST request to "/find-quiz"
    @PostMapping("/find-quiz")
    public String findQuizPost(@RequestBody FindQuizRequest findQuizRequest) {
        // Set the desired quiz name and author name from the request body
        this.desiredQuizName = findQuizRequest.getQuiz_name();
        this.desiredAuthorName = findQuizRequest.getAuthor_name();

        // Redirect to "/found-quiz"
        return "redirect:/found-quiz";
    }
}
