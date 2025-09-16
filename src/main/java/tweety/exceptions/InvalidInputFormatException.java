package tweety.exceptions;

public class InvalidInputFormatException extends TweetyException{
    private static final String FORMATTING_GAP_DEFAULT = "     ";

    public InvalidInputFormatException(String message, String taskName) {
        super(taskName + " description is of invalid format.\n" + FORMATTING_GAP_DEFAULT
                + "Please follow this format: e.g. " + message);
    }
}
