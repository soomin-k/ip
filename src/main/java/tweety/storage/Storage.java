package tweety.storage;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

import tweety.tasks.Deadline;
import tweety.tasks.Event;
import tweety.tasks.Task;
import tweety.tasks.ToDo;

/**
 * Handles storage operations for tasks such as saving to and loading from file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/Tweety.txt";
    private static final String DIRECTORY_PATH = "./data";

    /**
     * Checks if directory for the storage file exists.
     *
     * @throws IOException if no storage file exists in the directory path
     */
    private static void ensureDirectoryExists() throws IOException {
        Path path = Paths.get(DIRECTORY_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    /**
     * Saves the input list of tasks in the storage file.
     * Creates the directory structure if it doesn't exist.
     *
     * @param tasks the list of tasks to be saved.
     */
    public static void saveTasks(List<Task> tasks) {
        try {
            // Check if directory exists first
            ensureDirectoryExists();

            Path filePath = Paths.get(FILE_PATH);
            List<String> lines = new ArrayList<>();

            // Convert each task to string format
            for (int i = 0; i < tasks.size(); i++) {
                lines.add(convertTaskToFileString(tasks.get(i)));
            }

            // Write to file
            Files.write(filePath, lines);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the Tweety.txt file.
     *
     * @return a list of tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path filePath = Paths.get(FILE_PATH);
            if (!Files.exists(filePath)) {
                return tasks;
            }

            // Read the tasks from the string file
            List<String> lines = Files.readAllLines(filePath);
            for (int i = 0; i < lines.size(); i++) {
                // Convert the string to task object
                Task currTask =  parseTaskFromString(lines.get(i));

                // Add the task to the list of tasks to return
                if (currTask != null) {
                    tasks.add(currTask);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Converts tasks into strings to be saved in the storage file.
     *
     * @param task task to convert to string to be saved in the storage file.
     * @return String of the task.
     */
    private static String convertTaskToFileString(Task task) {
        if (task instanceof ToDo) {
            return "T | " + task.getStatusIcon() + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + task.getStatusIcon() + " | " + task.getDescription() + " | "
                    + ((Deadline) task).getDeadline();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + task.getStatusIcon() + " | " + task.getDescription()
                    + " | " + event.getEventStart() + " | " + event.getEventEnd();
        }
        return "";
    }

    /**
     * Parses tasks from string to tasks when "list" command is used.
     *
     * @return Task object by reading the strings in the file.
     */
    private static Task parseTaskFromString(String line) {
        // Split the string input into its type, isDone and description
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("[X]");
        String description = parts[2];

        // Return the string as respective task objects
        switch (type) {
            case "T":
                ToDo todo = new ToDo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
                // Fallthrough
            case "D":
                if (parts.length < 4) return null;
                Deadline deadline = new Deadline(description, parts[3]);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
                // Fallthrough
            case "E":
                if (parts.length < 5) return null;
                Event event = new Event(description, parts[4], parts[5]);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
                // Fallthrough
            default:
                return null;
                // Fallthrough
        }
    }
}
