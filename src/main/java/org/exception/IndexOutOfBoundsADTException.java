package org.exception;

public class IndexOutOfBoundsADTException extends GenericADTException {
    public IndexOutOfBoundsADTException() {
        super("Index is out of bounds.");
    }

    public IndexOutOfBoundsADTException(String message) {
        super(message);
    }
}