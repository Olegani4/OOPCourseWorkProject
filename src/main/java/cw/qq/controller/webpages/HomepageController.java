package cw.qq.controller.webpages;

import cw.qq.models.User;
import cw.qq.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomepageController {

    private final UserRepository userRepository;

    // Constructor injection for UserRepository
    public HomepageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Handles GET requests for the root endpoint "/"
    @GetMapping("/")
    public String homepage(Model model) {
        // Adds attributes to the model for rendering in the HTML template
        model.addAttribute("headerType", "headerNotUser");
        model.addAttribute("userId", ' ');

        // Returns the name of the HTML template to be rendered
        return "homepage";
    }

    // Handles GET requests for "/{userId}" endpoint
    @GetMapping("/{userId}")
    public String homepageAuth(Model model, @PathVariable String userId) {
        // Retrieves the user with the given ID from the UserRepository
        Optional<User> user = this.userRepository.findById(userId);

        if (user.isPresent()) {
            // Adds attributes to the model when the user is found
            model.addAttribute("headerType", "headerUser");
            model.addAttribute("userId", userId);
        } else {
            // Adds attributes to the model when the user is not found
            model.addAttribute("headerType", "headerNotUser");
            model.addAttribute("userId", ' ');
        }

        // Returns the name of the HTML template to be rendered
        return "homepage";
    }
}
