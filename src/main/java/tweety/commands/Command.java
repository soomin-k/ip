package tweety.commands;

import tweety.exceptions.TweetyException;
import tweety.tasks.TaskList;
import tweety.ui.Ui;
import tweety.storage.Storage;

public abstract class Command {
    public enum CommandType {
        MARK, UNMARK, DELETE, LIST, BYE, EVENT, TODO, DEADLINE, FIND
    }

    private CommandType type;

    public Command(CommandType type) {
        this.type = type;
    }

    public CommandType getType() {
        return type;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TweetyException;

    public abstract boolean isExit();
}