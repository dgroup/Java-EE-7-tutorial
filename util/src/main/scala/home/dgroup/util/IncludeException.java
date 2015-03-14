package home.dgroup.util;

/**
 * @author dgroup on 14.03.2015.
 */
public class IncludeException extends RuntimeException {

    public IncludeException(String message) {
        super(message);
    }

    public IncludeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncludeException(Throwable cause) {
        super(cause);
    }

    public IncludeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
