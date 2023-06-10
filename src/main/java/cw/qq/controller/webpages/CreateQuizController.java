package cw.qq.controller.webpages;

import cw.qq.models.User;
import cw.qq.repository.BackgroundRepository;
import cw.qq.repository.TopicRepository;
import cw.qq.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class CreateQuizController {
    // Dependencies
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final BackgroundRepository backgroundRepository;

    // Constructor injection of dependencies
    public CreateQuizController(UserRepository userRepository, TopicRepository topicRepository, BackgroundRepository backgroundRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.backgroundRepository = backgroundRepository;
    }

    // Handler for GET request to "/create-quiz" or "/create-quiz/"
    @GetMapping({"/create-quiz", "/create-quiz/"})
    public String createQuiz(Model model) {
        // Add model attributes for the view
        model.addAttribute("headerType", "headerNotUser");
        model.addAttribute("userId", ' ');

        // Return the name of the view to render
        return "log-in";
    }

    // Handler for GET request to "/create-quiz/{userId}"
    @GetMapping("/create-quiz/{userId}")
    public String createQuizAuth(Model model, @PathVariable String userId) {
        // Find the user by ID in the repository
        Optional<User> user = this.userRepository.findById(userId);

        // Get all topics and backgrounds from the repositories
        var topics = this.topicRepository.findAll();
        var backgrounds = this.backgroundRepository.findAll();

        // Add model attributes based on user existence
        if (user.isPresent()) {
            model.addAttribute("headerType", "headerUser");
            model.addAttribute("userId", userId);
            model.addAttribute("user", user.get());
            model.addAttribute("topics", topics);
            model.addAttribute("backgrounds", backgrounds);
        } else {
            return null;
        }

        // Return the name of the view to render
        return "quiz-creator";
    }
}
