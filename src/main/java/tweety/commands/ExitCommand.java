package tweety.commands;

import tweety.exceptions.TweetyException;
import tweety.storage.Storage;
import tweety.tasks.TaskList;
import tweety.ui.Ui;

public class ExitCommand extends Command{

    public ExitCommand() {
        super(CommandType.BYE);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        storage.saveTasks(tasks);
        ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
