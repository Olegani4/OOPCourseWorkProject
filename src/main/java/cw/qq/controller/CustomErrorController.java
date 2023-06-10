package cw.qq.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Error handler for handling error requests.
     *
     * @param request the HttpServletRequest object representing the request
     * @param model   the Model object to add attributes for the view
     * @return the name of the view to be rendered
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        model.addAttribute("headerType", "headerNotUser");  // Set the header type attribute for the view
        model.addAttribute("userId", ' ');  // Set the user ID attribute for the view

        return "homepage";  // Return the name of the view to be rendered
    }

}
