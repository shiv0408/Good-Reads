package org.example.exception;

/*
    @author shivansh0408
*/
public class EndOfBookException extends RuntimeException {

    private static final long serialVersionUID = 261104096126051566L;

    public EndOfBookException(String message) {
        super(message);
    }
}
