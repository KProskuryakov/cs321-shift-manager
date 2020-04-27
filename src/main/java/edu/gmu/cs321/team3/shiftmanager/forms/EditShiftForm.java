package edu.gmu.cs321.team3.shiftmanager.forms;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class EditShiftForm {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime = LocalDateTime.now().withHour(9).withMinute(0).withSecond(0).plusDays(1);
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime = LocalDateTime.now().withHour(17).withMinute(0).withSecond(0).plusDays(1);
    private List<Long> attendees;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Long> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Long> attendees) {
        this.attendees = attendees;
    }

}
