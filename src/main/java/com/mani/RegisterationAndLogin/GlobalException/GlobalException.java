package com.mani.RegisterationAndLogin.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> AuthenticationException(AuthenticationException ax){
        return new ResponseEntity<>(ax.getMessage(), HttpStatus.BAD_GATEWAY);
    }
}
