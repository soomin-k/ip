package tweety;

import tweety.exceptions.TweetyException;
import tweety.storage.Storage;
import tweety.tasks.Deadline;
import tweety.tasks.Event;
import tweety.tasks.Task;
import tweety.tasks.ToDo;

/**
 * Main class for the Tweety application.
 * Handles user input and manages tasks including todos, deadlines, and events.
 */
public class Tweety {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Tweety() {
        Tweety.ui = new Ui();
        Tweety.storage = new Storage();
        Tweety.tasks = new TaskList(Storage.loadTasks());
    }

    public static void main(String[] args) {
        new Tweety().run();
    }

    /**
     * Main method that runs the Tweety application.
     */
    public void run() {

        handleIntroCommand();

        // Handle each of the userInput commands
        while (true) {

            try {
                // Read the userInput
                Parser parser = new Parser();
                String userInput = ui.readCommand();
                Command command = parser.parseCommand(userInput);

                if (executeCommand(command)) {
                    break; // Exit the loop if bye command was executed
                }
            } catch (TweetyException e) {
                ui.printErrorMessage(e);
            }
        }
        ui.closeScanner();
    }

    private boolean executeCommand(Command command) throws TweetyException {
        switch (command.getType()) {
            case MARK:
                handleMarkCommand(command.getTaskNumber());
                break;
            case UNMARK:
                handleUnmarkCommand(command.getTaskNumber());
                break;
            case DELETE:
                handleDeleteCommand(command.getTaskNumber());
                break;
            case LIST:
                handleListCommand();
                break;
            case BYE:
                handleExitCommand();
                return true;
            case EVENT:
                handleEventCommand(command);
                break;
            case TODO:
                handleTodoCommand(command);
                break;
            case DEADLINE:
                handleDeadlineCommand(command);
                break;
            case INVALID:
                throw new TweetyException("Invalid command. Please try again.");
        }
        return false;
    }


    private static void handleMarkCommand(int taskNumber)
            throws TweetyException {

        Task markedTask = tasks.markTask(taskNumber);

        // Save the task
        Storage.saveTasks(tasks);

        ui.printMarkedTask(markedTask);
    }

    private static void handleUnmarkCommand(int taskNumber)
            throws TweetyException {

        Task unmarkedTask = tasks.unmarkTask(taskNumber);

        // Save the task
        Storage.saveTasks(tasks);

        ui.printUnmarkedTask(unmarkedTask);
    }


    private static void handleDeleteCommand(int taskNumber)
            throws TweetyException {

        Task deletedTask = tasks.deleteTask(taskNumber);

        // Save the task
        Storage.saveTasks(tasks);

        ui.printDeletedTask(deletedTask, tasks.getTaskCount());
    }

    private static void handleIntroCommand() {
        ui.printWelcomeMessage();
    }


    private static void handleListCommand()
            throws TweetyException {
        // Print the list of tasks
        ui.printTaskList(tasks);
    }

    private static void handleExitCommand() {
        // Save the tasks before exiting
        Storage.saveTasks(tasks);
        ui.printExitMessage();
    }


    private static void handleEventCommand(Command command) throws TweetyException{
        Task newTask = new Event(command.getDescription(), command.getFrom(), command.getTo());
        tasks.addTask(newTask);
        Storage.saveTasks(tasks);
        ui.printAddedTask(newTask, tasks.getTaskCount());
    }

    private static void handleTodoCommand(Command command) throws TweetyException {
        Task newTask = new ToDo(command.getDescription());
        tasks.addTask(newTask);
        Storage.saveTasks(tasks);
        ui.printAddedTask(newTask, tasks.getTaskCount());
    }

    private static void handleDeadlineCommand(Command command) throws TweetyException {
        Task newTask = new Deadline(command.getDescription(), command.getBy());
        tasks.addTask(newTask);
        Storage.saveTasks(tasks);
        ui.printAddedTask(newTask, tasks.getTaskCount());
    }

}
