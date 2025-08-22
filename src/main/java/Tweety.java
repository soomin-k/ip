import java.util.Scanner;

public class Tweety {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // define scanner to read user inputs
        Task[] tasks = new Task[100]; // storage for array of tasks that get created
        int taskCount = 0;

        System.out.println("     ____________________________________________________________");
        System.out.println("     Hello! I'm Tweety");
        System.out.println("     What can I do for you?");
        System.out.println("     ____________________________________________________________");

        while (true) {
            String userInput = sc.nextLine();

            try {
                if (userInput.startsWith("mark")) {
                    int taskNumber = getTaskNumber(userInput);
                    if (taskNumber > taskCount || taskNumber <= 0) {
                        throw new TweetyException("Please provide a valid task number.");
                    } else {
                        Task task = tasks[taskNumber - 1];
                        task.markAsDone();
                        System.out.println("     ____________________________________________________________");
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("          " + task.getStatusIcon() + " " + task.getDescription());
                        System.out.println("     ____________________________________________________________");
                    }
                } else if (userInput.startsWith("unmark")) {
                    int taskNumber = getTaskNumber(userInput);
                    if (taskNumber > taskCount || taskNumber <= 0) {
                        throw new TweetyException("Please provide a valid task number.");
                    } else {
                        Task task = tasks[taskNumber - 1];
                        task.unmark();
                        System.out.println("     ____________________________________________________________");
                        System.out.println("     OK, I've marked this task as not done yet:");
                        System.out.println("          " + task.getStatusIcon() + " " + task.getDescription());
                        System.out.println("     ____________________________________________________________");
                    }
                } else if (userInput.equals("list")) {
                    int index = 0;
                    System.out.println("     ____________________________________________________________");
                    System.out.println("     Here are the tasks in your list:");
                    while (tasks[index] != null) {
                        Task currTask = tasks[index];
                        System.out.println("     " + (index + 1) + ". " + currTask.toString());
                        index++;
                    }
                    System.out.println("     ____________________________________________________________");
                } else if (userInput.equals("bye")) {
                    System.out.println("     ____________________________________________________________");
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println("     ____________________________________________________________");
                    break;
                } else {
                    if (taskCount >= 100) {
                        throw new TweetyException("Task list is full. you cannot add more tasks.");
                    }
                    if (userInput.startsWith("event")) {
                        if (userInput.length() <= 5) {
                            throw new TweetyException("Event description cannot be empty.\n" +
                                    "     Please follow this format: e.g. event project meeting /from Mon 2pm /to 4pm");
                        }
                        String[] parts = userInput.substring(5).split("/from|/to");
                        if (parts[0].trim().isEmpty() || !userInput.contains("/from") || !userInput.contains("/to")) {
                            throw new TweetyException("Event description is of invalid format.\n" +
                                    "     Please follow this format: e.g. event project meeting /from Mon 2pm /to 4pm");
                        } else {
                            String description = parts[0].trim();
                            String from = parts[1].trim();
                            String to = parts[2].trim();
                            tasks[taskCount] = new Event(description, from, to);
                        }
                    } else if (userInput.startsWith("todo")) {
                        String description = userInput.substring(4).trim();
                        if (description.isEmpty()) {
                            throw new TweetyException("Todo description cannot be empty.\n" +
                                    "     Please follow this format: e.g. todo borrow book");
                        }
                        tasks[taskCount] = new ToDo(description);
                    } else if (userInput.startsWith("deadline")) {
                        if (userInput.length() <= 8) {
                            throw new TweetyException("Deadline description cannot be empty.\n" +
                                    "     Please follow this format: e.g. deadline borrow book /by Sunday");
                        }
                        String[] parts = userInput.substring(8).split("/by", 2);
                        if (parts[0].trim().isEmpty() || !userInput.contains("/by")) {
                            throw new TweetyException("Deadline description is of invalid format.\n" +
                                    "     Please follow this format: e.g. deadline borrow book /by Sunday");
                        }
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        tasks[taskCount] = new Deadline(description, by);
                    } else {
                        throw new TweetyException("Invalid command. Please try again.");
                    }

                    Task addedTask = tasks[taskCount];
                    if (addedTask != null) {
                        System.out.println("     ____________________________________________________________");
                        System.out.println("     " + "Got it. I've added this task:");
                        System.out.println("          " + addedTask.toString());
                        System.out.println("     " + "Now you have " + (taskCount + 1) + " tasks in the list");
                        System.out.println("     ____________________________________________________________");
                        taskCount++;
                    }
                }
            } catch (TweetyException e){
                System.out.println("     ____________________________________________________________");
                System.out.println("     " + e.getMessage());
                System.out.println("     ____________________________________________________________");
            }
        }

    }

    private static int getTaskNumber(String userInput) throws TweetyException {
        try {
            String taskNumberStr = userInput.substring(userInput.indexOf(" ") + 1).trim();
            return Integer.parseInt(taskNumberStr);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new TweetyException("Invalid command format. Please specify a valid task number.");
        }
    }

}
