package tweety.ui;

import tweety.tasks.Task;
import tweety.tasks.TaskList;

public class GuiUi extends Ui {
    private StringBuilder response;

    /**
     * Constructs a new GuiUi instance.
     * Initializes the response builder for capturing output.
     */
    public GuiUi() {
        super();
        this.response = new StringBuilder();
    }

    /**
     * Returns the captured response and clears the buffer.
     *
     * @return The formatted response string.
     */
    public String getResponse() {
        String result = response.toString().trim();
        response.setLength(0); // Clear the buffer for next use
        return result;
    }

    @Override
    public void printWelcomeMessage() {
        response.append("Hello! I'm Tweety\n");
        response.append("What can I do for you?\n");
    }

    @Override
    public void printExitMessage() {
        response.append("Bye. Hope to see you again soon!\n");
    }

    @Override
    public void printTaskList(TaskList tasks) {
        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task currTask = tasks.getTask(i);
            response.append((i + 1)).append(". ").append(currTask).append("\n");
        }
    }

    @Override
    public void printNoTaskLeft() {
        response.append("Nice! You don't have any tasks due at the moment.\n");
    }

    @Override
    public void printAddedTask(Task addedTask, int taskCount) {
        response.append("Got it. I've added this task:\n");
        response.append("  ").append(addedTask).append("\n");
        response.append("Now you have ").append(taskCount).append(" tasks in the list\n");
    }

    @Override
    public void printDeletedTask(Task deletedTask, int taskCount) {
        response.append("Noted. I've removed this task:\n");
        response.append("  ").append(deletedTask).append("\n");
        response.append("Now you have ").append(taskCount).append(" tasks in the list.\n");
    }

    @Override
    public void printUnmarkedTask(Task unmarkedTask) {
        response.append("OK, I've marked this task as not done yet:\n");
        response.append("  ").append(unmarkedTask).append("\n");
    }

    @Override
    public void printMarkedTask(Task markedTask) {
        response.append("Nice! I've marked this task as done:\n");
        response.append("  ").append(markedTask).append("\n");
    }

    @Override
    public void printFindTask(TaskList tasks) {
        response.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task currTask = tasks.getTask(i);
            response.append((i + 1)).append(". ").append(currTask).append("\n");
        }
    }

    @Override
    public void printEditedTask(Task editedTask) {
        response.append("Nice! I've edited this task:\n");
        response.append("  ").append(editedTask).append("\n");
    }

    @Override
    public void printErrorMessage(Exception e) {
        response.append(e.getMessage()).append("\n");
    }
}
