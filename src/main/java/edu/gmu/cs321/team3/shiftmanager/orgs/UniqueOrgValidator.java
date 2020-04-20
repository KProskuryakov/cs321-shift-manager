package edu.gmu.cs321.team3.shiftmanager.orgs;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniqueOrgValidator implements ConstraintValidator<UniqueOrgConstraint, String> {

    @Autowired
    private OrgService orgService;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return orgService.isNameUnique(name);
    }

}
