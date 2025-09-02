package tweety.commands;

import tweety.exceptions.TweetyException;
import tweety.storage.Storage;
import tweety.tasks.Deadline;
import tweety.tasks.Task;
import tweety.tasks.TaskList;
import tweety.ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        super(CommandType.DEADLINE);
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
            Task newTask = new Deadline(description, by);
            tasks.addTask(newTask);
            storage.saveTasks(tasks);
            ui.printAddedTask(newTask, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
