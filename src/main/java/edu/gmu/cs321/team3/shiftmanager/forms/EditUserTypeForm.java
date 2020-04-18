package edu.gmu.cs321.team3.shiftmanager.forms;

import edu.gmu.cs321.team3.shiftmanager.users.Role;

public class EditUserTypeForm {

    private String userEmail;
    private Role role;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
