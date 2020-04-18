package edu.gmu.cs321.team3.shiftmanager.orgs;
import edu.gmu.cs321.team3.shiftmanager.forms.CreateNewOrgForm;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrgService {

    @Autowired
    private OrgRepository orgRepo;


    @Transactional
    public void registerNewOrg(CreateNewOrgForm orgForm) {
        Org newOrg = new Org();
        newOrg.setName(orgForm.getName());
        newOrg.setInformation(orgForm.getInformation());
        orgRepo.save(newOrg);
    }
}
