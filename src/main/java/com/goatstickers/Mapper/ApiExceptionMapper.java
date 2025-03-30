package com.goatstickers.Mapper;

import com.goatstickers.Exception.ApiException;
import com.goatstickers.Exception.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getError(), exception.getStatus());
        return Response.status(exception.getStatus())
                .entity(errorResponse)
                .build();
    }
}