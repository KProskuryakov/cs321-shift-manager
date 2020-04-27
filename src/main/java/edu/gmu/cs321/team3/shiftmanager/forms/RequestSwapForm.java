package edu.gmu.cs321.team3.shiftmanager.forms;

public class RequestSwapForm {
    private Long userId;
    private Long wantedShiftId;
    private String message;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWantedShiftId() {
        return wantedShiftId;
    }

    public void setWantedShiftId(Long wantedShiftId) {
        this.wantedShiftId = wantedShiftId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}