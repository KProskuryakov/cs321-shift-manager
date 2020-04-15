package edu.gmu.cs321.team3.shiftmanager.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

	@Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

	@GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
	}

	@PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.registerNewUser(userForm);
        System.out.println("User: " + userForm.getEmail());

        return "login";
	}

}
