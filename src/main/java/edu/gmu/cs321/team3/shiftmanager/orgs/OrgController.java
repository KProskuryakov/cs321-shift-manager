package edu.gmu.cs321.team3.shiftmanager.orgs;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import edu.gmu.cs321.team3.shiftmanager.forms.CreateNewOrgForm;

@Controller
public class OrgController {

    @Autowired
    private OrgService orgService;

    @GetMapping("/org_registration")
    public String registration(CreateNewOrgForm orgForm) {
        return "registration";
    }

    @PostMapping("/org_registration")
    public String registration(@Valid CreateNewOrgForm orgForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        orgService.registerNewOrg(orgForm);
        System.out.println("Organization: " + orgForm.getName());

        return "";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "TimeAlign_UserDashboard";
    }

}

}