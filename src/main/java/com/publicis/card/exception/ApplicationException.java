package com.publicis.card.exception;


import java.util.Map;
import java.util.function.Supplier;


public class ApplicationException extends RuntimeException {

    public ApplicationException(){
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    // we can customized it by giving more information like errorcode instance variable etc.
}
