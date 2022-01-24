/**
 * @author Narayan Sajeev
 * List-managing app
 */
package todoey;

/**
 * Special type of task with date
 * Inherits from {@link Task} superclass
 */
public class DateTask extends Task {

    // define class name
    public static final String CLASSNAME = DateTask.class.getName();

    // create date
    private String date;

    /**
     * Creates constructor using superclass constructor
     * @param t Task to convert
     * @param date Date of task
     */
    public DateTask(Task t, String date) {

        // call superclass constructor
        super(t.getName(), t.isCompleted(), t.isStarred());

        // create date
        this.date = date;

    }

    /**
     * Gets date
     * @return Date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date
     * @param date Date of task
     */
    public void setDate(String date) {
        this.date = date;
    }
}
