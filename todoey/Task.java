/**
 * @author Narayan Sajeev
 * List-managing app
 */
// create package
package todoey;

/**
 * Create Task class
 */
public class Task {

  // create fields
  private String name;
  private boolean completed;
  private boolean starred;

  /**
   * Constructor
   * @param name Name of task
   * @param completed If task is completed
   * @param starred If task is starred
   */
  public Task(String name, boolean completed, boolean starred){
    this.name = name;
    this.completed = completed;
    this.starred = starred;
  }

  /**
   * Simplified constructor using {@link #Task(String, boolean, boolean)}
   * @param name Name of task
   */
  public Task(String name) {
    this(name, false, false);
  }

  /**
   * Gets name
   * @return Name
   */
  public String getName() {
    return this.name;
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
   * Makes task unstarred
   */
  public void unstar() {
    this.starred = false;
  }

  /**
   * Checks if task is completed
   * @return If task is completed
   */
  public boolean isCompleted() {
    return this.completed;
  }

  /**
   * Completes task
   */
  public void complete() {
    this.completed = true;
  }

  /**
   * Makes task uncomplete
   */
  public void uncomplete() {
    this.completed = false;
  }
}
