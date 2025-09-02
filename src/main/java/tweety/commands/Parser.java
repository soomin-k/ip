package tweety.commands;

import tweety.exceptions.TweetyException;

public class Parser {
    private static final String FORMATTING_GAP_DEFAULT = "     ";

    public Command parseCommand(String userInput) throws TweetyException {
        userInput = userInput.trim();

        String firstWord = userInput.split(" ")[0];

        switch (firstWord) {
            case "mark":
                return parseMarkCommand(userInput);
            case "unmark":
                return parseUnmarkCommand(userInput);
            case "delete":
                return parseDeleteCommand(userInput);
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "todo":
                return parseTodoCommand(userInput);
            case "deadline":
                return parseDeadlineCommand(userInput);
            case "event":
                return parseEventCommand(userInput);
            case "find":
                return parseFindCommand(userInput);
            default:
                throw new TweetyException("Invalid command. Please try again.");
        }
    }

    private Command parseMarkCommand(String userInput) throws TweetyException {
        int taskNumber = getTaskNumber(userInput);
        return new MarkCommand(taskNumber);
    }

    private Command parseUnmarkCommand(String userInput) throws TweetyException {
        int taskNumber = getTaskNumber(userInput);
        return new UnmarkCommand(taskNumber);
    }

    private Command parseDeleteCommand(String userInput) throws TweetyException {
        int taskNumber = getTaskNumber(userInput);
        return new DeleteCommand(taskNumber);
    }

    private Command parseTodoCommand(String userInput) throws TweetyException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new TweetyException("Todo description cannot be empty.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. todo borrow book");
        }

        return new TodoCommand(description);
    }

    private Command parseDeadlineCommand(String userInput) throws TweetyException {
        if (userInput.length() <= 8) {
            throw new TweetyException("Deadline description cannot be empty.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. deadline borrow book /by yyyy-mm-dd");
        }

        String[] parts = userInput.substring(8).split("/by", 2);
        if (parts[0].trim().isEmpty() || !userInput.contains("/by")) {
            throw new TweetyException("Deadline description is of invalid format.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. deadline borrow book /by yyyy-mm-dd");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();
        return new DeadlineCommand(description, by);

    }

    private Command parseEventCommand(String userInput) throws TweetyException {
        if (userInput.length() <= 5) {
            throw new TweetyException("Event description cannot be empty.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. event project meeting /from Mon 2pm /to 4pm");
        }

        String[] parts = userInput.substring(5).split("/from|/to");
        if (parts[0].trim().isEmpty() || !userInput.contains("/from") || !userInput.contains("/to")) {
            throw new TweetyException("Event description is of invalid format.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. event project meeting /from Mon 2pm /to 4pm");
        }

        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        return new EventCommand(description, from, to);
    }

    private Command parseFindCommand(String userInput) throws TweetyException {
        String keyword = userInput.substring(4).trim();
        if (keyword.isEmpty()) {
            throw new TweetyException("keyword cannot be empty.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. find read");
        }
        return new FindCommand(keyword);
    }

    private int getTaskNumber(String userInput) throws TweetyException {
        try {
            String taskNumber = userInput.substring(userInput.indexOf(" ") + 1).trim();
            return Integer.parseInt(taskNumber);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new TweetyException("Invalid command format. Please specify a valid task number.");
        }
    }
}
