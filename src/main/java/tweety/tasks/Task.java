package tweety.tasks;

import tweety.exceptions.TweetyException;

/**
 * Represents a generic task with description and completion status.
 * Serves as the base class for all task types in the Tweety application.
 */
public abstract class Task {
    protected String description;
    protected boolean isCompleted;

    /**
     * Creates a new task with the specified description.
     * The task is initially marked as not completed.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public abstract String getType();

    public abstract void editField(String field, String newValue) throws TweetyException;

    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    /**
     * Marks the task as completed.
     * IsCompleted is set to true.
     */
    public void markAsDone() {
        isCompleted = true;
    }

    /**
     * Unmarks the task.
     * IsCompleted is set to false.
     */
    public void unmark() {
        isCompleted = false;
    }

}
