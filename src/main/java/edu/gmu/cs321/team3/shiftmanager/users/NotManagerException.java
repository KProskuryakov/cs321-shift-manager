package edu.gmu.cs321.team3.shiftmanager.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="User is not a manager of this org")
public class NotManagerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

}