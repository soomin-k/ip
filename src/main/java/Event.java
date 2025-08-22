public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + getDescription()+ " (from: " + this.from + " to: " + this.to + ")";
    }

    public String getEventStart() {
        return this.from;
    }

    public String getLaterStart() {
        return this.to;
    }
}
