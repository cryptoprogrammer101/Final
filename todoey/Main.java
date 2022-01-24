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
			menu();

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

				// if user entered unpin list
				case "u":
					unpinList();
					break;

				// if user entered exit Todoey
				case "e":
					run = false;
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
	 * Waits for enter key to be pressed using {@link #pause(String)}
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
	 * Prints menu using {@link #menuPrint(String, String)}
	 */
	private static void menu() {

		// clear screen
		Styles.cls();

		// print to user
		Styles.hr();
		println(Styles.GREEN + "Welcome to Todoey. To start, press:\n");
		menuPrint("M", "Print menu");
		menuPrint("P", "Print lists");
		menuPrint("L", "Load lists");
		menuPrint("S", "Save lists");
		menuPrint("R", "Reorder lists");
		menuPrint("C", "Create list");
		menuPrint("N", "Rename list");
		menuPrint("D", "Delete list");
		menuPrint("O", "Open list");
		menuPrint("I", "Pin list");
		menuPrint("U", "Unpin list");
		menuPrint("E", "Exit " + Styles.GREEN + "Todoey\n");
		Styles.hr();

	}

	/**
	 * Checks if there are existing {@link #lists}
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
	 * Loads lists from file
	 */
	private static void loadLists() {

		// create empty user input
		String fname = "";

		// while user input is invalid
		while (fname.equals("")) {

			// print files in folder
			Files.printFiles();

			println();

			// get input
			fname = inp("Name of database file (\"e\" to exit): ");

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
			if (!Files.fileExists(fname)) {
				// beep
				Styles.beep();
				// print to user
				print("\nInvalid file name. File " + Styles.GREEN + fname);
				// wait for enter
				pause(" does not exist");
				// reset user input
				fname = "";
				// restart
				continue;
			}

		}

		// update list using filename
		lists = Files.loadLists(fname);

		// if no lists were found
		if (lists.size() == 0) {
			// print to user
			print("\nNo lists found in file " + Styles.GREEN + fname);
			// wait for enter
			pause(". To start, create new lists or load lists from another file");
			// exit
			return;
		}

		// print to user
		print("\nLoaded " + Styles.GREEN + lists.size());

		// wait for enter
		pause(" lists from file " + Styles.GREEN + fname + "");

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
	 * Prints {@link #lists}
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

				println();

			}

			println("\n");

		}

	}

	/**
	 * Saves {@link #lists}
	 */
	private static void saveLists() {

		// if there are no lists
		if (noLists()) {
			// stop
			return;
		}

		// create empty user input
		String fname = "";

		// while user input is invalid
		while (fname.equals("")) {

			// print files in folder
			Files.printFiles();

			println();

			// get input
			fname = inp("Name of database file to save to (\"e\" to exit): ");

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

			// check if file formatting
			if (!Files.formatted(fname)) {
				// reset user input
				fname = "";
				// beep
				Styles.beep();
				// wait for enter
				pause("\nInvalid file formatting");
				// restart
				continue;
			}

		}

		// save lists using filename
		Files.saveLists(fname);

		// print to user
		print("\nSaved " + Styles.GREEN + lists.size());

		// wait for enter
		pause(" lists to file " + Styles.GREEN + fname + "");

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
	 * Finds index given list name using {@link #listNames()}
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
	 * Reorders {@link #lists}
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

			println();

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
				// print to user
				print("\nInvalid input. List " + Styles.GREEN + name);
				// wait for enter key
				pause(" does not exist");
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
				pause("\nCannot move pinned list " + Styles.GREEN + current.getName() + "");
				// clear user input
				name = "";
				// restart
				continue;
			}

		}

		// retrieve list
		List l = lists.get(i);

		// print to user
		print("\nList " + Styles.GREEN + l.getName());
		pause(" selected");

		// create empty user input
		String usrI = "";

		// create new index of list
		int newI = -1;

		// while user input is invalid
		while (usrI.equals("")) {

			printLists();

			println();

			// print to user
			print("Move list " + Styles.GREEN + l.getName());

			// get input
			usrI = inp(" to index: (\"e\" to exit, index 1 = first element): ");

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
				// print to user
				print("\nInvalid input. Index must be between " + Styles.GREEN + "1 ");
				// wait for enter
				pause("and " + Styles.GREEN + lists.size() + "");
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
				pause("\nCannot move pinned list " + Styles.GREEN + current.getName() + "");
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

		// print to user
		print("\nMoved list " + Styles.GREEN + l.getName());
		// wait for enter
		pause(" to index " + Styles.GREEN + (newI + 1) + "");

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

			println();

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
				// print to user
				print("\nList " + Styles.GREEN + l.getName());
				// wait for enter
				pause(" already exists");
				// restart
				continue;
			}

		}

		// create list using name
		List l = new List(name);

		// add list to ArrayList
		lists.add(l);

		// wait for enter
		pause("\nOpening list " + Styles.GREEN + l.getName() + "");

		// open list
		ListTools.openList(l);

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

			println();

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
				// print to user
				print("\nInvalid input. List " + Styles.GREEN + name);
				// wait for enter
				pause(" does not exist.\nMake sure to check spelling");
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
		pause("\nDeleted list " + Styles.GREEN + l.getName() + "");

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
			// print to user
			print("\nCannot pin another list. Pinned list " + Styles.GREEN + first.getName());
			// wait for enter
			pause(" already exists");
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

			println();

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
				print("\nInvalid input. List " + Styles.GREEN + name);
				// wait for enter key
				pause(" does not exist");
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
		pause("\nPinned list " + Styles.GREEN + l.getName() + "");

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

			println();

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
				// print to user
				print("\nInvalid input. List " + Styles.GREEN + name);
				// wait for enter key
				pause(" does not exist");
				// clear user input
				name = "";
				// restart
				continue;
			}

		}

		// retrieve list
		List l = lists.get(i);

		// print to user
		print("\nList " + Styles.GREEN + l.getName());
		pause(" selected");

		// create new empty user input
		String newName = "";

		// while user input is invalid
		while (newName.equals("")) {

			printLists();
			println();

			// print to user
			print("New name for list " + Styles.GREEN + l.getName());

			// get input
			newName = inp(" (\"e\" to exit): ");

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
				// print to user
				print("\nList " + Styles.GREEN + current.getName());
				// wait for enter
				pause(" already exists");
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

			println();

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
				// print to user
				print("\nInvalid input. List " + Styles.GREEN + name);
				// wait for enter key
				pause(" does not exist");
				// clear user input
				name = "";
				// restart
				continue;
			}

		}

		// retrieve list
		List l = lists.get(i);

		// print to user
		print("\nOpening list " + Styles.GREEN + l.getName());


		// open list
		ListTools.openList(l);

	}

	/**
	 * Unpins list
	 */
	private static void unpinList() {

		// if there are no lists
		if (noLists()) {
			// stop
			return;
		}

		// get first list
		List first = lists.get(0);

		// if no list is pinned
		if (!first.isPinned()) {
			// beep
			Styles.beep();
			// wait for enter key
			pause("\nNo pinned list exists");
			// exit
			return;
		}

		// create empty user input
		String name = "";

		// create index of list to unpin
		int i = -1;

		// while user input is invalid
		while (name.equals("")) {

			printLists();

			println();

			// get input
			name = inp("Unpin list with name (\"e\" to exit): ");

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
				pause("\nExiting list unpinning");
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
				print("\nInvalid input. List " + Styles.GREEN + name);
				// wait for enter key
				pause(" does not exist");
				// clear user input
				name = "";
				// restart
				continue;
			}

			// retrieve list
			List current = lists.get(i);

			// if list is not pinned
			if (!current.isPinned()) {
				// beep
				Styles.beep();
				// print to user
				print("\nInvalid input. List " + Styles.GREEN + current.getName());
				// wait for enter key
				pause(" is not pinned");
				// clear user input
				name = "";
				// restart
				continue;
			}

		}

		// retrieve list
		List l = lists.get(i);

		// unpin
		l.unpin();

		// wait for enter
		pause("\nUnpinned list " + Styles.GREEN + l.getName() + "");

	}

	/**
	 * Terminates Todoey
	 */
	private static void exit() {
		// beep
		Styles.beep();
		// print to user
		println(Styles.GREEN + "\nExiting Todoey...\n");
		// exit
		System.exit(0);
	}

}
