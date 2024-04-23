package com.bank.managment.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DuplicateRecordFoundException extends RuntimeException{
    public DuplicateRecordFoundException(){
        super(("Duplicate Record Found!!"));
    }
}
