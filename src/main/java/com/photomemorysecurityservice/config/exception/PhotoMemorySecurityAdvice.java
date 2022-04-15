package com.photomemorysecurityservice.config.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PhotoMemorySecurityAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = PhotoMemorySecurityException.class)
    protected ResponseEntity<Object> handlePhotoMemorySecurityException(
            PhotoMemorySecurityException exception,
            WebRequest request) {
        exception.printStackTrace();
        return handleExceptionInternal(
                exception,
                exception.getFailures(),
                new HttpHeaders(),
                exception.getStatus(),
                request);
    }
}