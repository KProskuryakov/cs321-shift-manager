package edu.gmu.cs321.team3.shiftmanager.forms;

public class ViewRequestsForm {

    private String swapRequest;
    private String releaseRequest;
    private String createNewOrgRequest;
    private String addUserToOrgRequest;

    public String getSwapRequest() {
        return swapRequest;
    }

    public void setSwapRequest(String swapRequest) {
        this.swapRequest = swapRequest;
    }

    public String getReleaseRequest() {
        return releaseRequest;
    }

    public void setReleaseRequest(String releaseRequest) {
        this.releaseRequest = releaseRequest;
    }

    public String getCreateNewOrgRequest() {
        return createNewOrgRequest;
    }

    public void setCreateNewOrgRequest(String createNewOrgRequest) {
        this.createNewOrgRequest = createNewOrgRequest;
    }

    public String getAddUserToOrgRequest() {
        return addUserToOrgRequest;
    }

    public void setAddUserToOrgRequest(String addUserToOrgRequest) {
        this.addUserToOrgRequest = addUserToOrgRequest;
    }

}
