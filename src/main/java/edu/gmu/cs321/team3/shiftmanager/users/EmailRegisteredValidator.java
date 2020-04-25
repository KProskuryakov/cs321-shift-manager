package edu.gmu.cs321.team3.shiftmanager.users;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailRegisteredValidator implements ConstraintValidator<EmailRegisteredConstraint, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.isEmailUnique(email);
    }

}
