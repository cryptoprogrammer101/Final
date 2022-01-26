/**
 * @author Narayan Sajeev
 * List-managing app
 */
// create package
package todoey;

// import modules

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Run Todoey
 */
public class Main {

	// create ArrayList of lists
	private static ArrayList<List> lists = new ArrayList<>();

	// create Scanner object
	private static Scanner s = new Scanner(System.in);

	// create main class
	public static void main(String[] main) {

		// set flag variable
		boolean run = true;

		// repeat until user exits
		while (run) {

			// print menu
			printMenu();

			// retrieve user input
			String t = inp("Enter: ");
			t = t.toLowerCase();

			// check user input cases
			switch (t) {

				// if user entered print menu
				case "m":
					break;

				// if user entered load list
				case "l":
					loadLists();
					break;

				// if user entered print lists
				case "p":

					// if there are lists
					if (!noLists()) {
						// print lists
						printLists();
						pause();
					}

					break;

				// if user entered print as 2d array
				case "a":
					print2dArray();
					break;

				// if user entered save lists
				case "s":
					saveLists();
					break;

				// if user entered reorder lists
				case "r":
					reorderLists();
					break;

				// if user entered create list
				case "c":
					createList();
					break;

				// if user entered delete list
				case "d":
					deleteList();
					break;

				// if user entered pin list
				case "i":
					pinList();
					break;

				// if user entered pin list
				case "n":
					renameList();
					break;

				// if user entered open list
				case "o":
					openList();
					break;

				// if user entered exit Todoey
				case "e":
					run = false;
					exitLists();
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
	private static void printMenu() {

		// clear screen
		Styles.cls();

		// print to user
		Styles.hr();
		println(Styles.GREEN + "Welcome to Todoey. To start, press:\n");
		menuPrint("M", "Print menu");
		menuPrint("P", "Print lists");
		menuPrint("A", "Print as 2d array");
		menuPrint("L", "Load lists");
		menuPrint("S", "Save lists");
		menuPrint("R", "Reorder lists");
		menuPrint("C", "Create list");
		menuPrint("N", "Rename list");
		menuPrint("D", "Delete list");
		menuPrint("O", "Open list");
		menuPrint("I", "Pin list");
		menuPrint("E", "Exit " + Styles.GREEN + "Todoey\n");
		Styles.hr();

	}

	/**
	 * Prints files in folder
	 */
	public static void printFiles() {

		// retrieve filenames
		String[] filenames = Files.getFileNames();

		// clear screen
		Styles.cls();

		println();

	}

	/**
	 * Loads lists from file
	 */
	private static void loadLists() {

		// create empty user input
		String fname = "";

		// create Files object
		Files f = new Files(fname);

		// while user input is invalid
		while (fname.equals("")) {

			// print files in folder
			printFiles();

			// get input
			fname = inp("Name of database file (\"e\" to exit): ");

			// update filename
			f.setFileName(fname);

			// if name is empty
			if (fname.equals("")) {
				// beep
				Styles.beep();
				// wait for enter key
				pause("\nNull file name");
				// restart
				continue;
			}

			// if name is "e" (exit code)
			String lowercase = fname.toLowerCase();
			if (lowercase.equals("e")) {
				// beep
				Styles.beep();
				// print to user
				pause("\nExiting list loading");
				// exit list loading
				return;
			}

			// check if file exists
			if (!f.fileExists()) {
				// beep
				Styles.beep();
				// wait for enter
				pause("\nInvalid file name. File \"" + fname + "\" does not exist");
				// reset user input
				fname = "";
				// restart
				continue;
			}

		}

		// update list
		lists = f.loadLists();

		// if no lists were found
		if (lists.size() == 0) {
			// wait for enter
			pause("\nNo lists found in file \"" + fname + "\"");
			// exit
			return;
		}

		// wait for enter
		pause("\nLoaded " + lists.size() + " lists from file " + fname + "\"");

	}

	/**
	 * Checks if there are existing
	 * @return if list is found
	 */
	private static boolean noLists() {
		// if there are no lists
		if (lists.size() == 0) {
			// beep
			Styles.beep();
			// print to user
			pause("\nNo existing lists.\nStart by creating new list or loading saved list");
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
	 * Prints 
	 */
	private static void printLists() {

		// clear screen
		Styles.cls();

		// print to user
		println("Lists:\n");

		// if there are no lists
		if (lists.size() == 0) {
			println("No existing lists.\n");
		}

		// loop through lists
		for (int i = 0; i < lists.size(); i++) {

			// get list
			List l = lists.get(i);

			// print list name
			print((i + 1) + ". " + l.getName());

			// if list is pinned
			if (l.isPinned()) {

				// print pinned
				print(Styles.YELLOW + " (PINNED)");

			}

			println();

			// get number of tasks
			int numTasks = l.getTasks().size();

			// if there are no tasks
			if (numTasks == 0) {
				// print that there is no tasks
				println("   No existing tasks.");
			}

			// loop through tasks
			for (int j = 0; j < numTasks; j++) {

				// get task
				Task t = l.getTasks().get(j);

				// print bullet point
				print("\n \u2022 ");

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
					print(Styles.YELLOW + " \u2b50");
				}

				// if task is completed
				if (t.isCompleted()) {

					// print check mark
					print(" \u2714");

				}

				println();

			}

			println("\n");

		}

	}

	/**
	 * Calculates total number of tasks
	 * @return Number of tasks
	 */
	public static boolean noTasks() {

		// define counter variable
		int counter = 0;

		// loop through lists
		for (List l : lists) {

			// loop through tasks
			for (Task t : l.getTasks()) {

				// increment counter
				counter++;

			}

		}

		// if there are no tasks
		if (counter == 0) {
			// beep
			Styles.beep();
			// print to user
			pause("\nNo existing tasks.\nStart by opening new list & creating new task");
			// return true;
			return true;
		}

		return false;

	}

	/**
	 * Finds maximum number of tasks in list
	 * @return Number of tasks in list
	 */
	public static int maxTasks() {

		// define maximum
		int max = -1;

		// loop through lists
		for (List l : lists) {

			// retrieve tasks
			ArrayList<Task> tasks = l.getTasks();

			// if tasks is longer than max
			if (tasks.size() > max) {
				// update max
				max = tasks.size();
			}

		}

		// return max;
		return max;

	}

	/**
	 * Convert lists to 2d array
	 */
	public static String[][] create2dArray() {

		// find most tasks
		int max = maxTasks();

		// create 2d array
		String[][] arr = new String[lists.size()][max];

		// loop through array
		for (int i = 0; i < arr.length; i++) {

			// retrieve list
			List l = lists.get(i);

			// retrieve tasks
			ArrayList<Task> tasks = l.getTasks();

			// loop through tasks
			for (int j = 0; j < tasks.size(); j++) {

				// retrieve task
				Task t = tasks.get(j);

				// update array
				arr[i][j] = t.getName();

			}

		}

		// return array
		return arr;

	}

	/**
	 * Prints lists as 2d array
	 */
	public static void print2dArray() {

		// if there are no lists
		if (noLists()) {
			// stop
			return;
		}

		// if there are no tasks
		if (noTasks()) {
			// stop
			return;
		}

		// clear screen
		Styles.cls();

		// print to user
		println("Lists as 2d array:\n");

		// create 2d array using lists
		String[][] arr = create2dArray();

		// loop through rows
		for (String[] row : arr) {

			// loop through columns
			for (int j = 0; j < row.length; j++) {

				// retrieve name
				String n = row[j];

				// if name is null
				if (n == null) {
					// break out
					break;
				}

				// print name
				System.out.print(n);

				// if this is last column
				if (j + 2 > row.length) {
					// print newline
					println();
					// break out
					break;
				}

				// if this is the last non-null column
				if (row[j + 1] == null) {
					// print newline
					println();
					// break out
					break;
				}

				// print trailing comma
				System.out.print(",\t");

			}

		}

		// print newlines
		println("\n");

		// wait for enter
		pause();

	}

	/**
	 * Saves lists
	 */
	private static void saveLists() {

		// if there are no lists
		if (noLists()) {
			// stop
			return;
		}

		// create empty user input
		String fname = "";

		// create Files object
		Files f = new Files(fname, lists);

		// while user input is invalid
		while (fname.equals("")) {

			// print files in folder
			printFiles();

			// get input
			fname = inp("Name of database file to save to (\"e\" to exit): ");

			// update filename
			f.setFileName(fname);

			// if name is empty
			if (fname.equals("")) {
				// beep
				Styles.beep();
				// wait for enter key
				pause("\nNull file name");
				// restart
				continue;
			}

			// if name is "e" (exit code)
			String lowercase = fname.toLowerCase();
			if (lowercase.equals("e")) {
				// beep
				Styles.beep();
				// print to user
				pause("\nExiting list saving");
				// exit list saving
				return;
			}

			// if filename is not properly formatted
			if (!f.formatted()) {
				// reset user input
				fname = "";
				// beep
				Styles.beep();
				// wait for enter
				pause("\nInvalid file formatting.\nFile must be in format \"filename.txt\"");
				// restart
				continue;
			}

		}

		// save lists
		f.saveLists();

		// wait for enter
		pause("\nSaved " + lists.size() + " lists to file \"" + f.getFileName() + "\"");

	}

	/**
	 * Retrieves list names
	 * @return Array of names
	 */
	private static String[] listNames() {

		// create array with same length as lists
		String[] names = new String[lists.size()];

		// loop through lists
		for (int i = 0; i < lists.size(); i++) {

			// retrieve name
			String n = lists.get(i).getName();

			// update array
			names[i] = n.toLowerCase();

		}

		return names;
	}

	/**
	 * Finds index given list name using listNames()
	 * @param n List name
	 * @return Index
	 */
	private static int getIndexByName(String n) {

		// convert name to lowercase
		n = n.toLowerCase();

		// get name list
		String[] names = listNames();

		// loop through names
		for (int i = 0; i < lists.size(); i++) {

			// if current name is equal to name given
			if (names[i].equals(n)) {

				// return current index
				return i;

			}

		}

		// return -1 to indicate list name was not found
		return -1;

	}

	/**
	 * Reorders 
	 */
	private static void reorderLists() {

		// if there are no lists
		if (noLists()) {
			// stop
			return;
		}

		// create empty user input
		String name = "";

		// create index of list to move
		int i = -1;

		// while user input is invalid
		while (name.equals("")) {

			printLists();

			// get input
			name = inp("Move list with name (\"e\" to exit): ");

			// if name is empty
			if (name.equals("")) {
				// beep
				Styles.beep();
				// wait for enter key
				pause("\nNull list name");
				// restart
				continue;
			}

			// if name is "e" (exit code)
			String lowercase = name.toLowerCase();
			if (lowercase.equals("e")) {
				// beep
				Styles.beep();
				// print to user
				pause("\nExiting list reordering");
				// exit list
				return;
			}

			// get index of list
			i = getIndexByName(name);

			// if name was not found in list
			if (i < 0) {
				// beep
				Styles.beep();
				// wait for enter
				pause("\nInvalid input. List " + name +" does not exist");
				// clear user input
				name = "";
				// restart
				continue;
			}

			// get list
			List current = lists.get(i);

			// if list is pinned
			if (current.isPinned()) {
				// beep
				Styles.beep();
				// wait for enter
				pause("\nCannot move pinned list " + current.getName());
				// clear user input
				name = "";
				// restart
				continue;
			}

		}

		// retrieve list
		List l = lists.get(i);

		// wait for enter
		pause("\nList " + l.getName() + " selected");

		// create empty user input
		String usrI = "";

		// create new index of list
		int newI = -1;

		// while user input is invalid
		while (usrI.equals("")) {

			printLists();

			// get input
			usrI = inp("Move list " + l.getName() + " to index: (\"e\" to exit, index 1 = first element): ");

			// if name is empty
			if (usrI.equals("")) {
				// beep
				Styles.beep();
				// wait for enter key
				pause("\nNull list index");
				// restart
				continue;
			}

			// if name is "e" (exit code)
			String lowercase = usrI.toLowerCase();
			if (lowercase.equals("e")) {
				// beep
				Styles.beep();
				// print to user
				pause("\nExiting list reordering");
				// exit
				return;
			}

			try {
				// try to convert user input to integer
				newI = Integer.parseInt(usrI);
			// if theres an error
			} catch (NumberFormatException e) {
				// reset user input
				usrI = "";
			}

			// if user didnt enter integer
			if (usrI.equals("")) {
				// beep
				Styles.beep();
				// wait for enter
				pause("\nInvalid input. Index must be an integer");
				// restart
				continue;
			}

			// if index is out of bounds
			if (newI < 1 || newI > lists.size()) {
				// reset user input
				usrI = "";
				// beep
				Styles.beep();
				// wait for enter
				print("\nInvalid input. Index must be between 1 and " + lists.size());
				// restart
				continue;
			}

			// decrease index by 1 to start from 0
			newI--;

			// get list
			List current = lists.get(newI);

			// if list is pinned
			if (current.isPinned()) {
				// beep
				Styles.beep();
				// wait for enter
				pause("\nCannot move pinned list " + current.getName());
				// clear user input
				usrI = "";
				// restart
				continue;
			}

		}

		// retrieve list at index
		List newL = lists.get(newI);

		// unpins lists
		l.unpin();
		newL.unpin();

		// if new index is before old index
		if (newI < i) {

			// loop through lists from old index to new index
			for (int j = i - 1; j >= newI; j--) {
				// retrieve current list
				List current = lists.get(j);
				// move list 1 element forward
				lists.set(j + 1, current);
			}

		}

		// if new index is after old index
		if (newI > i) {
			// loop through lists from new index to old index
			for (int j = i + 1; j <= newI; j++) {
				// retrieve current list
				List current = lists.get(j);
				// move list 1 element backward
				lists.set(j - 1, current);
			}

		}

		// move list
		lists.set(newI, l);

		// wait for enter
		pause("\nMoved list " + l.getName() + " to index " + (newI + 1));

	}

	/**
	 * Creates list
	 */
	private static void createList() {

		// create empty user input
		String name = "";

		// while user input is invalid
		while (name.equals("")) {

			printLists();

			// get input
			name = inp("Create list with name (\"e\" to exit): ");

			// if name is empty
			if (name.equals("")) {
				// beep
				Styles.beep();
				// wait for enter key
				pause("\nNull list name");
				// restart
				continue;
			}

			// if name is "e" (exit code)
			String lowercase = name.toLowerCase();
			if (lowercase.equals("e")) {
				// beep
				Styles.beep();
				// print to user
				pause("\nExiting list creation");
				// exit list creation
				return;
			}

			// get index of list
			int i = getIndexByName(name);

			// if name already exists
			if (i > -1) {
				// clear user input
				name = "";
				// beep
				Styles.beep();
				// retrieve name
				List l = lists.get(i);
				// wait for enter
				pause("\nList " + l.getName() + " already exists");
				// restart
				continue;
			}

		}

		// create list using name
		List l = new List(name);

		// add list to ArrayList
		lists.add(l);

		// wait for enter
		pause("\nOpening list " + l.getName());

		// create ListTools object
		ListTools tools = new ListTools(l);

		// open list;
		tools.openList();

	}

	/**
	 * Deletes list
	 */
	private static void deleteList() {

		// if there are no lists
		if (noLists()) {
			// stop
			return;
		}

		// create empty user input
		String name = "";

		// create index of list to delete
		int i = -1;

		// while user input is invalid
		while (name.equals("")) {

			printLists();

			// get input
			name = inp("Delete list with name (\"e\" to exit): ");

			// if name is empty
			if (name.equals("")) {
				// beep
				Styles.beep();
				// wait for enter key
				pause("\nNull list name");
				// restart
				continue;
			}

			// if name is "e" (exit code)
			String lowercase = name.toLowerCase();
			if (lowercase.equals("e")) {
				// beep
				Styles.beep();
				// print to user
				pause("\nExiting list deletion");
				// exit list deletion
				return;
			}

			// get index of list
			i = getIndexByName(name);

			// if name was not found in list
			if (i < 0) {
				// beep
				Styles.beep();
				// wait for enter
				pause("\nInvalid input. List \"" + name + "\" does not exist.\nMake sure to check spelling");
				// clear user input
				name = "";
				// restart
				continue;

			}

		}

		// retrieve list
		List l = lists.get(i);

		// delete list
		lists.remove(l);

		// wait for enter
		pause("\nDeleted list " + l.getName());

	}

	/**
	 * Pins list
	 */
	private static void pinList() {

		// if there are no lists
		if (noLists()) {
			// stop
			return;
		}

		// get first list
		List first = lists.get(0);

		// if list is already pinned
		if (first.isPinned()) {
			// beep
			Styles.beep();
			// wait for enter
			pause("\nCannot pin another list. Pinned list " + first.getName() + " already exists");
			// exit
			return;
		}

		// create empty user input
		String name = "";

		// create index of list to pin
		int i = -1;

		// while user input is invalid
		while (name.equals("")) {

			printLists();

			// get input
			name = inp("Pin list with name (\"e\" to exit): ");

			// if name is empty
			if (name.equals("")) {
				// beep
				Styles.beep();
				// wait for enter key
				pause("\nNull list name");
				// restart
				continue;
			}

			// if name is "e" (exit code)
			String lowercase = name.toLowerCase();
			if (lowercase.equals("e")) {
				// beep
				Styles.beep();
				// print to user
				pause("\nExiting list pinning");
				// exit list
				return;
			}

			// get index of list
			i = getIndexByName(name);

			// if name was not found in list
			if (i < 0) {
				// beep
				Styles.beep();
				// print to user
				pause("\nInvalid input. List \"" + name + "\" does not exist");
				// clear user input
				name = "";
				// restart
				continue;
			}

		}

		// retrieve list
		List l = lists.get(i);

		// unpin list
		first.unpin();

		// pin list
		l.pin();

		// move list to front
		lists.set(0, l);

		// move first list back
		lists.set(i, first);

		// wait for enter
		pause("\nPinned list " + l.getName());

	}


	/**
	 * Renames list
	 */
	private static void renameList() {

		// if there are no lists
		if (noLists()) {
			// stop
			return;
		}

		// create empty user input
		String name = "";

		// create index of list to rename
		int i = -1;

		// while user input is invalid
		while (name.equals("")) {

			printLists();

			// get input
			name = inp("Rename list (\"e\" to exit): ");

			// if name is empty
			if (name.equals("")) {
				// beep
				Styles.beep();
				// wait for enter key
				pause("\nNull list name");
				// restart
				continue;
			}

			// if name is "e" (exit code)
			String lowercase = name.toLowerCase();
			if (lowercase.equals("e")) {
				// beep
				Styles.beep();
				// print to user
				pause("\nExiting list renaming");
				// exit list
				return;
			}

			// get index of list
			i = getIndexByName(name);

			// if name was not found in list
			if (i < 0) {
				// beep
				Styles.beep();
				// wait for enter
				print("\nInvalid input. List \"" + name + "\" does not exist");
				// clear user input
				name = "";
				// restart
				continue;
			}

		}

		// retrieve list
		List l = lists.get(i);

		// wait for enter
		pause("\nList " + l.getName() + " selected");

		// create new empty user input
		String newName = "";

		// while user input is invalid
		while (newName.equals("")) {

			printLists();

			// get input
			newName = inp("New name for list " + l.getName() + " (\"e\" to exit): ");

			// if name is empty
			if (newName.equals("")) {
				// beep
				Styles.beep();
				// wait for enter key
				pause("\nNull list name");
				// restart
				continue;
			}

			// if name is "e" (exit code)
			String lowercase = newName.toLowerCase();
			if (lowercase.equals("e")) {
				// beep
				Styles.beep();
				// print to user
				pause("\nExiting list renaming");
				// exit list
				return;
			}

			// get index of list
			int j = getIndexByName(newName);

			// if name already exists
			if (j > -1) {
				// clear user input
				newName = "";
				// beep
				Styles.beep();
				// retrieve name
				List current = lists.get(j);
				// wait for enter
				pause("\nList " + current.getName() + " already exists");
				// restart
				continue;
			}

		}

		// print to user
		print("\nRenamed list \"" + l.getName());

		// rename list
		l.setName(newName);

		// update lists
		lists.set(i, l);

		// wait for enter
		pause("\" to \"" + l.getName() + "\"");

	}

	/**
	 * Opens list
	 */
	private static void openList() {

		// if there are no lists
		if (noLists()) {
			// stop
			return;
		}

		// create empty user input
		String name = "";

		// create index of list to open
		int i = -1;

		// while user input is invalid
		while (name.equals("")) {

			printLists();

			// get input
			name = inp("Open list with name (\"e\" to exit): ");

			// if name is empty
			if (name.equals("")) {
				// beep
				Styles.beep();
				// wait for enter key
				pause("\nNull list name");
				// restart
				continue;
			}

			// if name is "e" (exit code)
			String lowercase = name.toLowerCase();
			if (lowercase.equals("e")) {
				// beep
				Styles.beep();
				// print to user
				pause("\nExiting list");
				// exit list
				return;
			}

			// get index of list
			i = getIndexByName(name);

			// if name was not found in list
			if (i < 0) {
				// beep
				Styles.beep();
				// wait for enter
				pause("\nInvalid input. List \"" + name + "\" does not exist");
				// clear user input
				name = "";
				// restart
				continue;
			}

		}

		// retrieve list
		List l = lists.get(i);

		// print to user
		pause("\nOpening list " + l.getName());

		// create ListTools object
		ListTools tools = new ListTools(l);

		// open list;
		tools.openList();

	}

	/**
	 * Terminates Todoey
	 */
	private static void exitLists() {
		// beep
		Styles.beep();
		// print to user
		println(Styles.GREEN + "\nExiting Todoey...\n");
		// exit
		System.exit(0);
	}

}
