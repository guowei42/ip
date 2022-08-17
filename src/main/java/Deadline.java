import java.util.List;

public class Deadline extends Task {
    private final String ICON = "[D]";
    Deadline(List<String> task) throws DekuExceptions {
        super(task, "deadline");
    }

    @Override
    public String toString() {
        return this.ICON + super.toString();
    }
}