package edu.gmu.cs321.team3.shiftmanager.orgs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueOrgValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueOrgConstraint {
    String message() default "Organization already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
