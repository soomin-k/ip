package tweety.exceptions;

public class InvalidTaskNumberException extends TweetyException {
    public InvalidTaskNumberException() {
        super("Please provide a valid task number.");
    }
}
