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
     * Attributes
     */
    private final String CHANGEDIR = "cd";
    private final String CREATEDIR = "md";
    private final String CREATEFILE = "mf";
    private final String RENAME = "mv";
    private final String LIST = "ls";
    private final String REMOVE = "rm";
    private final String EXIT = "exit";

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
            String temp = In.readString();

            // Split input into command and arguments
            String[] input = temp.split(" ");
            
            String command = input[0];
            String[] args = Arrays.copyOfRange(input, 1, input.length - 1);

            switch(command) {
                case CHANGEDIR:
                    tulkki.changeDirectory(args);
                    break;
                case CREATEDIR:
                    try {
                        tulkki.createDirectory(args);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error!");
                    }
                    break;
                case CREATEFILE:
                    try {
                        tulkki.createFile(args);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error!");
                    }
                    break;
                case RENAME:
                    try {
                        tulkki.rename(args);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error!");
                    }
                    break;
                case LIST:
                    tulkki.listDirectory(args);
                    break;
                case REMOVE:
                    try {
                        tulkki.remove(args);
                    } catch (Exception e) {
                        System.out.println("Error!");
                    }
                    break; 
                case EXIT:
                    System.out.println("Shell terminated.");
                    continueExecution = false;
                    break;
                default:
                    System.out.println("Error!");
                    break;
            }
        }
    }
}