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

        if (userInput.startsWith("mark")) {
            return parseMarkCommand(userInput);
        } else if (userInput.startsWith("unmark")) {
            return parseUnmarkCommand(userInput);
        } else if (userInput.startsWith("delete")) {
            return parseDeleteCommand(userInput);
        } else if (userInput.equals("list")) {
            return new Command(Command.CommandType.LIST);
        } else if (userInput.equals("bye")) {
            return new Command(Command.CommandType.BYE);
        } else if (userInput.startsWith("todo")) {
            return parseTodoCommand(userInput);
        } else if (userInput.startsWith("deadline")) {
            return parseDeadlineCommand(userInput);
        } else if (userInput.startsWith("event")) {
            return parseEventCommand(userInput);
        } else {
            return new Command(Command.CommandType.INVALID);
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
        Command command = new Command(Command.CommandType.MARK);
        command.setTaskNumber(getTaskNumber(userInput));
        return command;
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
        Command command = new Command(Command.CommandType.UNMARK);
        command.setTaskNumber(getTaskNumber(userInput));
        return command;
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
        Command command = new Command(Command.CommandType.DELETE);
        command.setTaskNumber(getTaskNumber(userInput));
        return command;
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

        Command command = new Command(Command.CommandType.TODO);
        command.setDescription(description);
        return command;
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

        Command command = new Command(Command.CommandType.DEADLINE);
        command.setDescription(parts[0].trim());
        command.setBy(parts[1].trim());
        return command;
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

        Command command = new Command(Command.CommandType.EVENT);
        command.setDescription(parts[0].trim());
        command.setFrom(parts[1].trim());
        command.setTo(parts[2].trim());
        return command;
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
