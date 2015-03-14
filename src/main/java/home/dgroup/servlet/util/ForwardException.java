package home.dgroup.servlet.util;

/**
 * @author dgroup on 14.03.2015.
 */
public class ForwardException extends RuntimeException {

    public ForwardException(String message) {
        super(message);
    }

    public ForwardException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForwardException(Throwable cause) {
        super(cause);
    }

    public ForwardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
