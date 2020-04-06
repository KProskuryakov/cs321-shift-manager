package edu.gmu.cs321.team3.shiftmanager.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/register")
	public String registrationPage() {
		return "registration";
	}

}