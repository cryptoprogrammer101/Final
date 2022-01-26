/**
 * @author Narayan Sajeev
 * List-managing app
 */
// create package
package todoey;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * List functions
 */
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

                // if user entered delete task
                case "r":
                    deleteTask();
                    break;

                // if user entered star task
                case "s":
                    starTask();
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
                    pause("\nInvalid input");
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
     * Clears formatting using print(String)
     */
    private static void print() {
        print("");
    }

    /**
     * Prints with newline using print(String)
     * @param t String to print
     */
    private static void println(String t) {
        print(t + "\n");
    }

    /**
     * Prints newline using println(String)
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
        System.out.print(Styles.GREEN);
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
        print(t);
        print(". Press enter to continue.");
        // wait for enter
        s.nextLine();
        // clear screen
        Styles.cls();
    }

    /**
     * Waits for enter key to be pressed using pause(String)
     */
    private static void pause() {
        // print text
        print("\nPress enter to continue.");
        // wait for enter
        s.nextLine();
        // clear screen
        Styles.cls();
    }

    /**
     * Special print command menu
     * @param t First String
     * @param u Second String
     */
    private static void menuPrint(String t, String u) {
        print(Styles.GREEN + t);
        println(" : " + u);
    }

    /**
     * Prints menu using menuPrint(String, String)
     */
    private static void menu() {

        // clear screen
        Styles.cls();

        // print to user
        Styles.hr();
        println(Styles.GREEN + "Opened list " + l.getName() + ". To start, press:\n");
        menuPrint("M", "Print menu");
        menuPrint("P", "Print tasks");
        menuPrint("C", "Create task");
        menuPrint("R", "Delete task");
        menuPrint("L", "Add color to task");
        menuPrint("S", "Star task");
        menuPrint("O", "Complete task");
        menuPrint("E", "Exit " + Styles.GREEN + l.getName() + "\n");
        Styles.hr();

    }

    /**
     * Checks if there are existing tasks
     * @return if task is found
     */
    private static boolean noTasks() {
        // if there are no tasks
        if (tasks.size() == 0) {
            // beep
            Styles.beep();
            // print to user
            pause("\nNo existing tasks.\nStart by creating new task");
            return true;
        }

        return false;

    }

    /**
     * Checks if task is instance of ColorTask
     * @param t Task to check
     * @return If task is colored
     */
    public static boolean isColored(Task t) {

        // get Task class
        String classname = t.getClass().getName();

        // compare to ColorTask class name
        return classname.equals(ColorTask.CLASSNAME);
    }

    /**
     * Prints tasks}
     */
    private static void printTasks() {

        // clear screen
        Styles.cls();

        // print to user
        print("List \"" + l.getName() + "\":");

        // if list is pinned
        if (l.isPinned()) {

            // print pinned
            print(Styles.YELLOW + " (PINNED)");

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
            // print task number
            print((i + 1) + ". ");

            // if task has color
            if (isColored(t)) {

                // convert to ColorTask
                ColorTask colorTask = (ColorTask) t;

                // print color
                System.out.print(colorTask.getColor());

            }

            // print task name
            print(t.getName());

            // if task is starred
            if (t.isStarred()) {

                // print star
                print(/*Styles.YELLOW + */" \u2b50");

            }

            // if task is completed
            if (t.isCompleted()) {

                // print check mark
                print(" \u2714");

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
                pause("\nNull task name");
                // restart
                continue;
            }

            // if name is "e" (exit code)
            String lowercase = name.toLowerCase();
            if (lowercase.equals("e")) {
                // beep
                Styles.beep();
                // print to user
                pause("\nExiting task creation");
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
                // wait for enter
                pause("\nTask \"" + t.getName() + "\" already exists");
                // restart
                continue;
            }

        }

        // create task using name
        Task t = new Task(name);

        // add task to ArrayList
        tasks.add(t);

        // wait for enter
        pause("\nCreated task \"" + t.getName() + "\"");

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

        // create empty user input
        String name = "";

        // define index of task
        int i = 0;

        // while user input is invalid
        while (name.equals("")) {

            printTasks();

            println();

            // get input
            name = inp("Color task with name (\"e\" to exit): ");

            // if name is empty
            if (name.equals("")) {
                // beep
                Styles.beep();
                // wait for enter key
                pause("\nNull task name");
                // restart
                continue;
            }

            // if name is "e" (exit code)
            String lowercase = name.toLowerCase();
            if (lowercase.equals("e")) {
                // beep
                Styles.beep();
                // print to user
                pause("\nExiting task coloring");
                // exit task creation
                return;
            }

            // get index of task
            i = getIndexByName(name);

            // if name was not found in task
            if (i < 0) {
                // beep
                Styles.beep();
                // wait for enter
                pause("\nInvalid input. Task \"" + name + "\" does not exist.\nMake sure to check spelling");
                // clear user input
                name = "";
                // restart
                continue;

            }

        }

        // retrieve task
        Task t = tasks.get(i);

        // wait for enter
        pause("\nTask \"" + t.getName() + "\" selected");

        // create color
        String color = "";

        // while color does nto exist
        while (color.equals("")) {

            printTasks();

            println();

            // get user input
            color = inp("New color of \"" + t.getName() + "\" (\"e\" to exit): ");

            // if name is empty
            if (color.equals("")) {
                // beep
                Styles.beep();
                // wait for enter key
                pause("\nNull color");
                // restart
                continue;
            }

            // if name is "e" (exit code)
            String lowercase = color.toLowerCase();
            if (lowercase.equals("e")) {
                // beep
                Styles.beep();
                // print to user
                pause("\nExiting task coloring");
                // exit task creation
                return;
            }

            // check for possible cases
            switch (lowercase) {

                // if user entered red
                case "red":
                    // set color to red
                    color = Styles.RED;
                    break;

                // if user entered maroon
                case "maroon":
                    // set color to red
                    color = Styles.RED;
                    break;

                // if user entered orange
                case "orange":
                    // set color to red
                    color = Styles.RED;
                    break;

                // if user entered yellow
                case "yellow":
                    // set color to yellow
                    color = Styles.YELLOW;
                    break;

                // if user entered green
                case "green":
                    // set color to green
                    color = Styles.GREEN;
                    break;

                // if user entered blue
                case "blue":
                    // set color to blue
                    color = Styles.BLUE;
                    break;

                // if user entered cyan
                case "cyan":
                    // set color to blue
                    color = Styles.BLUE;
                    break;

                // if user entered indigo
                case "indigo":
                    // set color to purple
                    color = Styles.PURPLE;
                    break;

                // if user entered purple
                case "purple":
                    // set color to purple
                    color = Styles.PURPLE;
                    break;

                // if user entered violet
                case "violet":
                    // set color to purple
                    color = Styles.PURPLE;
                    break;

                // if user entered magenta
                case "magenta":
                    // set color to pink
                    color = Styles.PINK;
                    break;

                // if user entered pink
                case "pink":
                    // set color to pink
                    color = Styles.PINK;
                    break;

                // if user entered black
                case "black":
                    // set color to black
                    color = Styles.BLACK;
                    break;

                // if user entered gray
                case "gray":
                    // set color to black
                    color = Styles.BLACK;
                    break;

                // if user entered white
                case "white":
                    // reset color
                    color = Styles.RESET;
                    break;

                // if the user did not enter valid color
                default:
                    // beep
                    Styles.beep();
                    // wait for enter
                    pause("\nInvalid input. Color \"" + color + "\" not recognized");
                    // clear user input
                    color = "";
                    break;

            }

        }

        // create ColorTask
        ColorTask newTask = new ColorTask(t, color);

        // replace old task with new one
        tasks.set(i, newTask);

        // wait for enter
        pause("\nColored task " + newTask.getColor() + newTask.getName() + "");

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
                pause("\nNull task name");
                // restart
                continue;
            }

            // if name is "e" (exit code)
            String lowercase = name.toLowerCase();
            if (lowercase.equals("e")) {
                // beep
                Styles.beep();
                // print to user
                pause("\nExiting task deletion");
                // exit task deletion
                return;
            }

            // get index of task
            i = getIndexByName(name);

            // if name was not found in task
            if (i < 0) {
                // beep
                Styles.beep();
                // wait for enter
                pause("\nInvalid input. Task \"" + name + "\" does not exist.\nMake sure to check spelling");
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

        // wait for enter
        pause("\nDeleted task \"" + t.getName() + "");

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
                pause("\nNull task name");
                // restart
                continue;
            }

            // if name is "e" (exit code)
            String lowercase = name.toLowerCase();
            if (lowercase.equals("e")) {
                // beep
                Styles.beep();
                // print to user
                pause("\nExiting task starring");
                // exit task
                return;
            }

            // get index of task
            i = getIndexByName(name);

            // if name was not found in task
            if (i < 0) {
                // beep
                Styles.beep();
                // wait for enter
                pause("\nInvalid input. Task \"" + name + "\" does not exist");
                // clear user input
                name = "";
                // restart
                continue;
            }

        }

        // retrieve task
        Task t = tasks.get(i);

        // set task to starred
        t.star();

        // set task to uncompleted
        t.uncomplete();

        // wait for enter
        pause("\nStarred task \"" + t.getName() + "\"");

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

        // create empty user input
        String name = "";

        // create index of task to pin
        int i = -1;

        // while user input is invalid
        while (name.equals("")) {

            printTasks();

            println();

            // get input
            name = inp("Complete task with name (\"e\" to exit): ");

            // if name is empty
            if (name.equals("")) {
                // beep
                Styles.beep();
                // wait for enter key
                pause("\nNull task name");
                // restart
                continue;
            }

            // if name is "e" (exit code)
            String lowercase = name.toLowerCase();
            if (lowercase.equals("e")) {
                // beep
                Styles.beep();
                // print to user
                pause("\nExiting task completion");
                // exit task
                return;
            }

            // get index of task
            i = getIndexByName(name);

            // if name was not found in task
            if (i < 0) {
                // beep
                Styles.beep();
                // wait for enter
                pause("\nInvalid input. Task \"" + name + "\" does not exist");
                // clear user input
                name = "";
                // restart
                continue;
            }

        }

        // retrieve task
        Task t = tasks.get(i);

        // set task to completed
        t.complete();

        // set task to un-starred
        t.unstar();

        // wait for enter
        pause("\nCompleted task \"" + t.getName() + "\"");

    }

    /**
     * Exits list
     */
    private static void exit() {
        // print to user
        print("\nExiting list " + Styles.GREEN + l.getName());
        // wait for enter
        pause("");
    }


}
