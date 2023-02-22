package webd4201.shanmugathasanj;

/**
 * InvalidIdException throws an exception if there are any errors with the user's password
 *
 * @author Jashakan Shanmugathasan
 * @version 1.0 (2023/01/19)
 * @since (2023 / 01 / 20)
 */
public class InvalidPasswordException extends Exception {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("serial")

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPasswordException(Throwable cause) {
        super(cause);
    }

    public InvalidPasswordException(String message, Throwable cause, boolean enabledSuppression, Boolean writableStackTrace) {
        super(message, cause, enabledSuppression, writableStackTrace);
    }
}
