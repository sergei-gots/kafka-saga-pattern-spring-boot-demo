package org.appsdeveloperblog.saga.core.exceptions;

public class CreditCardProcessorUnavailableException extends RuntimeException {

    public CreditCardProcessorUnavailableException(Throwable cause) {
        super(cause);
    }
}
