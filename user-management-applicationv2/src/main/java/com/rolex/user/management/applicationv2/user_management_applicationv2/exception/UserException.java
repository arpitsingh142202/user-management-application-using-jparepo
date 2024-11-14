package com.rolex.user.management.applicationv2.user_management_applicationv2.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException {

    private HttpStatus httpStatus;
    public UserException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }
}
