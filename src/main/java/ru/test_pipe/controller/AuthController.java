package ru.test_pipe.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.test_pipe.model.LoginRequest;
import ru.test_pipe.model.User;
import ru.test_pipe.service.AuthService;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute LoginRequest loginRequest,
            HttpSession session,
            Model model
    ) {
        return authService.authenticate(loginRequest)
                .map(user -> loginSuccessfully(user, session))
                .orElseGet(() -> loginFailed(model));
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    private String loginSuccessfully(User user, HttpSession session) {
        session.setAttribute("currentUser", user);
        return "redirect:/home";
    }

    private String loginFailed(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        model.addAttribute("error", "Неверный логин или пароль");
        return "login";
    }
}
