package edu.gmu.cs321.team3.shiftmanager.users;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.gmu.cs321.team3.shiftmanager.forms.CreateAccountForm;


import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Test
    @Transactional
	public void testCreateUser() throws Exception {
        CreateAccountForm userForm = new CreateAccountForm();
        userForm.setFname("Michael");
        userForm.setLname("Scott");
        userForm.setEmail("test1@test.com");
        userForm.setPassword("pass");
        userForm.setReEnteredPassword("pass");

        userService.registerNewUser(userForm);

        assertThat(userRepo.findByEmail("test1@test.com")).isNotNull();
    }


    
    @Test
    @Transactional
	public void testUniqueEmail() throws Exception {
        assertThat(userService.isEmailUnique("test1@test.com"));

        CreateAccountForm userForm = new CreateAccountForm();
        userForm.setFname("Michael");
        userForm.setLname("Scott");
        userForm.setEmail("test1@test.com");
        userForm.setPassword("pass");
        userForm.setReEnteredPassword("pass");

        userService.registerNewUser(userForm);

        assertThat(!(userService.isEmailUnique("test1@test.com")));
    }
    
    @Test
    @Transactional
	public void testEnsureManager() throws Exception {
        

        assertThatCode(() -> { userService.userForEmail("test1@test.com"); }).doesNotThrowAnyException();

    }


}