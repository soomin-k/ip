package tweety.tasks;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;

import tweety.exceptions.TweetyException;

/**
 * Represents a deadline task with a specific due date.
 * Extends the Task class to include deadline functionality.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Creates a new deadline task with the specified description and deadline.
     *
     * @param description the task description
     * @param deadline the deadline for completing the task
     */
    public Deadline(String description, String deadline) throws TweetyException{
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new TweetyException("Invalid date format: " + deadline +
                    ". Please use yyyy-mm-dd format (e.g. 2025-9-30)");
        }
    }

   @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + getDescription() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }
}
