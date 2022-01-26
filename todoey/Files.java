/**
 * @author Narayan Sajeev
 * List-managing app
 */
// create package
package todoey;

// import modules

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Create Files class
 */
public class Files {

    // create fields
    private String fname;
    private ArrayList<List> lists;

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
            for (List l : lists) {
                // write list to file
                w.write(l.getName() + " " + l.isPinned() + "\n");


                // loop through tasks
                for (Task t : l.getTasks()) {
                    // write task to file
                    w.write(t.getName() + " " + t.isCompleted() + " " + t.isStarred() + ",");
                }

                // write newline
                w.write("\n\n");

            }

            // close file
            w.close();

        // if error, do nothing
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Load lists
     * @return ArrayList of Lists
     */
    public ArrayList<List> loadLists() {
        return new ArrayList<>();
    }

    /**
     * Returns file names in folder
     * @return Filenames
     */
    public static String[] getFileNames() {
        return new String[1];
    }

    /**
     * Checks if file is formatted properly
     * @return If file is formatted properly
     */
    public boolean formatted() {

        // split text by .txt
        String[] split = fname.split(".txt");

        // put filename together
        String joined = String.join("", split);

        // split text by spaces
        split = joined.split(" ");

        // put filename together
        joined = String.join("", split) + ".txt";

        // update filename
        setFileName(joined);

        // return true if proper length
        return fname.length() > 5 && fname.length() < 32;

    }

    /**
     * Set filename
     * @param fname New filename
     */
    public void setFileName(String fname) {
        this.fname = fname;
    }
}
