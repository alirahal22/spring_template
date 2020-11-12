package com.alirahal.template.error;

import com.alirahal.template.error.exceptions.NotFoundException;
import com.alirahal.template.error.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Some problem occured")
    public void handleGenericException(final Exception ex, final WebRequest request) {
        ex.printStackTrace();
    }

    @ExceptionHandler({NotFoundException.class, ValidationException.class})
    public ResponseEntity<Object> handleRestErrors(final Exception ex, final WebRequest request) {
        Logger.getAnonymousLogger().warning(ex.getLocalizedMessage());
        ApiError error = ApiErrorFactory.getError(ex.getClass());
        return ResponseEntity.status(error.getStatus()).body(error);
    }


}
