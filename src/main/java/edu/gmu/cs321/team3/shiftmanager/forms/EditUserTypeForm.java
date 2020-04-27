package edu.gmu.cs321.team3.shiftmanager.forms;

import edu.gmu.cs321.team3.shiftmanager.users.Role;

public class EditUserTypeForm {

    private long userId;
    private Role role;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
