package tweety.exceptions;

public class EmtpyDescriptionException extends TweetyException {
    private static final String FORMATTING_GAP_DEFAULT = "     ";
    public EmtpyDescriptionException(String message, String taskName) {
        super(taskName + " description cannot be empty.\n" + FORMATTING_GAP_DEFAULT
                + "Please follow this format: e.g. " + message);
    }
}
