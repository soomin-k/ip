package tweety.commands;

import tweety.tasks.Task;
import tweety.tasks.TaskList;
import tweety.ui.Ui;
import tweety.storage.Storage;
import tweety.exceptions.TweetyException;

public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        super(CommandType.MARK);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        Task markedTask = tasks.markTask(taskNumber);
        storage.saveTasks(tasks);
        ui.printMarkedTask(markedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
