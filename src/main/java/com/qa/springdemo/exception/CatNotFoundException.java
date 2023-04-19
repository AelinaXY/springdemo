package com.qa.springdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="No cat found wth that ID")
public class CatNotFoundException extends RuntimeException{
    
}
