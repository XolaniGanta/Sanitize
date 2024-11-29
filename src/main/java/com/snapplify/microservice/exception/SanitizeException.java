package com.snapplify.microservice.exception;

import com.snapplify.microservice.pojo.constants.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SanitizeException extends RuntimeException{
    private final ErrorCode errorCode;

    public SanitizeException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }
}
