package com.example.demo;
import  org.springframework.http.HttpStatus;
import  org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import  org.springframework.web.bind.annotation.RestControllerAdvice;
import  org.springframework.web.context.request.WebRequest;
import  org.springframework.web.server.ResponseStatusException;
import  java.util.List;
@RestControllerAdvice // manage error from controller
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)//set method to solving type of Exception
    public ErrorResponse handleResponseStutasException(
            ResponseStatusException ex,
            WebRequest request
    ){
        return new ErrorResponse(
                ex.getStatusCode().value(),//get HTTP status code
                ex.getReason(),// get error message
                request.getDescription(false).replace("uri=","")
                // get URL to give rist error
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        return new ErrorResponse(
                400,
                "Validation Failed",
                errors,
                request.getDescription(false).replace("uri=", "")
        );
    }

}

