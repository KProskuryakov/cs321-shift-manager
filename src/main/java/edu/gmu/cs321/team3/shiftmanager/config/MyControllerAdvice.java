package edu.gmu.cs321.team3.shiftmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import edu.gmu.cs321.team3.shiftmanager.users.User;
import edu.gmu.cs321.team3.shiftmanager.users.UserService;

@ControllerAdvice
public class MyControllerAdvice {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User addAttributes(Authentication authentication) {
        if (authentication != null) {
            return userService.userForEmail(authentication.getName());
        }
        return null;

    }
}