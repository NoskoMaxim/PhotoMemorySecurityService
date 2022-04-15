package com.photomemorysecurityservice.config.exception.exceptions;

import com.photomemorysecurityservice.config.exception.PhotoMemorySecurityException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class PhotoNameExistenceException extends PhotoMemorySecurityException {
    public PhotoNameExistenceException(Map<String, String> failures, HttpStatus status) {
        super(failures, status);
    }

    public PhotoNameExistenceException(Map<String, String> failures) {
        super(failures);
    }
}