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
import edu.gmu.cs321.team3.shiftmanager.users.NotManagerException;
import edu.gmu.cs321.team3.shiftmanager.users.Role;
import edu.gmu.cs321.team3.shiftmanager.users.User;
import edu.gmu.cs321.team3.shiftmanager.users.UserRepository;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepo;

    @Autowired
    private ShiftSwapRepository shiftSwapRepo;

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

    @Transactional
    public void requestSwap(@Valid RequestSwapForm swapForm, long id, String userEmail) {
        ShiftSwap newSwap = new ShiftSwap();
        newSwap.setMessage(swapForm.getMessage());

        newSwap.setOrg(orgService.getOrg(userEmail));

        Shift offeredShift = shiftRepo.getOne(id);
        newSwap.setOfferedShift(offeredShift);

        Shift wantedShift = shiftRepo.getOne(swapForm.getWantedShiftId());
        newSwap.setWantedShift(wantedShift);

        newSwap.setRequestor(userRepo.findByEmail(userEmail));
        newSwap.setReceiver(userRepo.getOne(swapForm.getUserId()));

        shiftSwapRepo.save(newSwap);
    }

    @Transactional
    public ShiftSwap getSwap(long id) {
        return shiftSwapRepo.getOne(id);
    }

    @Transactional
    public void acceptSwap(long id, String name) {
        ShiftSwap swap = shiftSwapRepo.getOne(id);
        User user = userRepo.findByEmail(name);
        if (swap.getReceiver().getId() != user.getId()) {
            throw new NotReceiverException();
        }

        swap.setAccepted(true);
        if (swap.getApproved()) {
            performSwap(swap);
        }
    }

    @Transactional
    public void declineSwap(long id, String name) {
        ShiftSwap swap = shiftSwapRepo.getOne(id);
        User user = userRepo.findByEmail(name);
        if (swap.getReceiver().getId() != user.getId()) {
            throw new NotReceiverException();
        }

        removeSwap(swap);
    }

    @Transactional
    public void approveSwap(long id, String name) {
        ShiftSwap swap = shiftSwapRepo.getOne(id);
        User user = userRepo.findByEmail(name);
        if (swap.getOrg().getId() != user.getOrg().getId() || user.getRole() != Role.MANAGER) {
            throw new NotManagerException();
        }

        swap.setApproved(true);
        if (swap.getAccepted()) {
            performSwap(swap);
        }
    }

    @Transactional
    public void rejectSwap(long id, String name) {
        ShiftSwap swap = shiftSwapRepo.getOne(id);
        User user = userRepo.findByEmail(name);
        if (swap.getOrg().getId() != user.getOrg().getId() || user.getRole() != Role.MANAGER) {
            throw new NotManagerException();
        }

        removeSwap(swap);
    }

    @Transactional
    public void performSwap(ShiftSwap swap) {
        swap.getOfferedShift().addAttendee(swap.getRequestor());
        swap.getOfferedShift().removeAttendee(swap.getReceiver());

        swap.getWantedShift().addAttendee(swap.getReceiver());
        swap.getWantedShift().removeAttendee(swap.getRequestor());

        shiftRepo.save(swap.getWantedShift());
        shiftRepo.save(swap.getOfferedShift());

        shiftSwapRepo.delete(swap);
    }

    @Transactional
    public void removeSwap(ShiftSwap swap) {
        shiftSwapRepo.delete(swap);
    }

}