package tweety.exceptions;

/**
 * Custom exception class for the Tweety application.
 * Used to handle application-specific errors and provide error messages to users.
 */
public class TweetyException extends Exception {

    /**
     * Creates a new TweetyException with the specified error message.
     *
     * @param message the error message describing what went wrong
     */
    public TweetyException(String message) {
        super(message);
    }
}
