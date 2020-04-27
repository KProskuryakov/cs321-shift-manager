package edu.gmu.cs321.team3.shiftmanager.shifts;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.gmu.cs321.team3.shiftmanager.forms.EditShiftForm;
import edu.gmu.cs321.team3.shiftmanager.forms.RequestSwapForm;
import edu.gmu.cs321.team3.shiftmanager.orgs.OrgService;

@Controller
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private OrgService orgService;

    @GetMapping("/shifts")
    public String shifts(Model model, Authentication auth) {
        model.addAttribute("shifts", shiftService.getUpcomingShiftsForUser(auth.getName()));
        return "shifts";
    }

    @GetMapping("/org/shifts")
    public String orgShifts(Model model, Authentication auth) {
        model.addAttribute("shifts", shiftService.getUpcomingShiftsForOrg(orgService.getOrg(auth.getName())));
        return "org_shifts";
    }

    @GetMapping("/shift/new")
    public String newShift(@ModelAttribute("shiftForm") EditShiftForm shiftForm) {
        return "shift_new";
    }

    @PostMapping("/shift/new")
    public String newShift(@Valid @ModelAttribute("shiftForm") EditShiftForm shiftForm, BindingResult bindingResult, Authentication auth) {
        if (bindingResult.hasErrors()) {
            return "shift_new";
        }
        shiftService.newShift(shiftForm, auth.getName());
        return "redirect:/org/shifts";
    }

    @GetMapping("/shift/edit/{shiftId}")
    public String editShift(@ModelAttribute("shiftForm") EditShiftForm shiftForm, @PathVariable("shiftId") long id, Model model) {
        model.addAttribute("shiftId", id);
        shiftService.shiftToForm(id, shiftForm);
        return "shift_edit";
    }

    @PostMapping("/shift/edit/{shiftId}")
    public String editShift(@Valid @ModelAttribute("shiftForm") EditShiftForm shiftForm, BindingResult bindingResult, @PathVariable("shiftId") long id, Authentication auth, Model model) {
        model.addAttribute("shiftId", id);
        if (bindingResult.hasErrors()) {
            return "shift_edit";
        }
        shiftService.editShift(shiftForm, auth.getName(), id);
        return "redirect:/org/shifts";
    }

    @GetMapping("/shift/swap/{shiftId}")
    public String requestSwap(@ModelAttribute("shiftForm") RequestSwapForm shiftForm, @PathVariable("shiftId") long id, Model model, Authentication auth) {
        model.addAttribute("wantedShift", shiftService.getShift(id));
        model.addAttribute("shifts", shiftService.getUpcomingShiftsForUser(auth.getName()));
        return "shift_swap";
    }

    @PostMapping("/shift/swap/{shiftId}")
    public String requestSwap(@Valid @ModelAttribute("shiftForm") RequestSwapForm shiftForm, @PathVariable("shiftId") long id, Model model, Authentication auth, BindingResult bindingResult) {
        model.addAttribute("wantedShift", shiftService.getShift(id));
        model.addAttribute("shifts", shiftService.getUpcomingShiftsForUser(auth.getName()));
        if (bindingResult.hasErrors()) {
            return "shift_swap";
        }
        shiftService.requestSwap(shiftForm, id, auth.getName());
        return "redirect:/org/shifts";
    }

}