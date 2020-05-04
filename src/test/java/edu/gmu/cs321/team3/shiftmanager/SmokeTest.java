package edu.gmu.cs321.team3.shiftmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.gmu.cs321.team3.shiftmanager.orgs.OrgController;
import edu.gmu.cs321.team3.shiftmanager.users.UserController;

@SpringBootTest
public class SmokeTest {

	@Autowired
    private UserController controller1;
    private OrgController controller2;

	@Test
	public void contexLoads() throws Exception {
        assertThat(controller1).isNotNull();
        assertThat(controller2).isNotNull();
	}
}