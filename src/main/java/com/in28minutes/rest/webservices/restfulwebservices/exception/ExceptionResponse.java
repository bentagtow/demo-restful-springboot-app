package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;


//this class gives our exception response. So it is the info that will be given when exceptions are made.
//generic class and then inside of CustomizedResponseEntityExceptionHandler is where you customize
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
