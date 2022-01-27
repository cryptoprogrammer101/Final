/**
 * @author Narayan Sajeev
 * List-managing app
 */
// create package
package todoey;

// import modules

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Create Files class
 */
public class Files {

    // create fields
    private String fname;
    private ArrayList<List> lists;

    // define divider between lines in file
    private final String DIVIDER = "__________";

    // define text file extension
    private final String EXTENSION = ".txt";

    // define space
    private final String SPACE = " ";

    /**
     * Create constructor
     * @param fname Filename
     * @param lists Lists to save
     */
    public Files(String fname, ArrayList<List> lists) {
        this.fname = fname;
        this.lists = lists;
    }

    /**
     * Create constructor
     * @param fname Filename
     */
    public Files(String fname) {
        this.fname = fname;
    }

    /**
     * Return filename
     * @return Filename
     */
    public String getFileName() {
        return this.fname;
    }

    /**
     * Checks if file exists
     * @return If file exists
     */
    public boolean fileExists() {

        // create file
        File f = new File(this.fname);

        // return if file exists
        return f.exists();

    }

    /**
     * Create file
     */
    public void createFile() {

        // try
        try {
            // create File object
            File f = new File(fname);

            // create new file
            f.createNewFile();

        // if error, do nothing
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Save lists
     */
    public void saveLists() {

        // try
        try {

            // create file
            createFile();

            // create FileWriter object to clear file
            FileWriter clear = new FileWriter(fname);

            // clear file
            clear.write("");

            // create FileWriter object to append to file
            FileWriter w = new FileWriter(fname, true);

            // loop through lists
            for (int i = 0; i < lists.size(); i++) {

                // retrieve list
                List l = lists.get(i);

                // write list to file
                w.write(l.getName() + this.SPACE + l.isPinned() + "/\n");


                // loop through tasks
                for (int j = 0; j < l.getTasks().size(); j++) {

                    // retrieve task
                    Task t = l.getTasks().get(j);

                    // write task to file
                    w.write(t.getName() + this.SPACE + t.isCompleted() + this.SPACE + t.isStarred());

                    // if not last task
                    if (j + 1 < l.getTasks().size()) {

                        // add comma
                        w.write(",");

                    }

                }

                // if not last list
                if (i + 1 < lists.size()) {

                    // write horizontal line & newline
                    w.write("\n" + this.DIVIDER + "\n");

                }

            }

            // close file
            w.close();

        // if error, do nothing
        } catch (IOException ignore) {

        }

    }
    /**
     * Load lists
     * @return ArrayList of Lists
     */
    public ArrayList<List> loadLists() {

        // clear lists
        lists = new ArrayList<>();

        // try
        try {

            // create File object
            File f = new File(fname);

            // create Scanner object
            Scanner s = new Scanner(f);

            // create StringBuilder to store data
            StringBuilder sb = new StringBuilder();

            // loop through file
            while (s.hasNextLine()) {

                // retrieve line
                String line = s.nextLine();

                // update data
                sb.append(line);

            }

            // convert data to String
            String data = sb.toString();

            // convert data to lowercase
            data = data.toLowerCase();

            // split data by divider
            String[] allLists = data.split(this.DIVIDER);

            // loop through lists
            for (String u : allLists) {

                // create tasks
                ArrayList<Task> tasks = new ArrayList<>();

                // split list by newline
                String[] list = u.split("/");

                // retrieve list attributes
                String[] listAttr = list[0].split(this.SPACE);

                // retrieve list name
                String lstName = listAttr[0];

                // retrieve pinned
                boolean pinned = Boolean.parseBoolean(listAttr[1]);

                // create new List
                List l = new List(lstName, pinned, tasks);

                // split data by task
                String[] allTasks = list[1].split(",");

                // loop through tasks
                for (String v : allTasks) {

                    // retrieve task attributes
                    String[] taskAttr = v.split(this.SPACE);

                    // retrieve name
                    String taskName = taskAttr[0];

                    // retrieve completed
                    boolean completed = Boolean.parseBoolean(taskAttr[1]);

                    // retrieve starred
                    boolean starred = Boolean.parseBoolean(taskAttr[2]);

                    // create new Task
                    Task t = new Task(taskName, completed, starred);

                    // add task
                    l.addTask(t);

                }

                // add List to lists
                lists.add(l);

            }

            // close Scanner
            s.close();

        // if error, do nothing
        } catch (FileNotFoundException ignore) {

        }

        return lists;

    }

    /**
     * Returns file names in folder
     * @return Filenames
     */
    public ArrayList<String> getFileNames() {

        // create ArrayList of filenames
        ArrayList<String> fnames = new ArrayList<>();

        // open current folder
        File folder = new File(System.getProperty("user.dir"));

        // retrieve files
        String[] files = folder.list();

        // if there are no files
        if (files == null) {
            // return filenames
            return fnames;
        }

        // loop through files
        for (String file : files) {

            // extract file extension
            String extension = file.substring(file.length() - 4);

            // if file is text file
            if (extension.equals(this.EXTENSION)) {

                // add to ArrayList
                fnames.add(file);

            }

        }

        // return filenames
        return fnames;
    }

    /**
     * Checks if filename is proper length & updates current filename
     * @return If file is proper length
     */
    public boolean format() {

        // split text by .txt
        String[] split = fname.split(this.EXTENSION);

        // put filename together
        String joined = String.join("", split);

        // split text by spaces
        split = joined.split(this.SPACE);

        // put filename together
        joined = String.join("", split) + this.EXTENSION;

        // update filename
        setFileName(joined);

        // return true if proper length
        return fname.length() > 3 && fname.length() < 30;

    }

    /**
     * Set filename
     * @param fname New filename
     */
    public void setFileName(String fname) {
        this.fname = fname;
    }
}
