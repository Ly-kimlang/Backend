package com.example.demo;
import  java.util.List;
public class ErrorResponse {
    private int status;
    private String message;
    private String path;
    private List<String>errors;
    // constuctor : General error
    public ErrorResponse(int status, String message, String path){
        this.status=status;
        this.message=message;
        this.path=path;// URL
    }
    // constuctor : Validation error
    public ErrorResponse(int status, String message, List<String> errors, String path) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.path = path;
    }
    public int getStatus(){
        return status;
    }
    public String getMessage(){
        return message;
    }
    public String getPath(){
        return path;
    }
    public List<String>getErrors(){
        return errors;
    }
}
