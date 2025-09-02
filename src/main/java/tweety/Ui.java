package tweety;

import tweety.tasks.Task;

import java.util.Scanner;

public class Ui {
    private static final String FORMATTING_GAP_DEFAULT = "     ";
    private static final String FORMATTING_LINE = FORMATTING_GAP_DEFAULT
            + "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printWelcomeMessage() {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Hello! I'm Tweety");
        System.out.println(FORMATTING_GAP_DEFAULT + "What can I do for you?");
        System.out.println(FORMATTING_LINE);
    }

    public void printExitMessage() {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Bye. Hope to see you again soon!");
        System.out.println(FORMATTING_LINE);
    }

    public void printTaskList(TaskList tasks) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task currTask = tasks.getTask(i);
            System.out.println(FORMATTING_GAP_DEFAULT + (i + 1) + ". " + currTask);
        }
        System.out.println(FORMATTING_LINE);
    }

    public void printAddedTask(Task addedTask, int taskCount) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Got it. I've added this task:");
        System.out.println("          " + addedTask);
        System.out.println(FORMATTING_GAP_DEFAULT + "Now you have " + taskCount
                + " tasks in the list");
        System.out.println(FORMATTING_LINE);
    }

    public void printDeletedTask(Task deletedTask, int taskCount) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Noted. I've removed this task:");
        System.out.println("          " + deletedTask);
        System.out.println(FORMATTING_GAP_DEFAULT + "Now you have " + taskCount + " tasks in the list.");
        System.out.println(FORMATTING_LINE);
    }

    public void printUnmarkedTask(Task unmarkedTask) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "OK, I've marked this task as not done yet:");
        System.out.println("          " + unmarkedTask);
        System.out.println(FORMATTING_LINE);
    }

    public void printMarkedTask(Task markedTask) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + "Nice! I've marked this task as done:");
        System.out.println("          " + markedTask);
        System.out.println(FORMATTING_LINE);
    }

    public void printErrorMessage(Exception e) {
        System.out.println(FORMATTING_LINE);
        System.out.println(FORMATTING_GAP_DEFAULT + e.getMessage());
        System.out.println(FORMATTING_LINE);
    }

    public void closeScanner() {
        scanner.close();
    }
}
