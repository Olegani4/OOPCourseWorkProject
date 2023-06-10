package cw.qq.controller.webpages;

import cw.qq.models.User;
import cw.qq.repository.UserRepository;
import cw.qq.resource.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SignInController {
    private final UserRepository userRepository;

    public SignInController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * GET endpoint to render the sign-in page.
     *
     * @param model the Model object to add attributes for the view
     * @return the name of the view to be rendered
     */
    @GetMapping("/sign-in")
    public String signIn(Model model) {
        model.addAttribute("headerType", "headerNotUser");  // Set the header type attribute for the view
        model.addAttribute("userId", ' ');  // Set the user ID attribute for the view
        return "sign-in";  // Return the name of the view to be rendered
    }

    /**
     * POST endpoint to create a new user account.
     *
     * @param userRequest the UserRequest object containing user information
     * @return the ResponseEntity with the created User object
     */
    @PostMapping("/sign-in")
    public ResponseEntity<User> createQuiz(@RequestBody UserRequest userRequest) {
        var user = new User();
        user.setLogin(userRequest.getLogin());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setImage("/static/media/user-photo-default.svg");  // Set the default user image

        return ResponseEntity.status(201).body(this.userRepository.save(user));
    }
}
