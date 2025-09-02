package tweety.commands;

import tweety.exceptions.TweetyException;

public class Parser {
    private static final String FORMATTING_GAP_DEFAULT = "     ";

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

    private Command parseMarkCommand(String userInput) throws TweetyException {
        Command command = new Command(Command.CommandType.MARK);
        command.setTaskNumber(getTaskNumber(userInput));
        return command;
    }

    private Command parseUnmarkCommand(String userInput) throws TweetyException {
        Command command = new Command(Command.CommandType.UNMARK);
        command.setTaskNumber(getTaskNumber(userInput));
        return command;
    }

    private Command parseDeleteCommand(String userInput) throws TweetyException {
        Command command = new Command(Command.CommandType.DELETE);
        command.setTaskNumber(getTaskNumber(userInput));
        return command;
    }

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

    private int getTaskNumber(String userInput) throws TweetyException {
        try {
            String taskNumber = userInput.substring(userInput.indexOf(" ") + 1).trim();
            return Integer.parseInt(taskNumber);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new TweetyException("Invalid command format. Please specify a valid task number.");
        }
    }
}
