package com.photomemorysecurityservice.config.exception.exceptions;

import com.photomemorysecurityservice.config.exception.PhotoMemorySecurityException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class RefreshTokenExpirationException extends PhotoMemorySecurityException {

    public RefreshTokenExpirationException(Map<String, String> failures, HttpStatus status) {
        super(failures, status);
    }

    public RefreshTokenExpirationException(Map<String, String> failures) {
        super(failures);
    }
}
