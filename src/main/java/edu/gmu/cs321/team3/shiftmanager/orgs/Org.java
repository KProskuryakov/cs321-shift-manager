package edu.gmu.cs321.team3.shiftmanager.orgs;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import edu.gmu.cs321.team3.shiftmanager.shifts.Shift;
import edu.gmu.cs321.team3.shiftmanager.shifts.ShiftSwap;
import edu.gmu.cs321.team3.shiftmanager.users.User;

@Entity
public class Org {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String information;

    @OneToMany(mappedBy = "org", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<User> members = new LinkedHashSet<>();

    @ManyToMany // relation owner
    private Set<User> invitedUsers = new LinkedHashSet<>();;

    @OneToMany(mappedBy = "org", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Shift> schedule = new LinkedHashSet<>();

    @OneToMany(mappedBy = "org", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ShiftSwap> swaps = new LinkedHashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void addMember(User user) {
        this.members.add(user);
        user.setOrg(this);
    }

    public void removeMember(User user) {
        this.members.remove(user);
        user.setOrg(null);
    }

    public Set<User> getInvitedUsers() {
        return invitedUsers;
    }

    public void setInvitedUsers(Set<User> invitedUsers) {
        this.invitedUsers = invitedUsers;
    }

    public Set<Shift> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<Shift> schedule) {
        this.schedule = schedule;
    }

    public Set<ShiftSwap> getSwaps() {
        return swaps;
    }

    public void setSwaps(Set<ShiftSwap> swaps) {
        this.swaps = swaps;
    }
}