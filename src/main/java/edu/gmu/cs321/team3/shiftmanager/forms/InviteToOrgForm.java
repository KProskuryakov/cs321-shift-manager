package edu.gmu.cs321.team3.shiftmanager.forms;

public class InviteToOrgForm {

	private String manager;
	private String shiftLeader;
	private String employee;
	private String currentType;
	private String newType;

	public String getmanager() {
		return manager;
	}

	public void setmanager(String manager) {
		this.manager = manager;
	}	
	
	public String getshiftLeader() {
		return shiftLeader;
	}

	public void setshiftLeader(String shiftLeader) {
		this.shiftLeader = shiftLeader;
	}
	
	public String getemployee() {
		return employee;
	}

	public void setemployee(String employee) {
		this.employee = employee;
	}
	
	public String getcurrentType() {
		return currentType;
	}

	public void setcurrentType(String currentType) {
		this.currentType = currentType;
	}
	
	public String getnewType() {
		return newType;
	}

	public void setnewType(String newType) {
		this.newType = newType;
	}

}
