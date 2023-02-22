package webd4201.shanmugathasanj;

/**
 * InvalidIdException throws an exception if there are any errors with the Names
 *
 * @author Jashakan Shanmugathasan
 * @version 1.0 (2023/01/19)
 * @since (2023 / 01 / 20)
 */
public class InvalidNameException extends Exception {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("serial")

    public InvalidNameException(String message) {
        super(message);
    }

    public InvalidNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNameException(Throwable cause) {
        super(cause);
    }

    public InvalidNameException(String message, Throwable cause, boolean enabledSuppression, Boolean writableStackTrace) {
        super(message, cause, enabledSuppression, writableStackTrace);
    }

}
