package com.example.demo.exception;

import lombok.Data;

import java.util.Date;


@Data
public class ExceptionResponse {
    private String message;
    private String details;
    private Date timeStamp;

    public ExceptionResponse(String message, String details){
        this.message =message;
        this.details = details;
        this.timeStamp = new Date();
    }
}
