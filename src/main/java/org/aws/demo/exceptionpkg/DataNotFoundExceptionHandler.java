package org.aws.demo.exceptionpkg;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class DataNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public Object dataNotFoundException(DataNotFoundException dataNotFoundException, WebRequest webRequest) {
        return ExceptionResponse
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(new Date())
                .path(webRequest.getContextPath())
                .message(dataNotFoundException.getMessage())
                .build();
    }
}
