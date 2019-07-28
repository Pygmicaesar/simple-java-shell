package harjoitustyo;

import java.util.*;
import harjoitustyo.apulaiset.In;
import harjoitustyo.tiedot.Hakemisto;

/**
 * @author Jesse Sydänmäki
 * Email: jesse.sydanmaki@protonmail.com
 * Github: Pygmicaesar
 */

public class UI {

    /**
     * Class constants
     */

    private final String CHANGEDIR = "cd";
    private final String CREATEDIR = "md";
    private final String CREATEFILE = "mf";
    private final String RENAME = "mv";
    private final String FIND = "find";
    private final String LIST = "ls";
    private final String COPY = "cp";
    private final String REMOVE = "rm";
    private final String ERROR = "Error!";
    private final String EXIT = "exit";

    public void run() {

        /**
         * Self-explanatory flag.
         */
        boolean continueExecution = true;

        /**
         * Initialize the command interpreter and output variable.
         */
        Tulkki tulkki = new Tulkki(new Hakemisto(new StringBuilder("/"), null));
        String output;
        boolean inputOK;

        /**
         * INTO THE LOOP WE GO
         */
        System.out.println("Welcome to SOS.");

        while(continueExecution) {

            // Print the working directory
            output = tulkki.path() + ">";
            System.out.print(output);

            // Read user input.
            String tempInput = In.readString();
            String[] input = tempInput.split(" ");

            String command = input[0];
            String[] args = Arrays.copyOfRange(input, 1, input.length);

            switch (command) {
                case CHANGEDIR:
                    tulkki.changeDirectory(args);
                    break;
                case CREATEDIR:
                    tulkki.createDirectory(args);
                    break;
                case CREATEFILE:
                    tulkki.createFile(args);
                    break;
                case RENAME:
                    tulkki.rename(args);
                    break;
                case LIST:
                    tulkki.listDirectory(args);
                    break;
                case REMOVE:
                    tulkki.remove(args);
                    break;
                case EXIT:
                    System.out.println("Shell terminated.");
                    continueExecution = false;
                    break;
                default:
                    System.out.println(ERROR);
                    break;

            }
        }
    }
}
