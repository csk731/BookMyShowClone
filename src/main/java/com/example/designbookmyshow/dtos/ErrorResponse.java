package com.example.designbookmyshow.dtos;

import java.util.Date;

public class ErrorResponse {
    private Date timeStamp;
    private String message;
    private String details;

    public ErrorResponse(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
