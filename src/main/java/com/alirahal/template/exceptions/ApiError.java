package com.alirahal.template.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiError {
    private final String message;

    private int status;
    private String description;

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
