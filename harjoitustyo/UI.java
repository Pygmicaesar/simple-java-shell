package harjoitustyo;

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
            String command = In.readString();
            inputOK = true;

            String[] input = command.split(" ");

            switch (input[0]) {
                case CHANGEDIR:
                    tulkki.changeDirectory(input);
                    break;
                case CREATEDIR:
                    tulkki.createDirectory(input);
                    break;
                case CREATEFILE:
                    tulkki.createFile(input);
                    break;
                case RENAME:
                    tulkki.rename(input);
                    break;
                case LIST:
                    tulkki.listDirectory(input);
                    break;
                case FIND:
                    tulkki.printRecursive();
                    break;
                case COPY:
                    tulkki.copy(input);
                    break;
                case REMOVE:
                    tulkki.remove(input);
                    break;
                case EXIT:
                    System.out.println("Shell terminated.");
                    continueExecution = false;
                    break;
                default:
                    inputOK = false;
                    break;

            }

            if (!inputOK) {
                System.out.println(ERROR);
            }
        }
    }
}
