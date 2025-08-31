package tweety;

import java.util.ArrayList;
import java.util.Scanner;

import tweety.exceptions.TweetyException;
import tweety.storage.Storage;
import tweety.tasks.Deadline;
import tweety.tasks.Event;
import tweety.tasks.Task;
import tweety.tasks.ToDo;


/**
 * Main class for the Tweety application.
 * Handles user input and manages tasks including todos, deadlines, and events.
 */
public class Tweety {
    private static final String FORMATTING_GAP_DEFAULT = "     ";
    private static final String FORMATTING_LINE = FORMATTING_GAP_DEFAULT
            + "____________________________________________________________";
    private static ArrayList<Task> tasks = Storage.loadTasks();
    private static int taskCount = tasks.size();

    /**
     * Main method that runs the Tweety application.
     */
    public static void main(String[] args) {
        // Define scanner to read user inputs.
        Scanner sc = new Scanner(System.in);

        handleIntroCommand();

        // Handle each of the userInput commands
        while (true) {
            // Read the userInput
            String userInput = sc.nextLine();

            try {
                if (userInput.startsWith("mark")) {
                    int taskNumber = getTaskNumber(userInput);
                    if (taskNumber <= 0 || taskNumber - 1 >= taskCount) {
                        throw new TweetyException("Please provide a valid task number.");
                    } else {
                        handleMarkCommand(tasks, taskNumber);
                    }
                } else if (userInput.startsWith("unmark")) {
                    int taskNumber = getTaskNumber(userInput);
                    if (taskNumber <= 0 || taskNumber - 1 >= taskCount) {
                        throw new TweetyException("Please provide a valid task number.");
                    } else {
                        handleUnmarkCommand(tasks, taskNumber);
                    }
                } else if (userInput.startsWith("delete")) {
                    int taskNumber = getTaskNumber(userInput);
                    if (taskNumber <= 0 || taskNumber - 1 >= taskCount) {
                        throw new TweetyException("Please provide a valid task number.");
                    } else {
                        handleDeleteCommand(tasks, taskNumber);
                    }
                } else if (userInput.equals("list")) {
                    handleListCommand(tasks);
                } else if (userInput.equals("bye")) {
                    handleExitCommand();
                    break;
                } else {
                    if (userInput.startsWith("event")) {
                        handleEventCommand(userInput);
                    } else if (userInput.startsWith("todo")) {
                        handleTodoCommand(userInput);
                    } else if (userInput.startsWith("deadline")) {
                        handleDeadlineCommand(userInput);
                    } else {
                        // Handle invalid commands by throwing exception
                        throw new TweetyException("Invalid command. Please try again.");
                    }
                    handleTaskAddition();
                }
            } catch (TweetyException e){
                System.out.println(FORMATTING_LINE);
                System.out.println(FORMATTING_GAP_DEFAULT + e.getMessage());
                System.out.println(FORMATTING_LINE);
            }
        }

    }

    /**
     * Extracts the task number from user input for mark/unmark/delete commands.
     *
     * @param userInput the user's input string
     * @return the task number specified by the user
     * @throws TweetyException if the task number format is invalid
     */
    private static int getTaskNumber(String userInput) throws TweetyException {
        try {
            String taskNumberStr = userInput.substring(userInput.indexOf(" ") + 1).trim();
            return Integer.parseInt(taskNumberStr);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new TweetyException("Invalid command format. Please specify a valid task number.");
        }
    }

