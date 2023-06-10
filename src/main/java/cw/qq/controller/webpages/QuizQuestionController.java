package cw.qq.controller.webpages;

import cw.qq.models.Quiz;
import cw.qq.models.User;
import cw.qq.repository.QuizRepository;
import cw.qq.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;
import java.util.Optional;

@Controller
public class QuizQuestionController {
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    public QuizQuestionController(UserRepository userRepository, QuizRepository quizRepository) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }

    /**
     * Handler for the "/quiz/{quizId}/{name}/{questionInd}/{score}" GET request.
     * Retrieves a quiz based on the provided quiz ID and adds relevant information to the model.
     * Renders the appropriate quiz question template based on the question type.
     * Handles the end of the quiz by redirecting to the "quiz-end" template.
     *
     * @param model        The model to be used in the view
     * @param quizId       The ID of the quiz
     * @param name         The name of the user taking the quiz
     * @param questionInd  The index of the current question
     * @param score        The score obtained so far
     * @return The name of the view template to render
     */
    @GetMapping({"/quiz/{quizId}/{name}/{questionInd}/{score}", "/quiz/{quizId}/{name}/{questionInd}/{score}/"})
    public String quizQuestion(Model model, @PathVariable String quizId, @PathVariable String name, @PathVariable String questionInd, @PathVariable String score) {
        Optional<Quiz> quiz = this.quizRepository.findById(quizId);
        model.addAttribute("headerType", "headerNotUser");
        model.addAttribute("userId", ' ');

        try {
            if (quiz.isPresent()) {
                model.addAttribute("quiz", quiz.get());
                model.addAttribute("questionCount", Integer.parseInt(questionInd) + 1);
                model.addAttribute("questionNum", quiz.get().getQuestions_num());
                model.addAttribute("question", quiz.get().getQuestions().get(Integer.parseInt(questionInd)).getContent());
                model.addAttribute("choiceOfAnswers", quiz.get().getQuestions().get(Integer.parseInt(questionInd)).getChoice_of_answers());
                model.addAttribute("duration", quiz.get().getTime_limit_sec());
                model.addAttribute("correctAnswer", quiz.get().getQuestions().get(Integer.parseInt(questionInd)).getAnswer());
            } else {
                return null; // Return an appropriate response in case the quiz is not found
            }

            if (Objects.equals(quiz.get().getQuestions().get(Integer.parseInt(questionInd)).getType(), "True/False")) {
                return "quiz-question2"; // Render the "quiz-question2" template for True/False questions
            }

            return "quiz-question"; // Render the "quiz-question" template for other types of questions

        } catch (Exception e) {
            model.addAttribute("name", name);
            model.addAttribute("score", score);
            return "quiz-end"; // Redirect to the "quiz-end" template when the quiz ends
        }
    }

    /**
     * Handler for the "/quiz/{quizId}/{userId}/{name}/{questionInd}/{score}" GET request.
     * Retrieves a quiz and user based on the provided quiz ID and user ID.
     * Adds relevant information to the model and renders the appropriate quiz question template.
     * Handles the end of the quiz by redirecting to the "quiz-end" template.
     *
     * @param model        The model to be used in the view
     * @param userId       The ID of the user
     * @param quizId       The ID of the quiz
     * @param name         The name of the user taking the quiz
     * @param questionInd  The index of the current question
     * @param score        The score obtained so far
     * @return The name of the view template to render
     */
    @GetMapping("/quiz/{quizId}/{userId}/{name}/{questionInd}/{score}")
    public String quizQuestionAuth(Model model, @PathVariable String userId, @PathVariable String quizId, @PathVariable String name, @PathVariable String questionInd, @PathVariable String score) {
        Optional<User> user = this.userRepository.findById(userId);
        Optional<Quiz> quiz = this.quizRepository.findById(quizId);

        if (user.isPresent()) {
            model.addAttribute("headerType", "headerUser");
            model.addAttribute("userId", userId);
        } else {
            model.addAttribute("headerType", "headerNotUser");
            model.addAttribute("userId", ' ');
        }

        try {
            if (quiz.isPresent()) {
                model.addAttribute("quiz", quiz.get());
                model.addAttribute("questionCount", Integer.parseInt(questionInd) + 1);
                model.addAttribute("questionNum", quiz.get().getQuestions_num());
                model.addAttribute("question", quiz.get().getQuestions().get(Integer.parseInt(questionInd)).getContent());
                model.addAttribute("choiceOfAnswers", quiz.get().getQuestions().get(Integer.parseInt(questionInd)).getChoice_of_answers());
                model.addAttribute("duration", quiz.get().getTime_limit_sec());
                model.addAttribute("correctAnswer", quiz.get().getQuestions().get(Integer.parseInt(questionInd)).getAnswer());
            } else {
                return null; // Return an appropriate response in case the quiz is not found
            }

            if (Objects.equals(quiz.get().getQuestions().get(Integer.parseInt(questionInd)).getType(), "True/False")) {
                return "quiz-question2"; // Render the "quiz-question2" template for True/False questions
            }

            return "quiz-question"; // Render the "quiz-question" template for other types of questions

        } catch (Exception e) {
            model.addAttribute("name", name);
            model.addAttribute("score", score);
            return "quiz-end"; // Redirect to the "quiz-end" template when the quiz ends
        }
    }
}
