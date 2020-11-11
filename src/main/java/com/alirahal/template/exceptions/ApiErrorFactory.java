package com.alirahal.template.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiErrorFactory {

    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "The requested resource was not found", ApiError::new),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Validation error", ApiError::new);

    private final int status;
    private final String message;
    private final ErrorProvider provider;

    public ApiError provide() {
        return provider.provide(status, message);
    }

    public ApiError provide(String description) {
        ApiError error = provider.provide(status, message);
        error.setDescription(description);
        return error;
    }

    interface ErrorProvider {
        ApiError provide(int status, String message);
    }
}
