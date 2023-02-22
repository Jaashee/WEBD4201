package webd4201.shanmugathasanj;

/**
 * InvalidIdException throws an exception if there are any errors with the Users
 *
 * @author Jashakan Shanmugathasan
 * @version 1.0 (2023/01/19)
 * @since (2023 / 01 / 20)
 */
public class InvalidUserDataException extends Exception {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("serial")

    public InvalidUserDataException(String message) {
        super(message);
    }

    public InvalidUserDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserDataException(Throwable cause) {
        super(cause);
    }

    public InvalidUserDataException(String message, Throwable cause, boolean enabledSuppression, Boolean writableStackTrace) {
        super(message, cause, enabledSuppression, writableStackTrace);
    }
}
