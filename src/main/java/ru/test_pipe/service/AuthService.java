package ru.test_pipe.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.test_pipe.model.LoginRequest;
import ru.test_pipe.model.User;

@Service
public class AuthService {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345";

    public Optional<User> authenticate(LoginRequest loginRequest) {
        if (loginRequest == null) {
            return Optional.empty();
        }

        if (USERNAME.equals(loginRequest.getUsername()) && PASSWORD.equals(loginRequest.getPassword())) {
            return Optional.of(new User(USERNAME, "Администратор"));
        }

        return Optional.empty();
    }
}
