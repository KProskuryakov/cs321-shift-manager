package edu.gmu.cs321.team3.shiftmanager.config;
import edu.gmu.cs321.team3.shiftmanager.users.User;

public interface MyUserService {
        void save(User user);
    
        User findByEmail(String email);
        
}