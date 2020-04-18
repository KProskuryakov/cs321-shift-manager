package edu.gmu.cs321.team3.shiftmanager.users;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(UserForm userForm) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.registerNewUser(userForm);
        System.out.println("User: " + userForm.getEmail());

        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "TimeAlign_UserDashboard";
    }

}
