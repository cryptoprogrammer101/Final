/**
 * @author Narayan Sajeev
 * List-managing app
 */
// create package
package todoey;

import java.util.ArrayList;
import java.util.Scanner;

// create class
public class ListTools {

    // create list
    private static List l;

    // create tasks
    private static ArrayList<Task> tasks;

    // create Scanner object
    private static Scanner s = new Scanner(System.in);

    /**
     * Opens list
     * @param lst List
     */
    public static void openList(List lst) {

        // update list
        l = lst;

        // update tasks
        tasks = l.getTasks();

        // set flag variable
        boolean run = true;

        // repeat until user exits
        while (run) {

            // print menu
            menu();

            // retrieve user input
            String t = inp("Enter: ");
            t = t.toLowerCase();

            // check user input cases
            switch (t) {

                // if user entered print menu
                case "m":
                    menu();
                    break;

                // if user entered print tasks
                case "p":

                    // if there are tasks
                    if (!noTasks()) {
                        // print tasks
                        printTasks();
                        pause();
                    }

                    break;

                // if user entered create task
                case "c":
                    createTask();
                    break;

                // if user entered add color
                case "l":
                    addColor();
                    break;

                // if user entered add date
                case "d":
                    addDate();
                    break;

                // if user entered delete task
                case "r":
                    deleteTask();
                    break;

                // if user entered star task
                case "s":
                    starTask();
                    break;

                // if user entered star task
                case "u":
                    unstarTask();
                    break;

                // if user entered complete task
                case "o":
                    completeTask();
                    break;

                // if user entered exit program
                case "e":
                    run = false;
                    // beep
                    Styles.beep();
                    exit();
                    break;

                // if user gave invalid input
                default:
                    // beep
                    Styles.beep();
                    // print to user
                    pause("\nInvalid input. ");
                    break;
            }

        }

    }


    /**
	 * Prints texts & clears formatting
	 * @param t String to print
	 */
	private static void print(String t) {
		System.out.print(t + Styles.RESET);
	}

	/**
	 * Clears formatting using {@link #print(String)}
	 */
	private static void print() {
		print("");
	}

	/**
	 * Prints with newline using {@link #print(String)}
	 * @param t String to print
	 */
	private static void println(String t) {
		print(t + "\n");
	}

	/**
	 * Prints newline using {@link #println(String)}
	 */
	private static void println() {
		println("");
	}

	/**
	 * Retrieves input from user
	 * @param t String to print
	 * @return User input
	 */
	private static String inp(String t) {
		// print text
		print(t);
		// change text color to green
		System.out.print(Styles.GREEN_BT);
		// retrieve text
		t = s.nextLine();
		// clear styles
		print();
		// return text
		return t;
	}

	/**
	 * Waits for enter key to be pressed
	 * @param t String to print
	 */
	private static void pause(String t) {
		// print text
		print(t + "Press enter to continue.");
		// wait for enter
		s.nextLine();
		// clear screen
		Styles.cls();
	}

	/**
	 * Waits for enter key to be pressed using {@link #pause(String)}
	 */
	private static void pause() {
		// pause
		pause("\n");
	}

	/**
	 * Special print command menu
	 * @param t First String
	 * @param u Second String
	 */
	private static void menuPrint(String t, String u) {
		print(Styles.GREEN_BT + t);
		println(" : " + u);
	}

    /**
     * Prints menu using {@link #menuPrint(String, String)}
     */
    private static void menu() {

        // clear screen
        Styles.cls();

        // print to user
        Styles.hr();
        print(Styles.GREEN_BT + "Opened list ");
        print(Styles.GREEN_BG_BT + l.getName());
        println(Styles.GREEN_BT + ". To start, press:\n");
        menuPrint("M", "Print menu");
        menuPrint("P", "Print tasks");
        menuPrint("C", "Create task");
        menuPrint("R", "Delete task");
        menuPrint("L", "Add color to task");
        menuPrint("D", "Add date to task");
        menuPrint("S", "Star task");
        menuPrint("U", "Un-star task");
        menuPrint("O", "Complete task");
        menuPrint("E", "Exit list\n");
        Styles.hr();

    }

    /**
     * Checks if there are existing  {@link #tasks}
     * @return if task is found
     */
    private static boolean noTasks() {
        // if there are no tasks
        if (tasks.size() == 0) {
            // beep
            Styles.beep();
            // print to user
            pause("\nNo existing tasks.\nStart by creating new task. ");
            return true;
        }

        return false;

    }

