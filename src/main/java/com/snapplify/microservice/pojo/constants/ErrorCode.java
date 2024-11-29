package com.snapplify.microservice.pojo.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorCode {
    INVALID_INPUT("ERR001", "Invalid input provided", HttpStatus.BAD_REQUEST),
    EMPTY_INPUT("ERR002", "Input cannot be empty", HttpStatus.BAD_REQUEST),
    DATABASE_ERROR("ERR003", "General database error", HttpStatus.INTERNAL_SERVER_ERROR),
    SANITIZATION_ERROR("ERR004", "Error occurred while sanitizing the input", HttpStatus.INTERNAL_SERVER_ERROR),
    SENSITIVE_WORD_NOT_FOUND("ERR005", "Sensitive word not found", HttpStatus.NOT_FOUND);

    @Getter
    private final String code;

    @Getter
    private final String message;

    @Getter
    private final HttpStatus httpStatus;
}
