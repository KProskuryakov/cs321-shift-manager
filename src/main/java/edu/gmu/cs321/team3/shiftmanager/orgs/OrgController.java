package edu.gmu.cs321.team3.shiftmanager.orgs;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import edu.gmu.cs321.team3.shiftmanager.forms.CreateNewOrgForm;
import edu.gmu.cs321.team3.shiftmanager.forms.InviteToOrgForm;

@Controller
public class OrgController {

    @Autowired
    private OrgService orgService;

    @GetMapping("/org_registration")
    public String registration(@ModelAttribute("orgForm") CreateNewOrgForm orgForm) {
        return "org_registration";
    }

    @PostMapping("/org_registration")
    public String registration(@Valid @ModelAttribute("orgForm") CreateNewOrgForm orgForm, BindingResult bindingResult,
            Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "org_registration";
        }

        orgService.registerNewOrg(orgForm, authentication.getName());
        System.out.println("Organization: " + orgForm.getName());

        return "redirect:/dashboard";
    }

    @GetMapping("/org/{id}")
    public String orgDashboard(@PathVariable("id") long id, Model model) {
        model.addAttribute("org", orgService.getOrg(id));
        return "org";
    }

    @GetMapping("/org/invite")
    public String orgInvitePage(@ModelAttribute("orgForm") InviteToOrgForm orgForm, Model model) {
        return "invite_to_org";
    }

    @PostMapping("/org/invite")
    public String orgInvitePage(@Valid @ModelAttribute("orgForm") InviteToOrgForm orgForm, BindingResult bindingResult,
            Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "invite_to_org";
        }
        long orgId = orgService.inviteUserToOrg(orgForm.getUserEmail(), authentication.getName());
        return "redirect:/org/" + orgId;
    }

}
