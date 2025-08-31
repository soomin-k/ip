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

    /**
     * Returns the status icon representing the completion status of the task.
     * Returns "[X]" if completed, "[ ]" if not completed.
     *
     * @return the status icon string
     */
    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        isCompleted = true;
    }

    public void unmark() {
        isCompleted = false;
    }

}
