package com.olakayurlshot.urlshortener.error;

public class InvalidUrlIdException extends RuntimeException {

    public InvalidUrlIdException(String message) {
        super(message);
    }
}