package org.example.exception;

/*
    @author shivansh0408
*/
public class InvalidRatingException extends RuntimeException {

    private static final long serialVersionUID = 4386619138671173884L;

    public InvalidRatingException(String message) {
        super(message);
    }
}
