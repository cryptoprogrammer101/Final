/**
 * @author Narayan Sajeev
 * List-managing app
 */
// create package
package todoey;

// create class
public class Task {

  // create fields
  private String name;
  private boolean starred;
  private boolean completed;
  private String date;
  private String color;

  /**
   * Constructor
   * @param name Name of task
   * @param completed If task is completed
   * @param starred If task is starred
   * @param date Due date for task
   * @param color Color of task
   */
  private Task(String name, boolean completed, boolean starred, String date, String color) {
    this.name = name;
    this.starred = starred;
    this.date = date;
    this.color = color;
  }

  /**
   * Simplified constructor using {@link #Task(String, boolean, boolean, String, String)}
   * @param name Name of task
   */
  public Task(String name) {
    this(name, false, false, "No date", "black");
  }

  /**
   * Gets name
   * @return Name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets name
   * @param name Name
   */
  private void setName(String name) {
    this.name = name;
  }

  /**
   * Checks if task is starred
   * @return If task is starred
   */
  public boolean isStarred() {
    return this.starred;
  }

  /**
   * Makes task starred
   */
  public void star() {
    this.starred = true;
  }

  /**
   * Checks if task is completed
   * @return If task is completed
   */
  private boolean isCompleted() {
    return this.completed;
  }

  /**
   * Complete task
   */
  private void complete() {
    this.completed = true;
  }

  /**
   * Get due date of task
   * @return Date
   */
  private String getDate() {
    return this.date;
  }

  /**
   * Set due date of task
   * @param date Date
   */
  private void setDate(String date) {
    this.date = date;
  }

  /**
   * Get color of task
   * @return Color
   */
  private String getColor() {
    return this.color;
  }

  /**
   * Set color of task
   * @param color Color
   */
  private void setColor(String color) {
    this.color = color;
  }
}
