package tweety.commands;

import tweety.exceptions.TweetyException;
import tweety.storage.Storage;
import tweety.tasks.Task;
import tweety.tasks.TaskList;
import tweety.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword){
        super(CommandType.FIND);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException {


        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        TaskList matchedTaskList = new TaskList(matchingTasks);
        ui.printFindTask(matchedTaskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
