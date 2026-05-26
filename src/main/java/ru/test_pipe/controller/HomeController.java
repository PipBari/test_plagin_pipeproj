package ru.test_pipe.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.test_pipe.model.User;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "home";
    }
}
