import java.util.Scanner;
import java.util.ArrayList;

public class Tweety {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // define scanner to read user inputs
        ArrayList<Task> tasks = new ArrayList<>(); // storage for array of tasks that get created
        int taskCount = 0; // keep track of the number of tasks

        System.out.println("     ____________________________________________________________");
        System.out.println("     Hello! I'm Tweety");
        System.out.println("     What can I do for you?");
        System.out.println("     ____________________________________________________________");

        while (true) {
            String userInput = sc.nextLine(); // read the input

            try {
                if (userInput.startsWith("mark")) {
                    int taskNumber = getTaskNumber(userInput); // read the task number specified by the user
                    if (taskNumber <= 0 || taskNumber - 1 >= taskCount) {
                        throw new TweetyException("Please provide a valid task number.");
                    } else {
                        Task task = tasks.get(taskNumber - 1);
                        task.markAsDone();
                        System.out.println("     ____________________________________________________________");
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("          " + task.getStatusIcon() + " " + task.getDescription());
                        System.out.println("     ____________________________________________________________");
                    }
                } else if (userInput.startsWith("unmark")) {
                    int taskNumber = getTaskNumber(userInput);
                    if (taskNumber <= 0 || taskNumber - 1 >= taskCount) {
                        throw new TweetyException("Please provide a valid task number.");
                    } else {
                        Task task = tasks.get(taskNumber - 1);
                        task.unmark();
                        System.out.println("     ____________________________________________________________");
                        System.out.println("     OK, I've marked this task as not done yet:");
                        System.out.println("          " + task.getStatusIcon() + " " + task.getDescription());
                        System.out.println("     ____________________________________________________________");
                    }
                } else if (userInput.startsWith("delete")) {
                    int taskNumber = getTaskNumber(userInput);
                    if (taskNumber <= 0 || taskNumber - 1 >= taskCount) {
                        throw new TweetyException("Please provide a valid task number.");
                    } else {
                        Task task = tasks.get(taskNumber - 1);
                        tasks.remove(taskNumber - 1);
                        taskCount--; // update taskCount accordingly
                        System.out.println("     ____________________________________________________________");
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("          " + task.toString());
                        System.out.println("     Now you have " + taskCount + " tasks in the list.");
                        System.out.println("     ____________________________________________________________");
                    }
                } else if (userInput.equals("list")) {
                    System.out.println("     ____________________________________________________________");
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        Task currTask = tasks.get(i);
                        System.out.println("     " + (i + 1) + ". " + currTask.toString());
                    }
                    System.out.println("     ____________________________________________________________");
                } else if (userInput.equals("bye")) {
                    System.out.println("     ____________________________________________________________");
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println("     ____________________________________________________________");
                    break;
                } else {
                    if (userInput.startsWith("event")) {
                        if (userInput.length() <= 5) { // if the user input no description of event throw exception
                            throw new TweetyException("Event description cannot be empty.\n" +
                                    "     Please follow this format: e.g. event project meeting /from Mon 2pm /to 4pm");
                        }
                        // logic to obtain the relevant info used in creating new event
                        String[] parts = userInput.substring(5).split("/from|/to");
                        if (parts[0].trim().isEmpty() || !userInput.contains("/from") || !userInput.contains("/to")) {
                            throw new TweetyException("Event description is of invalid format.\n" +
                                    "     Please follow this format: e.g. event project meeting /from Mon 2pm /to 4pm");
                        } else {
                            String description = parts[0].trim();
                            String from = parts[1].trim();
                            String to = parts[2].trim();
                            tasks.add(new Event(description, from, to));
                        }
                    } else if (userInput.startsWith("todo")) {
                        String description = userInput.substring(4).trim();
                        if (description.isEmpty()) {
                            throw new TweetyException("Todo description cannot be empty.\n" +
                                    "     Please follow this format: e.g. todo borrow book");
                        }
                        tasks.add(new ToDo(description));
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
                        tasks.add(new Deadline(description, by));
                    } else {
                        throw new TweetyException("Invalid command. Please try again.");
                    }

                    Task addedTask = tasks.get(taskCount);
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
