package edu.gmu.cs321.team3.shiftmanager.users;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void registerNewUser(UserForm userForm) {
        User newUser = new User();
        newUser.setName(userForm.getName());
        newUser.setEmail(userForm.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        newUser.setRole(Role.EMPLOYEE);
        userRepo.save(newUser);
    }

    @Transactional
    public boolean isEmailUnique(String email) {
        return userRepo.findByEmail(email) == null;
    }

}
