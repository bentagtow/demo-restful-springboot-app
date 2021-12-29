package com.in28minutes.rest.webservices.restfulwebservices.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//class for UserNotFoundException


//this is where you designate the HTTPStatus given when this error is thrown
//NOT_FOUND is 401 I think, each status is a different #
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
