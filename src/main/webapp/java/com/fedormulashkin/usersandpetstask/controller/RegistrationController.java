package com.fedormulashkin.usersandpetstask.controller;

import com.fedormulashkin.usersandpetstask.entity.User;
import com.fedormulashkin.usersandpetstask.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для регистрации и проверки незанятых username
 */
@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public String createUser(@RequestBody User user) {
        System.out.println("create user");
        userService.saveUser(user);
        return "User successfully created";
    }

    @GetMapping("/check")
    public String checkUsername(@RequestBody User user) {
        return userService.checkUsername(user.getUsername());
    }
}
