package com.in28minutes.rest.webservices.restfulwebservices.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//class for InvalidUserException


//this is where you designate the HTTPStatus given when this error is thrown
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidUserException extends RuntimeException {

    public InvalidUserException(String message) {
        super(message);
    }
}
