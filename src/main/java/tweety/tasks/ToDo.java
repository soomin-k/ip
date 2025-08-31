package tweety.tasks;

/**
 * Represents Todo task
 * Extends the Task class to include Todo functionality.
 */
public class ToDo extends Task {

    /**
     * Creates a new Todo task with the specified description and deadline.
     *
     * @param description the task description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + getDescription();
    }
}
