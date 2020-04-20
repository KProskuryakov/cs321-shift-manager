package edu.gmu.cs321.team3.shiftmanager.forms;

public class ViewRequestsForm {

    private String swapRequest;
    private String releaseRequest;
    private String createNewOrgRequest;
    private String addUserToOrgRequest;

    public String getswapRequest() {
        return swapRequest;
    }

    public void setswapRequest(String swapRequest) {
        this.swapRequest = swapRequest;
    }

    public String getreleaseRequest() {
        return releaseRequest;
    }

    public void setreleaseRequest(String releaseRequest) {
        this.releaseRequest = releaseRequest;
    }

    public String getcreateNewOrgRequest() {
        return createNewOrgRequest;
    }

    public void setcreateNewOrgRequest(String createNewOrgRequest) {
        this.createNewOrgRequest = createNewOrgRequest;
    }

    public String getaddUserToOrgRequest() {
        return addUserToOrgRequest;
    }

    public void setaddUserToOrgRequest(String addUserToOrgRequest) {
        this.addUserToOrgRequest = addUserToOrgRequest;
    }

}
