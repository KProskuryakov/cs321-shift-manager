package edu.gmu.cs321.team3.shiftmanager.users;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import edu.gmu.cs321.team3.shiftmanager.forms.CreateAccountForm;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(@ModelAttribute("userForm") CreateAccountForm createAccountForm) {
        return "TimeAlign_CreateNewAccount";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userForm") CreateAccountForm createAccountForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "TimeAlign_CreateNewAccount";
        }

        userService.registerNewUser(createAccountForm);
        System.out.println("User: " + createAccountForm.getEmail());

        return "TimeAlign_SignIn";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "TimeAlign_UserDashboard";
    }

}
