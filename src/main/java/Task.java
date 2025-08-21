public class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    public String getDescription() {
        return this.description;
    }


    public void markAsDone() {
        isCompleted = true;
    }

    public void unmark() {
        isCompleted = false;
    }

}
