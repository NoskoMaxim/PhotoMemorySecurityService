package com.photomemorysecurityservice.config.exception.exceptions;

import com.photomemorysecurityservice.config.exception.PhotoMemorySecurityException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class UserRoleNotFoundException extends PhotoMemorySecurityException {

    public UserRoleNotFoundException(Map<String, String> failures, HttpStatus status) {
        super(failures, status);
    }

    public UserRoleNotFoundException(Map<String, String> failures) {
        super(failures);
    }
}