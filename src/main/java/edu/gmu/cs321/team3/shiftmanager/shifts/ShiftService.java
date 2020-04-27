package edu.gmu.cs321.team3.shiftmanager.shifts;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gmu.cs321.team3.shiftmanager.forms.EditShiftForm;
import edu.gmu.cs321.team3.shiftmanager.forms.RequestSwapForm;
import edu.gmu.cs321.team3.shiftmanager.orgs.Org;
import edu.gmu.cs321.team3.shiftmanager.orgs.OrgService;
import edu.gmu.cs321.team3.shiftmanager.users.User;
import edu.gmu.cs321.team3.shiftmanager.users.UserRepository;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OrgService orgService;

    @Transactional
    public List<Shift> getUpcomingShiftsForOrg(Org org) {
        Date currentTime = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(currentTime);
        c.add(Calendar.DATE, 7);
        Date sevenDaysLater = c.getTime();

        return shiftRepo.findAllByOrgAndEndTimeAfterAndStartTimeBeforeOrderByStartTimeAsc(org, currentTime,
                sevenDaysLater);
    }

    @Transactional
    public List<Shift> getUpcomingShiftsForUser(String userEmail) {
        Date currentTime = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(currentTime);
        c.add(Calendar.DATE, 7);
        Date sevenDaysLater = c.getTime();

        User user = userRepo.findByEmail(userEmail);

        return shiftRepo.findAllByAttendeesContainingAndEndTimeAfterAndStartTimeBeforeOrderByStartTimeAsc(user,
                currentTime, sevenDaysLater);
    }

    @Transactional
    public void newShift(EditShiftForm shiftForm, String userEmail) {
        Shift shift = new Shift();
        shift.setStartTime(Timestamp.valueOf(shiftForm.getStartTime()));
        shift.setEndTime(Timestamp.valueOf(shiftForm.getEndTime()));
        shift.setOrg(orgService.getOrg(userEmail));

        List<User> users = userRepo.findAllById(shiftForm.getAttendees());
        shift.setAttendees(users);

        shiftRepo.save(shift);
    }

    @Transactional
    public void shiftToForm(long id, EditShiftForm shiftForm) {
        Shift shift = shiftRepo.getOne(id);
        shiftForm.setStartTime(shift.getStartTime().toLocalDateTime());
        shiftForm.setEndTime(shift.getEndTime().toLocalDateTime());
        List<Long> ids = shift.getAttendees().stream().map(u -> u.getId()).collect(Collectors.toList());
        shiftForm.setAttendees(ids);
    }

    @Transactional
    public void editShift(EditShiftForm shiftForm, String userEmail, long shiftId) {
        Shift shift = shiftRepo.getOne(shiftId);
        shift.setStartTime(Timestamp.valueOf(shiftForm.getStartTime()));
        shift.setEndTime(Timestamp.valueOf(shiftForm.getEndTime()));
        shift.setOrg(orgService.getOrg(userEmail));

        List<User> users = userRepo.findAllById(shiftForm.getAttendees());
        shift.setAttendees(users);

        shiftRepo.save(shift);
    }

    @Transactional
    public Shift getShift(long id) {
        return shiftRepo.getOne(id);
    }

	public void requestSwap(@Valid RequestSwapForm shiftForm, long id, String name) {
        ShiftSwap newSwap = new ShiftSwap();
        // newSwap.set
	}

}