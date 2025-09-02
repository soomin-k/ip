package tweety.commands;

import tweety.exceptions.TweetyException;
import tweety.storage.Storage;
import tweety.tasks.Event;
import tweety.tasks.Task;
import tweety.tasks.TaskList;
import tweety.ui.Ui;

public class EventCommand extends Command {

    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        super(CommandType.EVENT);
        this.description = description;
        this.from = from;
        this.to = to;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {
        Task newTask = new Event(description, from, to);
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        ui.printAddedTask(newTask, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
