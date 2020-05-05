package edu.gmu.cs321.team3.shiftmanager.orgs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.gmu.cs321.team3.shiftmanager.forms.CreateNewOrgForm;
import edu.gmu.cs321.team3.shiftmanager.users.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

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
	public void contexLoads() throws Exception {
        CreateNewOrgForm orgForm = new CreateNewOrgForm();
        orgForm.setName("My New Org");
        orgForm.setInformation("This is a great organization.");

        orgService.registerNewOrg(orgForm, "test@test.com");

        assertThat(orgRepo.findByName("My New Org")).isNotNull();
        assertThat(orgRepo.findByName("My New Org").getMembers()).isNotEmpty();
        assertThat(userRepo.findByEmail("test@test.com").getOrg()).isEqualTo(orgRepo.findByName("My New Org"));
	}
}