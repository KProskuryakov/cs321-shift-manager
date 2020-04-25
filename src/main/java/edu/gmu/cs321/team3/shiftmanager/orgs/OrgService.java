package edu.gmu.cs321.team3.shiftmanager.orgs;

import edu.gmu.cs321.team3.shiftmanager.forms.CreateNewOrgForm;
import edu.gmu.cs321.team3.shiftmanager.users.Role;
import edu.gmu.cs321.team3.shiftmanager.users.User;
import edu.gmu.cs321.team3.shiftmanager.users.UserRepository;

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

}
