package com.alirahal.template.middleware;

import com.alirahal.template.config.MapperFactory;
import com.alirahal.template.error.ApiError;
import com.alirahal.template.error.ApiErrorFactory;
import com.alirahal.template.error.ApplicationExceptionHandler;
import com.alirahal.template.error.exceptions.ValidationException;
import com.alirahal.template.services.ValidationService;
import com.alirahal.template.utils.HasLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@Component
@WebFilter(filterName = "validateSignatureFilter", urlPatterns = {"/*"})
public class ValidateSignatureFilter extends OncePerRequestFilter implements HasLogger {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ApplicationExceptionHandler exceptionHandler;

    private static List<String> excluded;


    static {
        excluded = new ArrayList<>();
        excluded.add("/someRoute");
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        getLogger().info("Validation Filter for " + validationService.getFullUrl(httpServletRequest));

        for (int i = 0; i < excluded.size(); i++) {
            if (httpServletRequest.getRequestURL().indexOf(excluded.get(i)) >= 0) {
                getLogger().info("Validation not required");
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
        }
        try {
            validationService.validateSignature(httpServletRequest);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (ValidationException e) {
            getLogger().error(e.getLocalizedMessage());
            exceptionHandler.errorHandler(ApiErrorFactory.BAD_REQUEST.provide(), httpServletResponse);
        }
    }


}