package edu.gmu.cs321.team3.shiftmanager.users;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

    @NotEmpty
    private String name;

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]{1,256})@([a-zA-Z0-9_\\-\\.]{1,256})\\.([a-zA-Z]{1,20})$", message = "Must be valid email")
    @UniqueEmailConstraint
    private String email;

    @Size(min = 8, max = 64)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}