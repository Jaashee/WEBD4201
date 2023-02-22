package webd4201.shanmugathasanj;

/**
 * InvalidIdException throws an exception if there are any errors with the ID
 *
 * @author Jashakan Shanmugathasan
 * @version 1.0 (2023/01/19)
 * @since (2023 / 01 / 20)
 */
public class InvalidIdException extends Exception {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("serial")

    public InvalidIdException(String message) {
        super(message);
    }

    public InvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIdException(Throwable cause) {
        super(cause);
    }

    public InvalidIdException(String message, Throwable cause, boolean enabledSuppression, Boolean writableStackTrace) {
        super(message, cause, enabledSuppression, writableStackTrace);
    }

    public InvalidIdException() {

    }

}
