package tweety.commands;

import tweety.exceptions.TweetyException;
import tweety.storage.Storage;
import tweety.tasks.Task;
import tweety.tasks.TaskList;
import tweety.tasks.ToDo;
import tweety.ui.Ui;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        super(CommandType.TODO);
        this.description = description;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        Task newTask = new ToDo(description);
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        ui.printAddedTask(newTask, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
