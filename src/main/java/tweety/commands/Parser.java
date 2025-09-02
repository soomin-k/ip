package tweety.commands;

import tweety.exceptions.TweetyException;

/**
 * Parses user input strings into Command objects for the Tweety application.
 * Handles various command types including mark, unmark, delete, list, bye, find, todo, deadline, and event commands.
 */
public class Parser {
    private static final String FORMATTING_GAP_DEFAULT = "     ";

    /**
     * Parses the user input string and returns the corresponding Command object.
     * Identifies command type and delegates to appropriate parsing methods.
     *
     * @param userInput The raw user input string to parse.
     * @return A Command object representing the parsed user input.
     * @throws TweetyException If the command format is invalid.
     */
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

    /**
     * Parses a mark command and extracts the task number.
     * Expected format: "mark [task_number]"
     *
     * @param userInput The user input string containing the mark command.
     * @return A Command object with MARK type and task number set.
     * @throws TweetyException If the task number is invalid or missing.
     */
    private Command parseMarkCommand(String userInput) throws TweetyException {
        int taskNumber = getTaskNumber(userInput);
        return new MarkCommand(taskNumber);
    }

    /**
     * Parses an unmark command and extracts the task number.
     * Expected format: "unmark [task_number]"
     *
     * @param userInput The user input string containing the unmark command.
     * @return A Command object with UNMARK type and task number set.
     * @throws TweetyException If the task number is invalid or missing.
     */
    private Command parseUnmarkCommand(String userInput) throws TweetyException {
        int taskNumber = getTaskNumber(userInput);
        return new UnmarkCommand(taskNumber);
    }

    /**
     * Parses a delete command and extracts the task number.
     * Expected format: "delete [task_number]"
     *
     * @param userInput The user input string containing the delete command.
     * @return A Command object with DELETE type and task number set.
     * @throws TweetyException If the task number is invalid or missing.
     */
    private Command parseDeleteCommand(String userInput) throws TweetyException {
        int taskNumber = getTaskNumber(userInput);
        return new DeleteCommand(taskNumber);
    }

    /**
     * Parses a todo command and extracts the task description.
     * Expected format: "todo [description]"
     *
     * @param userInput The user input string containing the todo command.
     * @return A Command object with TODO type and description set.
     * @throws TweetyException If the description is empty or missing.
     */
    private Command parseTodoCommand(String userInput) throws TweetyException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new TweetyException("Todo description cannot be empty.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. todo borrow book");
        }

        return new TodoCommand(description);
    }

    /**
     * Parses a deadline command and extracts description and due date.
     * Expected format: "deadline [description] /by [date]"
     *
     * @param userInput The user input string containing the deadline command.
     * @return A Command object with DEADLINE type, description, and due date set.
     * @throws TweetyException If the format is invalid, description is empty, or /by is missing.
     */
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

    /**
     * Parses an event command and extracts description, start time, and end time.
     * Expected format: "event [description] /from [start_time] /to [end_time]"
     *
     * @param userInput The user input string containing the event command.
     * @return A Command object with EVENT type, description, start time, and end time set.
     * @throws TweetyException If the format is invalid, description is empty, or /from or /to is missing.
     */
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

    /**
     * Extracts and parses the task number from user input.
     * Assumes the task number follows the command keyword separated by a space.
     *
     * @param userInput The user input string containing a command with task number.
     * @return The parsed task number as an integer.
     * @throws TweetyException If the task number is not a valid integer or is missing.
     */
    private int getTaskNumber(String userInput) throws TweetyException {
        try {
            String taskNumber = userInput.substring(userInput.indexOf(" ") + 1).trim();
            return Integer.parseInt(taskNumber);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new TweetyException("Invalid command format. Please specify a valid task number.");
        }
    }
}
