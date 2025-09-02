package tweety.commands;

import tweety.tasks.Task;
import tweety.tasks.TaskList;
import tweety.ui.Ui;
import tweety.storage.Storage;
import tweety.exceptions.TweetyException;

public class UnmarkCommand extends Command{
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        super(CommandType.UNMARK);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        Task unmarkedTask = tasks.unmarkTask(taskNumber);
        storage.saveTasks(tasks);
        ui.printUnmarkedTask(unmarkedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
