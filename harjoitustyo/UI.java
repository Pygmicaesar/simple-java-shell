package harjoitustyo;

import harjoitustyo.apulaiset.In;
import harjoitustyo.tiedot.Hakemisto;
import java.util.*;

/**
 * @author Jesse Sydänmäki 427665
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 */

public class UI {

    /**
     * Constants
     */
    private final String CHANGEDIR = "cd";
    private final String CREATEDIR = "md";
    private final String CREATEFILE = "mf";
    private final String RENAME = "mv";
    private final String LIST = "ls";
    private final String REMOVE = "rm";
    private final String EXIT = "exit";
    // Error message
    private final String ERROR = "Error!";

    // UI function
    public void run() {

        /**
         * Self-explanatory boolean
         */
        boolean continueExecution = true;

        /**
         * Initialize the command interpreter
         */
        Tulkki tulkki = new Tulkki(new Hakemisto(new StringBuilder("/"), null));
        
        /**
         * Welcome message.
         */
        System.out.println("Welcome to SOS.");
        
        // Into the loop we go...
        while(continueExecution) {

            String output = tulkki.path() + ">";
            System.out.print(output);

            // Read input from the user
            String tmp = In.readString();

            // Split input into command and arguments
            String[] input = tmp.split(" ");

            // The first element is the command
            String command = input[0];

            // It seems I need to resort to spaghetti code...
            String arg1 = "";
            String arg2 = "";

            if (input.length == 2) {
                arg1 = input[1];
            } else if (input.length >= 3) {
                arg1 = input[1];
                arg2 = input[2];
            }

            switch(command) {
                case CHANGEDIR:
                    if (arg1 == null || input.length > 2) {
                        System.out.println(ERROR);
                    } else {
                        tulkki.changeDirectory(arg1);
                    }
                    break;
                case CREATEDIR:
                    if (arg1 != null
                            && !arg1.isEmpty()
                            && !tulkki.checkIfExists(arg1)
                            && input.length == 2) {

                        try {
                            tulkki.createDirectory(arg1);
                        } catch (IllegalArgumentException e) {
                            System.out.println(ERROR);
                        }
                    } else {
                        System.out.println(ERROR);
                    }
                    break;
                case CREATEFILE:
                    if (arg1 != null
                            && !arg1.isEmpty()
                            && input.length <= 3
                            && !tulkki.checkIfExists(arg1)) {

                        try {
                            tulkki.createFile(input);
                        } catch (IllegalArgumentException e) {
                            System.out.println(ERROR);
                        }
                    } else {
                        System.out.println(ERROR);
                    }
                    break;
                case RENAME:
                    if (!arg1.isEmpty()
                            && !arg2.isEmpty()
                            && input.length == 3
                            && tulkki.checkIfExists(arg1)
                            && !tulkki.checkIfExists(arg2)) {

                        try {
                            tulkki.rename(input);
                        } catch (IllegalArgumentException e) {
                            System.out.println(ERROR);
                        }
                    } else {
                        System.out.println(ERROR);
                    }
                    break;
                case LIST:
                    if (!arg1.isEmpty()) {
                        if (tulkki.checkIfExists(arg1) && input.length == 2) {
                            tulkki.listDirectory(arg1);
                        } else {
                            System.out.println(ERROR);
                        }
                    } else {
                        tulkki.listDirectory(arg1);
                    }
                    break;
                case REMOVE:
                    if (!arg1.isEmpty()
                            && input.length == 2
                            && tulkki.checkIfExists(arg1)) {

                        try {
                            tulkki.remove(arg1);
                        } catch (Exception e) {
                            System.out.println(ERROR);
                        }
                    } else {
                        System.out.println(ERROR);;
                    }
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