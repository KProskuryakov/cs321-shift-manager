package edu.gmu.cs321.team3.shiftmanager.forms;

import edu.gmu.cs321.team3.shiftmanager.orgs.UniqueOrgConstraint;

public class CreateNewOrgForm {

	@UniqueOrgConstraint
	private String name;
	private String information;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}
