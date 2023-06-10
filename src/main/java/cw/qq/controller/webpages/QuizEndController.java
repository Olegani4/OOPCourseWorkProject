package cw.qq.controller.webpages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizEndController {
    // Handles the GET request for the "/quiz-results" endpoint
    @GetMapping("/quiz-results")
    public String quizEnd(Model model) {
        return "quiz-end"; // Returns the name of the view template to render
    }
}
