package com.goatstickers.Exception;

import jakarta.ws.rs.core.Response;

public class ApiException extends RuntimeException {

    private final Response.Status statusCode;
    private final String errorMessage;

    public ApiException(Response.Status statusCode, String errorMessage){
        super(errorMessage);
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public Response.Status getStatus(){
        return statusCode;
    }

    public String getError(){
        return errorMessage;
    }
}


