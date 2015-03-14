package home.dgroup.util;

/**
 * @author dgroup on 14.03.2015.
 */
public class CopyFileException extends RuntimeException {

    public CopyFileException(String message) {
        super(message);
    }

    public CopyFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CopyFileException(Throwable cause) {
        super(cause);
    }

    public CopyFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
