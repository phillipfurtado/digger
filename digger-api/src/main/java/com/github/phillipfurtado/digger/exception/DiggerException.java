package com.github.phillipfurtado.digger.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class DiggerException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DiggerException(String message, Throwable e) {
        super(message, e);
    }

}
