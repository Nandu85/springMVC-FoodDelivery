package com.narola.fooddelivery.utility;

public class URLException extends RuntimeException{
    public URLException() {
        super();
    }

    public URLException(String message) {
        super(message);
    }

    public URLException(String message, Throwable cause) {
        super(message, cause);
    }

    public URLException(Throwable cause) {
        super(cause);
    }
}
