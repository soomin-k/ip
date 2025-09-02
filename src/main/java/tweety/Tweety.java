package tweety;

import tweety.commands.Command;
import tweety.commands.Parser;
import tweety.exceptions.TweetyException;
import tweety.storage.Storage;
import tweety.tasks.*;
import tweety.ui.Ui;

/**
 * Main class for the Tweety application.
 * Handles user input and manages tasks including todos, deadlines, and events.
 */
public class Tweety {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Tweety() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasks());
    }

    public static void main(String[] args) {
        new Tweety().run();
    }

    /**
     * Main method that runs the Tweety application.
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
