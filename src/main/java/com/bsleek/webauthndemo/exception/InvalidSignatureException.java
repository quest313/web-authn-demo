package com.bsleek.webauthndemo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
public class InvalidSignatureException extends Exception {
    public InvalidSignatureException(String s){
            super(s);
        }
}
