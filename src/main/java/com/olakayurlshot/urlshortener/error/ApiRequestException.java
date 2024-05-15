package com.olakayurlshot.urlshortener.error;

public class ApiRequestException extends RuntimeException {
    
    public ApiRequestException(String message){
        super(message);
    }

}
