package com.avi.blogging.apiResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class ApiResponse {
    private String message;
    private Boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ApiResponse() {
    }

    public ApiResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
}