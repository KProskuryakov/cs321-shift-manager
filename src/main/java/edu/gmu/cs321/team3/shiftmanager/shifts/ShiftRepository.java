package edu.gmu.cs321.team3.shiftmanager.shifts;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.gmu.cs321.team3.shiftmanager.orgs.Org;
import edu.gmu.cs321.team3.shiftmanager.users.User;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {

    public Set<Shift> findAllByOrg(Org org);

    public List<Shift> findAllByAttendeesContainingAndEndTimeAfterAndStartTimeBeforeOrderByStartTimeAsc(User user, Date endTime, Date startTime);

    public List<Shift> findAllByOrgAndEndTimeAfterAndStartTimeBeforeOrderByStartTimeAsc(Org org, Date endTime, Date startTime);

}