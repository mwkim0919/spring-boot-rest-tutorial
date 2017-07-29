package com.mwkim.restwebservices.exceptions;

import java.time.LocalDateTime;


public class ExceptionResponse {
    //timeStamp
    private LocalDateTime timeStamp;

    //message
    private String message;

    //detail
    private String detail;

    public ExceptionResponse(LocalDateTime timeStamp, String message, String detail) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.detail = detail;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

}
