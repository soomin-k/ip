package tweety.commands;

public class Command {
    public enum CommandType {
        MARK, UNMARK, DELETE, LIST, BYE, EVENT, TODO, DEADLINE, INVALID
    }

    private CommandType type;
    private String description;
    private String from;
    private String to;
    private String by;
    private int taskNumber;

    public Command(CommandType type) {
        this.type = type;
    }

    public CommandType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getBy() {
        return by;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }
}
