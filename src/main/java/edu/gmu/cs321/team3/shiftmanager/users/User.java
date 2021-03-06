package edu.gmu.cs321.team3.shiftmanager.users;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import edu.gmu.cs321.team3.shiftmanager.orgs.Org;
import edu.gmu.cs321.team3.shiftmanager.shifts.Shift;
import edu.gmu.cs321.team3.shiftmanager.shifts.ShiftSwap;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String fname;

    @NotEmpty
    private String lname;

    @Column(unique = true)
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "attendees")
    private Set<Shift> shifts;

    @OneToMany(mappedBy = "requestor")
    private Set<ShiftSwap> requestedSwaps;

    @OneToMany(mappedBy = "receiver")
    private Set<ShiftSwap> receivedSwaps;

    @ManyToOne
    private Org org;

    @ManyToMany(mappedBy = "invitedUsers")
    private Set<Org> invitesFromOrgs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }

    public Set<ShiftSwap> getRequestedSwaps() {
        return requestedSwaps;
    }

    public void setRequestedSwaps(Set<ShiftSwap> requestedSwaps) {
        this.requestedSwaps = requestedSwaps;
    }

    public Set<ShiftSwap> getReceivedSwaps() {
        return receivedSwaps;
    }

    public void setReceivedSwaps(Set<ShiftSwap> receivedSwaps) {
        this.receivedSwaps = receivedSwaps;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
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

    public Set<Org> getInvitesFromOrgs() {
        return invitesFromOrgs;
    }

    public void acceptInvite(Org org) {
        if (invitesFromOrgs.contains(org)) {
            org.addMember(this);
            setRole(Role.EMPLOYEE);
            invitesFromOrgs.remove(org);
            org.getInvitedUsers().remove(this);
        }
    }

    public void declineInvite(Org org) {
        if (invitesFromOrgs.contains(org)) {
            invitesFromOrgs.remove(org);
            org.getInvitedUsers().remove(this);
        }
    }

    @Transient
    public String getTag() {
        return fname + ' ' + lname + " - " + role.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User other = (User) o;

        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}