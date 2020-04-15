package edu.gmu.cs321.team3.shiftmanager.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserRepository userRepo;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm user = (UserForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail().length() < 5 || user.getEmail().length() > 256) {
            errors.rejectValue("email", "Length.userForm.email", "Email invalid length.");
        }
        if (userRepo.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email", "User with this email already created.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 128) {
            errors.rejectValue("password", "Length.userForm.password", "Password invalid length.");
        }
    }
}