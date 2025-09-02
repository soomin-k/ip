package tweety.tasks;

import tweety.exceptions.TweetyException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskNumber) throws TweetyException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new TweetyException("Please provide a valid task number.");
        }
        Task deletedTask = tasks.remove(taskNumber - 1);
        return deletedTask;
    }

    public Task markTask(int taskNumber) throws TweetyException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new TweetyException("Please provide a valid task number.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int taskNumber) throws TweetyException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new TweetyException("Please provide a valid task number.");
        }
        Task task = tasks.get(taskNumber - 1);
        task.unmark();
        return task;
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

}
