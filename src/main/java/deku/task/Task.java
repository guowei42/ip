package deku.task;

import deku.DekuExceptions;
import deku.InputParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Main parent Task class
 */
public class Task {
    private final List<String> taskArray;
    private final String task;
    private final String icon;
    private Boolean completionStatus;
    private String completionIcon;
    private final InputParser parser = new InputParser();

    Task(List<String> task, String taskName, String icon) throws DekuExceptions {
        if (task.size() == 0) {
            throw new DekuExceptions("The description of a " + taskName + " cannot be empty.");
        }

        this.task = parser.parseTask(task);
        this.taskArray = task;
        this.icon = icon;
        this.completionStatus = false;
        this.completionIcon = "[ ]";
    }

    private String getTask() {
        String output = "";
        for (int i = 0; i < taskArray.size(); i++) {
            String current = taskArray.get(i);
            if (current.charAt(0) == '/') {
                break;
            }
            output += current + " ";
        }
        return (output.equals("")) ? output : output.substring(0, output.length() - 1);
    }

    public LocalDate getDate() {
        return parser.getDate();
    }

    public LocalTime getTime() {
        return parser.getTime();
    }

    private String getSpecial() {
        String output = "";
        for (int i = 0; i < taskArray.size(); i++) {
            String current = taskArray.get(i);
            if (current.charAt(0) == '/') {
                output = current;
                break;
            }
        }
        return output;
    }

    /**
     * Alternates Task's status of whether it is completed
     *
     * @param set 1 = Task completed, 0 = Task incomplete
     */
    public void setCompletionStatus(boolean set) {
        completionStatus = set;
        if (completionStatus) {
            completionIcon = "[X]";
        } else {
            completionIcon = "[ ]";
        }
    }

    /**
     * Parse the current task to custom data structure to write to file for storage
     *
     * @return custom data structure for file
     */
    public String saveFormat() {
        String completionParse = (completionIcon.equals("[X]")) ? "1" : "0";
        return icon + "|" + completionParse + "|" + getTask() + "|" + getSpecial() + "|" + getDate() + "|" + getTime();
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + completionIcon + " - " + task;
    }
}