package com.goatstickers.Exception;

import jakarta.ws.rs.core.Response;

public class ErrorResponse {

    private String message;
    private int status;

    public ErrorResponse(String message, Response.Status status){
        this.message = message;
        this.status = status.getStatusCode();
    }

    public String getMessage(){
        return message;
    }

    public int getStatus(){
        return status;
    }
}