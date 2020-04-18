package edu.gmu.cs321.team3.shiftmanager.users;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        // model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult) {
        // userValidator.validate(userForm, bindingResult);

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
