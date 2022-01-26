/**
 * @author Narayan Sajeev
 * List-managing app
 */
// create package
package todoey;

/**
 * Define styles
 */
public class Styles {

    public static final String RESET = "\u001B[0m";

    public static final String GREEN = "\033[0;92m";
    public static final String YELLOW = "\033[0;93m";
    public static final String BLACK = "\033[0;90m";
    public static final String RED = "\033[0;91m";

    public static final String PINK = "\033[0;95m";
    public static final String BLUE = "\033[0;96m";

    public static final String CLS = "\033[H\033[2J";
    public static final String BEEP = "\007";

    public static final String PURPLE = "\u001B[34m";

    public static final int HR_LEN = 35;

    /**
     * Plays beep using {@link #BEEP}
     */
    public static void beep() {
        System.out.print(BEEP);
    }

    /**
     * Prints horizontal line using {@link #HR_LEN}
     */
    public static void hr() {

//         repeat
        for (int i = 0; i < HR_LEN; i++) {
            // print dash
            System.out.print(RESET + "-");
        }

        // print newline
        System.out.println("\n" + RESET);

    }

    /**
     * Clears screen using {@link #CLS}
     */
    public static void cls() {
        System.out.print(CLS);
    }

}