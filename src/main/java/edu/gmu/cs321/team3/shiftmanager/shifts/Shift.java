package edu.gmu.cs321.team3.shiftmanager.shifts;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import edu.gmu.cs321.team3.shiftmanager.orgs.Org;
import edu.gmu.cs321.team3.shiftmanager.users.User;

@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp startTime;

    private Timestamp endTime;

    @ManyToMany // relation owner
    private Set<User> attendees = new LinkedHashSet<>();

    @ManyToOne
    private Org org;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Set<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> users) {
        for (User attendee : attendees) {
            attendee.getShifts().remove(this);
        }
        attendees.clear();
        for (User user : users) {
            attendees.add(user);
            user.getShifts().add(this);
        }
    }

    public void addAttendee(User attendee) {
        attendees.add(attendee);
        attendee.getShifts().add(this);
    }

    public void removeAttendee(User attendee) {
        attendees.remove(attendee);
        attendee.getShifts().remove(this);
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Shift))
            return false;

        Shift other = (Shift) o;

        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}