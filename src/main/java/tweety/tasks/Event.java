package tweety.tasks;

/**
 * Represents Event task with specific start and end date/time.
 * Extends the Task class to include Event functionality.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates a new Event task with the specified description, from and to date/time.
     *
     * @param description the task description
     * @param from the starting date/time for completing the task
     * @param to the ending date/time for completing the task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + getDescription()
                + " (from: " + this.from + " to: " + this.to + ")";
    }

    public String getEventStart() {
        return this.from;
    }

    public String getEventEnd() {
        return this.to;
    }
}
