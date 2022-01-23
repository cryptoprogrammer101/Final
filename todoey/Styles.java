/**
 * @author Narayan Sajeev
 * List-managing app
 */
// create package
package todoey;

// create class
public class Styles {

    /**
     * Defines styles
     *
     * BD: bold
     * BT: bright
     * LT: light
     * DK: dark
     * BG: background
     */

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String GREEN_BT = "\033[0;92m";
    public static final String GREEN_BG_BT = "\033[0;102m";
    public static final String YELLOW_BT = "\033[0;93m";

    private static final String CLS = "\033[H\033[2J";
    private static final String BEEP = "\007";

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String DK_GRAY = "\u001B[90m";
    private static final String PINK = "\u001B[91m";
    private static final String LT_GREEN = "\u001B[92m";
    private static final String LT_YELLOW = "\u001B[93m";
    private static final String LT_BLUE = "\u001B[94m";
    private static final String LT_MAGENTA = "\u001B[95m";
    private static final String LT_CYAN = "\u001B[96m";

    private static final String BLACK_BG = "\u001B[40m";
    private static final String RED_BG = "\u001B[41m";
    private static final String GREEN_BG = "\u001B[42m";
    private static final String YELLOW_BG = "\u001B[43m";
    private static final String BLUE_BG = "\u001B[44m";
    private static final String PURPLE_BG = "\u001B[45m";
    private static final String CYAN_BG = "\u001B[46m";
    private static final String WHITE_BG = "\u001B[107m";
    private static final String LT_GRAY_BG = "\u001B[47m";
    private static final String DK_GRAY_BG = "\u001B[100m";
    private static final String PINK_BG = "\u001B[101m";
    private static final String LT_GREEN_BG = "\u001B[102m";
    private static final String LT_YELLOW_BG = "\u001B[103m";
    private static final String LT_BLUE_BG = "\u001B[104m";
    private static final String LT_MAGENTA_BG = "\u001B[105m";
    private static final String LT_CYAN_BG = "\u001B[106m";

    private static final String BLACK_UL = "\033[4;30m";
    private static final String RED_UL = "\033[4;31m";
    private static final String GREEN_UL = "\033[4;32m";
    private static final String YELLOW_UL = "\033[4;33m";
    private static final String BLUE_UL = "\033[4;34m";
    private static final String PURPLE_UL = "\033[4;35m";
    private static final String CYAN_UL = "\033[4;36m";
    private static final String WHITE_UL = "\033[4;37m";

    private static final String BLACK_BT = "\033[0;90m";
    private static final String RED_BT = "\033[0;91m";
    private static final String BLUE_BT = "\033[0;94m";
    private static final String PURPLE_BT = "\033[0;95m";
    private static final String CYAN_BT = "\033[0;96m";
    private static final String WHITE_BT = "\033[0;97m";

    private static final String BLACK_BD_BT = "\033[1;90m";
    private static final String RED_BD_BT = "\033[1;91m";
    private static final String GREEN_BD_BT = "\033[1;92m";
    private static final String YELLOW_BD_BT = "\033[1;93m";
    private static final String BLUE_BD_BT = "\033[1;94m";
    private static final String PURPLE_BD_BT = "\033[1;95m";
    private static final String CYAN_BD_BT = "\033[1;96m";
    private static final String WHITE_BD_BT = "\033[1;97m";

    private static final String BLACK_BG_BT = "\033[0;100m";
    private static final String RED_BG_BT = "\033[0;101m";
    private static final String YELLOW_BG_BT = "\033[0;103m";
    private static final String BLUE_BG_BT = "\033[0;104m";
    private static final String PURPLE_BG_BT = "\033[0;105m";
    private static final String CYAN_BG_BT = "\033[0;106m";
    private static final String WHITE_BG_BT = "\033[0;107m";

    private static final int HR_LEN = 33;

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

        // repeat
        for (int i = 0; i < HR_LEN; i++) {
            // print dash
            System.out.print("-");
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