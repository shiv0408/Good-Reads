package org.example.exception;

/*
    @author shivansh0408
*/
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2956270926640731509L;

    public ResourceNotFoundException (String message) {
        super(message);
    }
}
