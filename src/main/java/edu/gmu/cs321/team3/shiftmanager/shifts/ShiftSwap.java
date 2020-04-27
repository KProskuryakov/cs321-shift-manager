package edu.gmu.cs321.team3.shiftmanager.shifts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import edu.gmu.cs321.team3.shiftmanager.orgs.Org;
import edu.gmu.cs321.team3.shiftmanager.users.User;

@Entity
public class ShiftSwap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String message;

    private Boolean accepted = false; // accepted by employee
    private Boolean approved = false; // approved by manager

    @ManyToOne
    private Org org;

    @ManyToOne
    private Shift wantedShift;

    @ManyToOne
    private Shift offeredShift;

    @ManyToOne
    private User requestor;

    @ManyToOne
    private User receiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
        org.getSwaps().add(this);
    }

    public Shift getWantedShift() {
        return wantedShift;
    }

    public void setWantedShift(Shift wantedShift) {
        this.wantedShift = wantedShift;
    }

    public Shift getOfferedShift() {
        return offeredShift;
    }

    public void setOfferedShift(Shift offeredShift) {
        this.offeredShift = offeredShift;
    }

    public User getRequestor() {
        return requestor;
    }

    public void setRequestor(User requestor) {
        this.requestor = requestor;
        requestor.getRequestedSwaps().add(this);
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
        receiver.getReceivedSwaps().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof ShiftSwap))
            return false;

        ShiftSwap other = (ShiftSwap) o;

        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}