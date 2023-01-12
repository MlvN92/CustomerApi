package com.example.customerapi.model.response;

import org.springframework.http.HttpStatus;

public class ServerResponse {

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private HttpStatus httpStatus;
    private String errorMessage;
}
