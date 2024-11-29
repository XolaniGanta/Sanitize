package com.snapplify.microservice.controller.advice;

import com.snapplify.microservice.exception.SanitizeException;
import com.snapplify.microservice.pojo.request.ApplicationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = SanitizeException.class)
    public ResponseEntity<ApplicationError> handleRunTimeException(SanitizeException e){
        return new ResponseEntity<>(ApplicationError.builder()
                .code(e.getErrorCode().getCode())
                .msg(e.getErrorCode().getMessage())
                .build(),
                e.getErrorCode().getHttpStatus());
    }
}
