package edu.gmu.cs321.team3.shiftmanager.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import edu.gmu.cs321.team3.shiftmanager.users.UniqueEmailConstraint;

public class CreateAccountForm {

    @NotEmpty
    private String fname;

    @NotEmpty
    private String lname;

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]{1,256})@([a-zA-Z0-9_\\-\\.]{1,256})\\.([a-zA-Z]{1,20})$", message = "Must be valid email")
    @UniqueEmailConstraint
    private String email;

    @Size(min = 8, max = 64)
    private String password;

    @Size(min = 8, max = 64)
    private String reEnteredPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}