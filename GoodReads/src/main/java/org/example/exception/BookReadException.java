package org.example.exception;

import org.example.utils.Book;

/*
    @author shivansh0408
*/
public class BookReadException extends RuntimeException {

    private static final long serialVersionUID = 3742459410987275458L;

    public BookReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
