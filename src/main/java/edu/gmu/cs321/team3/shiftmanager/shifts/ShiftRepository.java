package edu.gmu.cs321.team3.shiftmanager.shifts;

import java.util.Set;

import org.springframework.stereotype.Repository;

import edu.gmu.cs321.team3.shiftmanager.orgs.Org;
import edu.gmu.cs321.team3.shiftmanager.users.User;

@Repository
public interface ShiftRepository {

    public Set<Shift> findAllByOrg(Org org);

    public Set<Shift> findAllAttendeesContaining(User user);

}