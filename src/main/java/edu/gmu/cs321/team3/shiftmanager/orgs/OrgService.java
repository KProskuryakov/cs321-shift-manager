package edu.gmu.cs321.team3.shiftmanager.orgs;

import edu.gmu.cs321.team3.shiftmanager.forms.CreateNewOrgForm;
import edu.gmu.cs321.team3.shiftmanager.users.Role;
import edu.gmu.cs321.team3.shiftmanager.users.User;
import edu.gmu.cs321.team3.shiftmanager.users.UserRepository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService {

    @Autowired
    private OrgRepository orgRepo;

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public void registerNewOrg(CreateNewOrgForm orgForm, String userEmail) {
        Org newOrg = new Org();
        newOrg.setName(orgForm.getName());
        newOrg.setInformation(orgForm.getInformation());

        User currentUser = userRepo.findByEmail(userEmail);
        newOrg.addMember(currentUser);
        currentUser.setRole(Role.MANAGER);

        orgRepo.save(newOrg);
    }

    @Transactional
    public boolean isNameUnique(String name) {
        return orgRepo.findByName(name) == null;
    }

    @Transactional
    public Org getOrg(long id) {
        Optional<Org> maybeOrg = orgRepo.findById(id);
        maybeOrg.orElseThrow(() -> {
            throw new OrgNotFoundException();
        });
        return maybeOrg.get();
    }

    @Transactional
    public long inviteUserToOrg(String inviteeEmail, String name) {
        Org curOrg = userRepo.findByEmail(name).getOrg();
        User invitee = userRepo.findByEmail(inviteeEmail);

        curOrg.inviteUser(invitee);
        return curOrg.getId();
    }

}
