package cw.qq.controller.webpages;

import cw.qq.models.User;
import cw.qq.repository.UserRepository;
import cw.qq.resource.UserRequest;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Data
@Controller
public class LogInController {
    private final UserRepository userRepository;
    private String userId = null;

    // Constructor injection for UserRepository
    public LogInController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Handles GET requests for "/log-in" endpoint
    @GetMapping("/log-in")
    public String logIn() {
        // Returns the name of the HTML template to be rendered
        return "log-in";
    }

    // Handles POST requests for "/log-in" endpoint
    public ModelAndView getLogIn(@RequestBody UserRequest userRequest, RedirectAttributes redirectAttributes) {
        String login = userRequest.getLogin();
        String password = userRequest.getPassword();

        // Iterates over all users in the UserRepository
        for (User user : this.userRepository.findAll()) {
            // Checks if the login and password match
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                this.userId = user.getId();
                break;
            }
        }

        if (this.userId != null) {
            // Adds a flash attribute to be accessed in the redirected page
            redirectAttributes.addFlashAttribute("message", "User found");
            // Redirects to the user's home page based on their ID
            return new ModelAndView("redirect:/" + this.userId);
        } else {
            // Adds a flash attribute to be accessed in the redirected page
            redirectAttributes.addFlashAttribute("message", "User not found");
            // Redirects to the sign-in page
            return new ModelAndView("redirect:/sign-in");
        }
    }

    // Handles GET requests for "/log-in-id" endpoint
    @GetMapping("/log-in-id")
    public String sendId(Model model) {
        // Adds the userId attribute to the model for rendering in the HTML template
        model.addAttribute("userId", this.userId);
        // Returns the name of the HTML template to be rendered
        return "temp/log-in-id";
    }
}
