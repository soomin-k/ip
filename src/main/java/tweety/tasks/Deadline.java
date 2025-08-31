package tweety.tasks;

/**
 * Represents a deadline task with a specific due date.
 * Extends the Task class to include deadline functionality.
 */
public class Deadline extends Task {
    protected String deadline;

    /**
     * Creates a new deadline task with the specified description and deadline.
     *
     * @param description the task description
     * @param deadline the deadline for completing the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

   @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + getDescription() + " (by: " + deadline + ")";
    }

    public String getDeadline() {
        return this.deadline;
    }
}
