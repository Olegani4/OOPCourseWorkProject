package cw.qq.controller.webpages;

import cw.qq.models.Quiz;
import cw.qq.models.User;
import cw.qq.repository.QuizRepository;
import cw.qq.repository.TopicRepository;
import cw.qq.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserQuizzesController {

    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final QuizRepository quizRepository;

    public UserQuizzesController(UserRepository userRepository, TopicRepository topicRepository, QuizRepository quizRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.quizRepository = quizRepository;
    }

    /**
     * GET endpoint to retrieve quizzes created by a specific user.
     *
     * @param model   the Model object to add attributes for the view
     * @param userId  the ID of the user
     * @return the name of the view to be rendered
     */
    @GetMapping("/my-quizzes/{userId}")
    public String userQuizzesAuth(Model model, @PathVariable String userId) {
        Optional<User> user = this.userRepository.findById(userId);

        if (user.isPresent()) {
            var topics = this.topicRepository.findAll();
            model.addAttribute("topics", topics);

            var quizzes_temp = this.quizRepository.findAll();
            List<Quiz> quizzes = new ArrayList<>();
            for (Quiz quiz : quizzes_temp) {
                if (quiz.getAuthor_email().equals(user.get().getEmail())) {
                    quizzes.add(quiz);
                }
            }
            model.addAttribute("headerType", "headerUser");  // Set the header type attribute for the view
            model.addAttribute("userId", userId);  // Set the user ID attribute for the view
            model.addAttribute("quizzes", quizzes);  // Set the quizzes attribute for the view
        } else {
            return null;  // If the user is not found, return null
        }

        return "my-quizzes";  // Return the name of the view to be rendered
    }
}
