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

            if (userInput.startsWith("mark")) {
                int taskNumber = getTaskNumber(userInput);
                if (taskNumber == -1) {
                    continue;
                }

                if (taskNumber > taskCount || taskNumber <= 0) {
                    invalidTask();
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
                    invalidTask();
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

                if (userInput.startsWith("event")) {
                    String[] parts = userInput.substring(6).split("/from|/to");
                    if (parts.length < 3 || parts[0].trim().isEmpty()) {
                            System.out.println("     ____________________________________________________________");
                            System.out.println("     OOPS!!! Invalid event format.");
                            System.out.println("     ____________________________________________________________");
                    } else {
                            String description = parts[0].trim();
                            String from = parts[1].trim();
                            String to = parts[2].trim();
                            tasks[taskCount] = new Event(description, from, to);
                    }
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(5).trim();
                    tasks[taskCount] = new ToDo(description);
                } else if (userInput.startsWith("deadline")) {
                    String[] parts = userInput.substring(9).split("/by", 2);
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    tasks[taskCount] = new Deadline(description, by);
                }

                Task addedTask = tasks[taskCount];
                System.out.println("     ____________________________________________________________");
                System.out.println("     " + "Got it. I've added this task:");
                System.out.println("          " + addedTask.toString());
                System.out.println("     " + "Now you have " + (taskCount + 1) + " tasks in the list");
                System.out.println("     ____________________________________________________________");
                taskCount++;
            }
        }

    }

    private static int getTaskNumber(String userInput) {
        try {
            String taskNumberStr = userInput.substring(userInput.indexOf(" ") + 1).trim();
            return Integer.parseInt(taskNumberStr);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("     ____________________________________________________________");
            System.out.println("     Invalid command");
            System.out.println("     ____________________________________________________________");
            return -1;
        }
    }

    private static void invalidTask() {
        System.out.println("     ____________________________________________________________");
        System.out.println("     Invalid task number.");
        System.out.println("     ____________________________________________________________");
    }
}