    /**
     * Prints {@link #tasks}
     */
    private static void printTasks() {

        // clear screen
        Styles.cls();

        // print to user
        print("List \"" + Styles.GREEN_BT + l.getName());
        print("\":");

        // if task is pinned
        if (l.isPinned()) {

            // print pinned
            print(Styles.YELLOW_BT + " (PINNED)");

        }

        println("\n");

        // get number of tasks
        int numTasks = tasks.size();

        // if there are no tasks
        if (numTasks == 0) {
            // print that there is no tasks
            println("No existing tasks.");
        }

        // loop through tasks
        for (int i = 0; i < numTasks; i++) {
            // get task
            Task t = tasks.get(i);
            // print task title
            print((i + 1) + ". " + t.getName());

            // if task is starred
            if (t.isStarred()) {
                // print star
                print(Styles.YELLOW_BT + " \u2b50");
            }

            println();

        }

        println();

    }

    /**
     * Retrieves task names
     * @return Array of names
     */
    private static String[] taskNames() {

        // create array with same length as tasks
        String[] names = new String[tasks.size()];

        // loop through tasks
        for (int i = 0; i < tasks.size(); i++) {

            // retrieve name
            String n = tasks.get(i).getName();

            // update array
            names[i] = n.toLowerCase();

        }

        return names;
    }

    /**
	 * Finds index given task name
	 * @param n Task name
	 * @return Index
	 */
    private static int getIndexByName(String n) {

        // convert name to lowercase
        n = n.toLowerCase();

        // get name task
        String[] names = taskNames();

        // loop through names
        for (int i = 0; i < tasks.size(); i++) {

            // if current name is equal to name given
            if (names[i].equals(n)) {

                // return current index
                return i;

            }

        }

        // return -1 to indicate task name was not found
        return -1;

    }

    /**
     * Creates task
     */
    private static void createTask() {

        // create empty user input
        String name = "";

        // while user input is invalid
        while (name.equals("")) {

            printTasks();

            println();

            // get input
            name = inp("Create task with name (\"e\" to exit): ");

            // if name is empty
            if (name.equals("")) {
                // beep
                Styles.beep();
                // wait for enter key
                pause("\nNull task name. ");
                // restart
                continue;
            }

            // if name is "e" (exit code)
            String lowercase = name.toLowerCase();
            if (lowercase.equals("e")) {
                // beep
                Styles.beep();
                // print to user
                pause("\nExiting task creation. ");
                // exit task creation
                return;
            }

            // get index of task
            int i = getIndexByName(name);

            // if name already exists
            if (i > -1) {
                // clear user input
                name = "";
                // beep
                Styles.beep();
                // retrieve name
                Task t = tasks.get(i);
                // print to user
                print("\nTask \"" + Styles.GREEN_BT + t.getName());
                // wait for enter
                pause("\" already exists. ");
                // restart
                continue;
            }

        }

        // create task using name
        Task t = new Task(name);

        // add task to ArrayList
        tasks.add(t);

        // print to user
        print("\nCreated task \"" + Styles.GREEN_BT + t.getName());
        pause("\". ");

    }

    /**
     * Adds color
     */
    private static void addColor() {

        // if there are no tasks
        if (noTasks()) {
            // stop
            return;
        }

    }

    /**
     * Adds date
     */
    private static void addDate() {

        // if there are no tasks
        if (noTasks()) {
            // stop
            return;
        }

    }

    /**
     * Deletes task
     */
    private static void deleteTask() {

        // if there are no tasks
        if (noTasks()) {
            // stop
            return;
        }

        // create empty user input
        String name = "";

        // create index of task to delete
        int i = -1;

        // while user input is invalid
        while (name.equals("")) {

            printTasks();

            println();

            // get input
            name = inp("Delete task with name (\"e\" to exit): ");

            // if name is empty
            if (name.equals("")) {
                // beep
                Styles.beep();
                // wait for enter key
                pause("\nNull task name. ");
                // restart
                continue;
            }

            // if name is "e" (exit code)
            String lowercase = name.toLowerCase();
            if (lowercase.equals("e")) {
                // beep
                Styles.beep();
                // print to user
                pause("\nExiting task deletion. ");
                // exit task deletion
                return;
            }

            // get index of task
            i = getIndexByName(name);

            // if name was not found in task
            if (i < 0) {
                // beep
                Styles.beep();
                // print to user
                print("\nInvalid input. Task \"" + Styles.GREEN_BT + name);
                // wait for enter
                pause("\" does not exist.\nMake sure to check spelling. ");
                // clear user input
                name = "";
                // restart
                continue;

            }

        }

        // retrieve task
        Task t = tasks.get(i);

        // delete task
        tasks.remove(t);

        // print to user
        print("\nDeleted task \"" + Styles.GREEN_BT + t.getName());
        pause("\". ");

    }

