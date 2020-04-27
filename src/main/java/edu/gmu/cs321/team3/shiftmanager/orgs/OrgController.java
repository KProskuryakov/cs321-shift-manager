package edu.gmu.cs321.team3.shiftmanager.orgs;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import edu.gmu.cs321.team3.shiftmanager.forms.CreateNewOrgForm;
import edu.gmu.cs321.team3.shiftmanager.forms.EditUserTypeForm;
import edu.gmu.cs321.team3.shiftmanager.forms.InviteToOrgForm;
import edu.gmu.cs321.team3.shiftmanager.shifts.ShiftService;
import edu.gmu.cs321.team3.shiftmanager.users.UserService;

@Controller
public class OrgController {

    @Autowired
    private OrgService orgService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShiftService shiftService;

    @GetMapping("/org_registration")
    public String registration(@ModelAttribute("orgForm") CreateNewOrgForm orgForm) {
        return "org_registration";
    }

    @PostMapping("/org_registration")
    public String registration(@Valid @ModelAttribute("orgForm") CreateNewOrgForm orgForm, BindingResult bindingResult,
            Authentication auth) {
        if (bindingResult.hasErrors()) {
            return "org_registration";
        }

        orgService.registerNewOrg(orgForm, auth.getName());

        return "redirect:/dashboard";
    }

    @GetMapping("/org")
    public String orgDashboard(Model model, Authentication auth) {
        Org org = orgService.getOrg(auth.getName());
        model.addAttribute("org", org);
        return "org";
    }

    @GetMapping("/org/invite")
    public String orgInvitePage(@ModelAttribute("orgForm") InviteToOrgForm orgForm, Model model, Authentication auth) {
        userService.ensureManager(auth.getName());
        return "invite_to_org";
    }

    @PostMapping("/org/invite")
    public String orgInvitePage(@Valid @ModelAttribute("orgForm") InviteToOrgForm orgForm, BindingResult bindingResult,
            Authentication auth) {
        if (bindingResult.hasErrors()) {
            return "invite_to_org";
        }
        userService.ensureManager(auth.getName());
        orgService.inviteUserToOrg(orgForm.getUserEmail(), auth.getName());
        return "redirect:/org";
    }

    @GetMapping("/org/{id}/accept")
    public String acceptOrgInvite(@PathVariable("id") long id, Authentication auth) {
        orgService.acceptInvite(id, auth.getName());
        return "redirect:/dashboard";
    }

    @GetMapping("/org/{id}/decline")
    public String declineOrgInvite(@PathVariable("id") long id, Authentication auth) {
        orgService.declineInvite(id, auth.getName());
        return "redirect:/dashboard";
    }

    @GetMapping("/org/roles")
    public String roleChange(@ModelAttribute("rolesForm") EditUserTypeForm rolesForm, Authentication auth) {
        userService.ensureManager(auth.getName());
        return "change_member_role";
    }

    @PostMapping("/org/roles")
    public String roleChange(@Valid @ModelAttribute("rolesForm") EditUserTypeForm rolesForm, BindingResult bindingResult, Authentication auth) {
        if (bindingResult.hasErrors()) {
            return "change_member_role";
        }
        userService.ensureManager(auth.getName());

        // TODO it's possible to change your own role and end up with the org not having a manager. Should be fixed

        orgService.modifyUserRole(rolesForm.getUserId(), rolesForm.getRole());
        return "redirect:/org/";
    }

}
