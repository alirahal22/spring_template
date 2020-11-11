package com.alirahal.template.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiErrorFactory {

    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "The requested resource was not found", ApiError::new);

    private final int status;
    private final String message;
    private final ErrorProvider provider;

    public ApiError provide() {
        return provider.provide(status, message);
    }

    interface ErrorProvider {
        ApiError provide(int status, String message);
    }
}
