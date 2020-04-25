package edu.gmu.cs321.team3.shiftmanager.forms;

import edu.gmu.cs321.team3.shiftmanager.users.EmailRegisteredConstraint;

public class InviteToOrgForm {

    @EmailRegisteredConstraint
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
