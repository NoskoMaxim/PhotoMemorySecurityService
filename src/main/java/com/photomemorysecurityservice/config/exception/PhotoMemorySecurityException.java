package com.photomemorysecurityservice.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class PhotoMemorySecurityException extends RuntimeException {
    private final Map<String, String> failures;
    private final HttpStatus status;

    public PhotoMemorySecurityException(Map<String, String> failures, HttpStatus status) {
        this.failures = failures;
        this.status = status;
    }

    public PhotoMemorySecurityException(Map<String, String> failures) {
        this.failures = failures;
        this.status = BAD_REQUEST;
    }

    @Nullable
    public Map<String, String> getFailures() {
        return failures;
    }

    public HttpStatus getStatus() {
        return status;
    }
}