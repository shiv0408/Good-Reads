package org.example.exception;

/*
    @author shivansh0408
*/
public class UnauthorizedActionException extends RuntimeException {

    private static final long serialVersionUID = 8812390857812413605L;

    public UnauthorizedActionException(String message) {
        super(message);
    }
}
