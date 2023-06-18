package fr.idarkay.morefeatures.exception;

/**
 * File <b>KeyBindingException</b> located on fr.idarkay.morefeatures.exception
 * KeyBindingException is a part of Features-mod_1.17.1.
 * <p>
 * Copyright (c) 2021 Features-mod_1.17.1.
 * <p>
 *
 * @author Alois. B. (IDarKay),
 * Created the 07/08/2021 at 04:04
 */
public class KeyBindingException extends RuntimeException
{
    public KeyBindingException() {
        super();
    }

    public KeyBindingException(String message) {
        super(message);
    }

    public KeyBindingException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyBindingException(Throwable cause) {
        super(cause);
    }

    protected KeyBindingException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
