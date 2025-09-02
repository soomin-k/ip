package tweety.commands;

import tweety.exceptions.TweetyException;
import tweety.storage.Storage;
import tweety.tasks.TaskList;
import tweety.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        ui.printTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
