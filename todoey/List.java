/**
 * @author Narayan Sajeev
 * List-managing app
 */
// create package
package todoey;

// import ArrayList
import java.util.ArrayList;

// create class
public class List {

  // create fields
  private String name;
  private boolean pinned;
  private ArrayList<Task> tasks;

  /**
   * Constructor
   * @param name Name
   * @param pinned If list is pinned
   * @param tasks ArrayList of Tasks
   */
  private List(String name, boolean pinned, ArrayList<Task> tasks) {
    this.name = name;
    this.pinned = pinned;
    this.tasks = tasks;
  }

  /**
   * Simplified constructor using {@link #List(String, boolean, ArrayList)}
   * @param name Name of list
   */
  public List(String name) {
    this(name, false, new ArrayList<>());
  }

  /**
   * Create task
   * @param t Task
   */
  private void createTask(Task t) {
    tasks.add(t);
  }

  /**
   * Get name of list
   * @return Name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Set name of list
   * @param name Name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Checks if list is pinned
   * @return If list is pinned
   */
  public boolean isPinned() {
    return this.pinned;
  }

  /**
   * Pins list
   */
  public void pin() {
    this.pinned = true;
  }

  /**
   * Unpins list
   */
  public void unpin() {
    this.pinned = false;
  }

  /**
   * Gets tasks
   * @return ArrayList of Tasks
   */
  public ArrayList<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Sets tasks
   * @param tasks Tasks
   */
  private void setTasks(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }
}
