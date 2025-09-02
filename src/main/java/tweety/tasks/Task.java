package tweety.tasks;

/**
 * Represents a generic task with description and completion status.
 * Serves as the base class for all task types in the Tweety application.
 */
public class Task {
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

    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    public String getDescription() {
        return this.description;
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