    /**
     * Stars task
     */
    private static void starTask() {

        // if there are no tasks
        if (noTasks()) {
            // stop
            return;
        }

        // create empty user input
        String name = "";

        // create index of task to pin
        int i = -1;

        // while user input is invalid
        while (name.equals("")) {

            printTasks();

            println();

            // get input
            name = inp("Star task with name (\"e\" to exit): ");

            // if name is empty
            if (name.equals("")) {
                // beep
                Styles.beep();
                // wait for enter key
                pause("\nNull task name. ");
                // restart
                continue;
            }

            // if name is "e" (exit code)
            String lowercase = name.toLowerCase();
            if (lowercase.equals("e")) {
                // beep
                Styles.beep();
                // print to user
                pause("\nExiting task starring. ");
                // exit task
                return;
            }

            // get index of task
            i = getIndexByName(name);

            // if name was not found in task
            if (i < 0) {
                // beep
                Styles.beep();
                // print to user
                print("\nInvalid input. Task \"" + Styles.GREEN_BT + name);
                // wait for enter key
                pause("\" does not exist. ");
                // clear user input
                name = "";
                // restart
                continue;
            }

        }

        // retrieve task
        Task t = tasks.get(i);

        // set pinned task to pinned
        t.star();

        // print to user
        print("\nStarred task \"" + Styles.GREEN_BT + t.getName());
        pause("\". ");

    }

    /**
     * Checks if starred taske xists
     * @return starred task exists
     */
    private static boolean starredExists() {
        // loop through tasks
        for (Task t : tasks) {
            // if task is starred
            if (t.isStarred()) {
                // return true
                return true;
            }
        }

        // otherwise return false
        return false;

    }

    /**
     * Un-star task
     */
    private static void unstarTask() {

        // if there are no tasks
        if (noTasks()) {
            // stop
            return;
        }

        // if no task is starred
        if (!starredExists()) {
            // beep
            Styles.beep();
            // wait for enter key
            pause("\nNo starred task exists. ");
            // exit
            return;
        }

        // create empty user input
        String name = "";

        // create index of task to unstar
        int i = -1;

        // while user input is invalid
        while (name.equals("")) {

            printTasks();

            println();

            // get input
            name = inp("Un-star task with name (\"e\" to exit): ");

            // if name is empty
            if (name.equals("")) {
                // beep
                Styles.beep();
                // wait for enter key
                pause("\nNull task name. ");
                // restart
                continue;
            }

            // if name is "e" (exit code)
            String lowercase = name.toLowerCase();
            if (lowercase.equals("e")) {
                // beep
                Styles.beep();
                // print to user
                pause("\nExiting task un-starring. ");
                // exit task
                return;
            }

            // get index of task
            i = getIndexByName(name);

            // if name was not found in task
            if (i < 0) {
                // beep
                Styles.beep();
                // print to user
                print("\nInvalid input. Task \"" + Styles.GREEN_BT + name);
                // wait for enter key
                pause("\" does not exist. ");
                // clear user input
                name = "";
                // restart
                continue;
            }

            // retrieve task
            Task current = tasks.get(i);

            // if task is not pinned
            if (!current.isStarred()) {
                // beep
                Styles.beep();
                // print to user
                print("\nInvalid input. Task \"" + Styles.GREEN_BT + current.getName());
                // wait for enter key
                pause("\" is not starred. ");
                // clear user input
                name = "";
                // restart
                continue;
            }

        }

        // retrieve task
        Task t = tasks.get(i);

        // set task to unpinned
        t.star();

        // print to user
        print("\nUn-starred task \"" + Styles.GREEN_BT + t.getName());
        pause("\". ");


    }

    /**
     * Completes task
     */
    private static void completeTask() {

        // if there are no tasks
        if (noTasks()) {
            // stop
            return;
        }

    }

    /**
     * Exits list
     */
    private static void exit() {
        // print to user
        print("\nExiting list \"" + Styles.GREEN_BT + l.getName());
        // wait for enter
        pause("\". ");
    }


}
