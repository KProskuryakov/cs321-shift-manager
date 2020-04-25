package edu.gmu.cs321.team3.shiftmanager.orgs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Invalid Org ID")
public class OrgNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

}