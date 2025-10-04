package com.moviepoint.exception;

/**
 * Custom exception for handling validation errors
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}