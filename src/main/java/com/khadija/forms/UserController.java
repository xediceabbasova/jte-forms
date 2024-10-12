package com.khadija.forms;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/save")
    public String saveUser(@Valid User user, Model model) {
        userRepository.save(user);
        model.addAttribute("message", "User information was saved succesfully!");
        return "index";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(MethodArgumentNotValidException ex, Model model) {
        User user = (User) ex.getBindingResult().getTarget();
        model.addAttribute("user", user);
        model.addAttribute("error", "Please fill out all of the required fields");
        log.info("User Validation failed for {}", user);
        return "index";
    }
}
