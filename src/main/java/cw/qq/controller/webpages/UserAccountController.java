package cw.qq.controller.webpages;

import cw.qq.models.User;
import cw.qq.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class UserAccountController {

    private final UserRepository userRepository;

    public UserAccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * GET endpoint to retrieve the user account information.
     *
     * @param model   the Model object to add attributes for the view
     * @param userId  the ID of the user
     * @return the name of the view to be rendered
     */
    @GetMapping("/my-account/{userId}")
    public String userAccountAuth(Model model, @PathVariable String userId) {
        Optional<User> user = this.userRepository.findById(userId);

        if (user.isPresent()) {
            model.addAttribute("headerType", "headerUser");  // Set the header type attribute for the view
            model.addAttribute("userId", userId);  // Set the user ID attribute for the view
            model.addAttribute("userData", user.get());  // Set the user data attribute for the view
        } else {
            return null;  // If the user is not found, return null
        }

        return "user-account";  // Return the name of the view to be rendered
    }

}
