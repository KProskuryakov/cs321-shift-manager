package edu.gmu.cs321.team3.shiftmanager.shifts;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.gmu.cs321.team3.shiftmanager.forms.CreateNewOrgForm;
import edu.gmu.cs321.team3.shiftmanager.forms.EditShiftForm;
import edu.gmu.cs321.team3.shiftmanager.orgs.Org;
import edu.gmu.cs321.team3.shiftmanager.orgs.OrgService;
import edu.gmu.cs321.team3.shiftmanager.users.User;
import edu.gmu.cs321.team3.shiftmanager.users.UserRepository;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class ShiftServiceTest {

    @Autowired
    private OrgService orgService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ShiftRepository shiftRepo;

    @Autowired
    private ShiftSwapRepository shiftSwapRepo;

    @Autowired
    private ShiftService shiftService;

    @BeforeEach
    public void beforeEach() {
        CreateNewOrgForm orgForm = new CreateNewOrgForm();
        orgForm.setName("My New Org");
        orgForm.setInformation("This is a great organization.");

        orgService.registerNewOrg(orgForm, "test@test.com");

        orgService.inviteUserToOrg("test2@test.com", "test@test.com");
        orgService.inviteUserToOrg("test3@test.com", "test@test.com");

        orgService.acceptInvite(orgService.getOrg("test@test.com").getId(), "test2@test.com");
        orgService.acceptInvite(orgService.getOrg("test@test.com").getId(), "test3@test.com");
    }

    @Test
    @Transactional
    public void testNewShift() throws Exception {
        EditShiftForm shiftForm = new EditShiftForm();
        Org org = orgService.getOrg("test@test.com");
        shiftForm.setAttendees(org.getMembers().stream().map(u -> u.getId()).collect(Collectors.toList()));
        shiftService.newShift(shiftForm, "test@test.com");

        assertThat(shiftService.getUpcomingShiftsForOrg(org)).isNotEmpty().contains(shiftRepo.findAll().get(0));
        assertThat(userRepo.findByEmail("test@test.com").getShifts()).containsAll(userRepo.findByEmail("test2@test.com").getShifts()).contains(shiftRepo.findAll().get(0));
        assertThat(shiftService.getUpcomingShiftsForUser("test2@test.com")).containsAll(shiftService.getUpcomingShiftsForUser("test3@test.com")).contains(shiftRepo.findAll().get(0));
    }

    @Test
    @Transactional
    public void testRequestSwap() throws Exception {
        EditShiftForm shiftForm = new EditShiftForm();
        Org org = orgService.getOrg("test@test.com");
        List<User> justManager = new ArrayList<>();
        justManager.add(userRepo.findByEmail("test@test.com"));
        shiftForm.setAttendees(org.getMembers().stream().map(u -> u.getId()).collect(Collectors.toList()));
        shiftService.newShift(shiftForm, "test@test.com");

        EditShiftForm otherShiftForm = new EditShiftForm();
        otherShiftForm.setStartTime(otherShiftForm.getStartTime().plusDays(1));
        otherShiftForm.setEndTime(otherShiftForm.getEndTime().plusDays(1));
    }
    
}