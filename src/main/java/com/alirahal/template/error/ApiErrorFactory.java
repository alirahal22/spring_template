package com.alirahal.template.error;

import com.alirahal.template.error.exceptions.NotFoundException;
import com.alirahal.template.error.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum ApiErrorFactory {

    NOT_FOUND(NotFoundException.class, HttpStatus.NOT_FOUND.value(), "The requested resource was not found",
            ApiError::new),
    BAD_REQUEST(ValidationException.class, HttpStatus.BAD_REQUEST.value(), "Validation error", ApiError::new),
    ILLEGAL_ARGUMENT(IllegalArgumentException.class, HttpStatus.BAD_REQUEST.value(), "Illegal arguments passed",
            ApiError::new),
    INVALID_DATA_ACCESS(InvalidDataAccessApiUsageException.class, HttpStatus.BAD_REQUEST.value(), "Illegal arguments " +
            "passed",
            ApiError::new);

    private final Class exception;
    private final int status;
    private final String message;
    private final ErrorProvider provider;

    private static final Map<Class, ApiErrorFactory> cache = Arrays.stream(values()).collect(Collectors.toMap
            (ApiErrorFactory::getException, Function.identity()));

    public static ApiError getError(Class exception) {
        return cache.get(exception).provide();
    }

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
