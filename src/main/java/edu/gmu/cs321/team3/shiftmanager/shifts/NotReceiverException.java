package edu.gmu.cs321.team3.shiftmanager.shifts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="User is not the receiver of this swap")
public class NotReceiverException extends RuntimeException {

    private static final long serialVersionUID = 1L;

}