    /**
     * Marks a task as completed and saves the updated task list.
     * Displays confirmation message to the user.
     *
     * @param tasks the list of tasks
     * @param taskNumber the task number to mark as done
     * @throws TweetyException if the task number is invalid
     */
    private static void handleMarkCommand(ArrayList<Task> tasks, int taskNumber)
            throws TweetyException {
        // Mark task as done
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();

        // Save the task
        Storage.saveTasks(tasks);

        // Print Response to marking of tasks
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Nice! I've marked this task as done:");
        System.out.println("          " + task.getStatusIcon() + " " + task.getDescription());
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Unmarks a task and saves the updated task list.
     * Displays confirmation message to the user.
     *
     * @param tasks the list of tasks
     * @param taskNumber the task number to unmark
     * @throws TweetyException if the task number is invalid
     */
    private static void handleUnmarkCommand(ArrayList<Task> tasks, int taskNumber)
            throws TweetyException {
        // Unmark the task
        Task task = tasks.get(taskNumber - 1);
        task.unmark();

        // Save the task
        Storage.saveTasks(tasks);

        // Print response to marking of tasks
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "OK, I've marked this task as not done yet:");
        System.out.println("          " + task.getStatusIcon() + " " + task.getDescription());
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Deletes a task from the task list and saves the updated list.
     * Updates the task count and displays confirmation message to the user.
     *
     * @param tasks the list of tasks
     * @param taskNumber the task number to delete
     * @throws TweetyException if the task number is invalid
     */
    private static void handleDeleteCommand(ArrayList<Task> tasks, int taskNumber)
            throws TweetyException {
        // Remove the task
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        taskCount--;

        // Save the task
        Storage.saveTasks(tasks);

        // Print response to removal of tasks
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Noted. I've removed this task:");
        System.out.println("          " + task.toString());
        System.out.println(FORMATTING_GAP_DEFAULT + "Now you have " + taskCount + " tasks in the list.");
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays the introduction message when the application starts.
     */
    private static void handleIntroCommand() {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Hello! I'm Tweety");
        System.out.println(FORMATTING_GAP_DEFAULT + "What can I do for you?");
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Displays all tasks in the current task list with their numbering and status.
     *
     * @param tasks the list of tasks to display
     * @throws TweetyException if there is an error accessing the task list
     */
    private static void handleListCommand(ArrayList<Task> tasks)
            throws TweetyException {
        // Print the list of tasks
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task currTask = tasks.get(i);
            System.out.println(FORMATTING_GAP_DEFAULT + (i + 1) + ". " + currTask.toString());
        }
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Handles the exit command by saving all tasks and displaying goodbye message.
     */
    private static void handleExitCommand() {
        // Save the tasks before exiting
        Storage.saveTasks(tasks);

        // Print goodbye message
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Bye. Hope to see you again soon!");
        System.out.println(FORMATTING_LINE);
    }

    /**
     * Handles the event command to create a new event task.
     * Extracts the event description, start time and end time from user input.
     *
     * @param userInput the user's input string containing event details
     * @throws TweetyException if the event format is invalid or description is empty
     */
    private static void handleEventCommand(String userInput) throws TweetyException{
        if (userInput.length() <= 5) {
            throw new TweetyException("Event description cannot be empty.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. event project meeting /from Mon 2pm /to 4pm");
        }
        String[] parts = userInput.substring(5).split("/from|/to");
        if (parts[0].trim().isEmpty() || !userInput.contains("/from") || !userInput.contains("/to")) {
            throw new TweetyException("Event description is of invalid format.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. event project meeting /from Mon 2pm /to 4pm");
        }

        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        tasks.add(new Event(description, from, to));

        Storage.saveTasks(tasks);
    }

    /**
     * Handles the todo command to create a new todo task.
     * Extracts the task description from user input.
     *
     * @param userInput the user's input string containing todo description
     * @throws TweetyException if the todo description is empty
     */
    private static void handleTodoCommand(String userInput) throws TweetyException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new TweetyException("Todo description cannot be empty.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. todo borrow book");
        }

        tasks.add(new ToDo(description));

        Storage.saveTasks(tasks);
    }

    /**
     * Handles the deadline command to create a new deadline task.
     * Extracts the task description and deadline from user input.
     *
     * @param userInput the user's input string containing deadline details
     * @throws TweetyException if the deadline format is invalid or description is empty
     */
    private static void handleDeadlineCommand(String userInput) throws TweetyException {
        if (userInput.length() <= 8) {
            throw new TweetyException("Deadline description cannot be empty.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. deadline borrow book /by Sunday");
        }

        String[] parts = userInput.substring(8).split("/by", 2);
        if (parts[0].trim().isEmpty() || !userInput.contains("/by")) {
            throw new TweetyException("Deadline description is of invalid format.\n"
                    + FORMATTING_GAP_DEFAULT
                    + "Please follow this format: e.g. deadline borrow book /by Sunday");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();
        tasks.add(new Deadline(description, by));

        Storage.saveTasks(tasks);
    }

    /**
     * Handles the confirmation message and task count update after adding a new task.
     */
    private static void handleTaskAddition() {
        // Print response to addition of tasks
        Task addedTask = tasks.get(taskCount);
        if (addedTask != null) {
            System.out.println(FORMATTING_LINE);
            System.out.println(FORMATTING_GAP_DEFAULT + "Got it. I've added this task:");
            System.out.println("          " + addedTask.toString());
            System.out.println(FORMATTING_GAP_DEFAULT + "Now you have " + (taskCount + 1)
                    + " tasks in the list");
            System.out.println(FORMATTING_LINE);
            taskCount++;
        }
    }
}
