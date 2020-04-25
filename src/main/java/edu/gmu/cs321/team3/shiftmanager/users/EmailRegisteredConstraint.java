package edu.gmu.cs321.team3.shiftmanager.users;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailRegisteredValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailRegisteredConstraint {
    String message() default "That user does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
