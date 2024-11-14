package com.rolex.user.management.applicationv2.user_management_applicationv2.handler;

import com.rolex.user.management.applicationv2.user_management_applicationv2.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class UserManagementExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        ProblemDetail problemDetail = ProblemDetail.
                forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request is invalid");
        Map<String,Object> errorMap = errors.stream().collect(Collectors
                .toMap(FieldError::getField, x->x.getDefaultMessage()));
        problemDetail.setProperties(errorMap);
        return problemDetail;
    }

    @ExceptionHandler(UserException.class)
    public ProblemDetail userException(UserException userException){
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(userException.getHttpStatus(),"Request is invalid");
        problemDetail.setDetail(userException.getMessage());
        return problemDetail;
    }
}
