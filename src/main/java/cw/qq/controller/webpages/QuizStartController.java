package cw.qq.controller.webpages;

import cw.qq.models.Quiz;
import cw.qq.models.User;
import cw.qq.repository.QuizRepository;
import cw.qq.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class QuizStartController {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    public QuizStartController(UserRepository userRepository, QuizRepository quizRepository) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }

    /**
     * Handler for the "/quiz/{quizId}" GET request.
     * Retrieves a quiz based on the provided quiz ID and adds it to the model.
     * Renders the "quiz-start" template.
     *
     * @param model   The model to be used in the view
     * @param quizId  The ID of the quiz
     * @return The name of the view template to render
     */
    @GetMapping({"/quiz/{quizId}", "/quiz/{quizId}/"})
    public String quizStart(Model model, @PathVariable String quizId) {
        Optional<Quiz> quiz = this.quizRepository.findById(quizId);
        model.addAttribute("headerType", "headerNotUser");
        model.addAttribute("userId", ' ');

        if (quiz.isPresent()) {
            model.addAttribute("quiz", quiz.get());
        } else {
            return null; // Return an appropriate response in case the quiz is not found
        }

        return "quiz-start";
    }

    /**
     * Handler for the "/quiz/{quizId}/{userId}" GET request.
     * Retrieves a quiz and user based on the provided quiz ID and user ID.
     * Adds them to the model and renders the "quiz-start" template.
     *
     * @param model   The model to be used in the view
     * @param userId  The ID of the user
     * @param quizId  The ID of the quiz
     * @return The name of the view template to render
     */
    @GetMapping({"/quiz/{quizId}/{userId}", "/quiz/{quizId}/{userId}/"})
    public String quizStartAuth(Model model, @PathVariable String userId, @PathVariable String quizId) {
        Optional<User> user = this.userRepository.findById(userId);
        Optional<Quiz> quiz = this.quizRepository.findById(quizId);

        if (user.isPresent()) {
            model.addAttribute("headerType", "headerUser");
            model.addAttribute("userId", userId);
        } else {
            model.addAttribute("headerType", "headerNotUser");
            model.addAttribute("userId", ' ');
        }

        if (quiz.isPresent()) {
            model.addAttribute("quiz", quiz.get());
        } else {
            return null; // Return an appropriate response in case the quiz is not found
        }

        return "quiz-start";
    }
}
