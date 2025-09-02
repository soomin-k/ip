package tweety;

import tweety.commands.Command;
import tweety.commands.Parser;

import tweety.exceptions.TweetyException;

import tweety.storage.Storage;

import tweety.tasks.TaskList;

import tweety.ui.Ui;

/**
 * Main class for the Tweety application.
 * Handles user input and manages tasks including todos, deadlines, and events.
 */
public class Tweety {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Tweety application instance.
     * Initialises the user interface, storage, and task list with previously saved tasks.
     */
    public Tweety() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Entry point for the Tweety application.
     * Creates a new Tweety instance and starts the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Tweety().run();
    }

    /**
     * Runs the main application loop.
     * Displays welcome message and continuously processes user commands until exit.
     */
    public void run() {

        ui.printWelcomeMessage();

        boolean isExit = false;

        while (!isExit) {
            try {
                Parser parser = new Parser();
                String userInput = ui.readCommand();
                Command command = parser.parseCommand(userInput);
                isExit = executeCommand(command);
            } catch (TweetyException e) {
                ui.printErrorMessage(e);
            }
        }
        ui.closeScanner();
    }

    private boolean executeCommand(Command command) throws TweetyException {
        command.execute(tasks, ui, storage);
        return command.isExit();
    }
}
