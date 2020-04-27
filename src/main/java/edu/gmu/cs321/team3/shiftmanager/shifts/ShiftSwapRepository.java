package edu.gmu.cs321.team3.shiftmanager.shifts;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.gmu.cs321.team3.shiftmanager.users.User;

@Repository
public interface ShiftSwapRepository extends JpaRepository<ShiftSwap, Long> {
    public Set<ShiftSwap> findAllByRequestor(User requestor);

    public Set<ShiftSwap> findAllByReceiver(User receiver);

    public Set<ShiftSwap> findAllByOrg(User org);

    public Set<ShiftSwap> findAllByOrgAndAcceptedAndApproved(User org, Boolean accepted, Boolean approved);
}