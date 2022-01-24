/**
 * @author Narayan Sajeev
 * List-managing app
 */
package todoey;

/**
 * Special type of task with color
 * Inherits from {@link Task} superclass
 */
public class ColorTask extends Task {

    // define class name
    public static final String CLASSNAME = ColorTask.class.getName();

    // create color
    private String color;

    /**
     * Creates constructor using superclass constructor
     * @param t Task to convert
     * @param color Color of task
     */
    public ColorTask(Task t, String color) {

        // call superclass constructor
        super(t.getName(), t.isCompleted(), t.isStarred());

        // create color
        this.color = color;

    }

    /**
     * Gets color
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets color
     * @param color Color of task
     */
    public void setColor(String color) {
        this.color = color;
    }
}
