package edu.gmu.cs321.team3.shiftmanager.orgs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.gmu.cs321.team3.shiftmanager.forms.CreateNewOrgForm;
import edu.gmu.cs321.team3.shiftmanager.users.Role;
import edu.gmu.cs321.team3.shiftmanager.users.User;
import edu.gmu.cs321.team3.shiftmanager.users.UserRepository;

import static org.assertj.core.api.Assertions.*;

import javax.transaction.Transactional;

@SpringBootTest
public class OrgServiceTest {

    @Autowired
    private OrgService orgService;

    @Autowired
    private OrgRepository orgRepo;

    @Autowired
    private UserRepository userRepo;

    @Test
    @Transactional
    public void testCreateOrg() throws Exception {
        CreateNewOrgForm orgForm = new CreateNewOrgForm();
        orgForm.setName("My New Org");
        orgForm.setInformation("This is a great organization.");

        orgService.registerNewOrg(orgForm, "test@test.com");

        assertThat(orgRepo.findByName("My New Org")).isNotNull();
        assertThat(orgRepo.findByName("My New Org").getMembers()).isNotEmpty();
        assertThat(userRepo.findByEmail("test@test.com").getOrg()).isEqualTo(orgRepo.findByName("My New Org"));
        assertThat(userRepo.findByEmail("test@test.com").getRole()).isEqualTo(Role.MANAGER);
    }

    @Test
    @Transactional
    public void testOrgUnique() throws Exception {
        assertThat(orgService.isNameUnique("My New Org")).isTrue();

        CreateNewOrgForm orgForm = new CreateNewOrgForm();
        orgForm.setName("My New Org");
        orgForm.setInformation("This is a great organization.");

        orgService.registerNewOrg(orgForm, "test@test.com");

        assertThat(orgService.isNameUnique("My New Org")).isFalse();
    }

    @Test
    @Transactional
    public void testGetOrg() throws Exception {
        CreateNewOrgForm orgForm = new CreateNewOrgForm();
        orgForm.setName("My New Org");
        orgForm.setInformation("This is a great organization.");

        orgService.registerNewOrg(orgForm, "test@test.com");

        assertThat(orgService.getOrg("test@test.com")).isEqualTo(orgRepo.findByName("My New Org"));
    }

    @Test
    @Transactional
    public void testInviteUser() throws Exception {
        CreateNewOrgForm orgForm = new CreateNewOrgForm();
        orgForm.setName("My New Org");
        orgForm.setInformation("This is a great organization.");

        orgService.registerNewOrg(orgForm, "test@test.com");

        orgService.inviteUserToOrg("test2@test.com", "test@test.com");

        assertThat(orgRepo.findByName("My New Org").getInvitedUsers()).contains(userRepo.findByEmail("test2@test.com"));
        assertThat(userRepo.findByEmail("test2@test.com").getInvitesFromOrgs()).contains(orgRepo.findByName("My New Org"));
    }

    @Test
    @Transactional
    public void testAcceptDecline() throws Exception {
        CreateNewOrgForm orgForm = new CreateNewOrgForm();
        orgForm.setName("My New Org");
        orgForm.setInformation("This is a great organization.");

        orgService.registerNewOrg(orgForm, "test@test.com");
        long id = orgRepo.findByName("My New Org").getId();

        orgService.inviteUserToOrg("test2@test.com", "test@test.com");

        orgService.acceptInvite(id, "test2@test.com");

        assertThat(orgService.getOrg("test2@test.com")).isEqualTo(orgRepo.findByName("My New Org"));

        orgService.inviteUserToOrg("test3@test.com", "test@test.com");

        orgService.declineInvite(id, "test3@test.com");

        assertThatExceptionOfType(OrgNotFoundException.class).isThrownBy(() -> {
            orgService.getOrg("test3@test.com");
        });
    }

    @Test
    @Transactional
    public void testModify() throws Exception {
        CreateNewOrgForm orgForm = new CreateNewOrgForm();
        orgForm.setName("My New Org");
        orgForm.setInformation("This is a great organization.");

        orgService.registerNewOrg(orgForm, "test@test.com");
        long id = orgRepo.findByName("My New Org").getId();

        orgService.inviteUserToOrg("test2@test.com", "test@test.com");

        orgService.acceptInvite(id, "test2@test.com");

        assertThat(userRepo.findByEmail("test2@test.com").getRole()).isEqualTo(Role.EMPLOYEE);

        orgService.modifyUserRole(userRepo.findByEmail("test2@test.com").getId(), Role.SHIFTLEAD);

        assertThat(userRepo.findByEmail("test2@test.com").getRole()).isEqualTo(Role.SHIFTLEAD);
    }

}