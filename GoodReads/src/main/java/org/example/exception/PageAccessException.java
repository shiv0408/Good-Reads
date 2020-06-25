package org.example.exception;

/*
    @author shivansh0408
*/
public class PageAccessException extends RuntimeException {

    private static final long serialVersionUID = -6016707813262689714L;

    public PageAccessException(Throwable cause) {
        super(cause);
    }

    public PageAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
