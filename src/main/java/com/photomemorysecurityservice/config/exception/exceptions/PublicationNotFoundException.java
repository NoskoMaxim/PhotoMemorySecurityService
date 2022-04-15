package com.photomemorysecurityservice.config.exception.exceptions;

import com.photomemorysecurityservice.config.exception.PhotoMemorySecurityException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class PublicationNotFoundException extends PhotoMemorySecurityException {
    public PublicationNotFoundException(Map<String, String> failures, HttpStatus status) {
        super(failures, status);
    }

    public PublicationNotFoundException(Map<String, String> failures) {
        super(failures);
    }
}