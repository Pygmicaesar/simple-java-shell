package harjoitustyo;

import harjoitustyo.apulaiset.*;
import harjoitustyo.tiedot.*;
import java.util.*;

/**
 * @author Jesse Sydänmäki 427665
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 */

public class Tulkki
{
    /**
     * Attributes
     */
    private Hakemisto ROOT;
    private Hakemisto workingDirectory;

    /**
     * Constructor
     * @param dirName name of the directory
     */
    public Tulkki(Hakemisto dirName) {
        this.ROOT = dirName;
        workingDirectory = dirName;
    }

    /**
     * Change to a different directory.
     *
     * @param args array of arguments
     */
    public void changeDirectory(String[] args) {

        if (args[0] == null || args.length > 1) {
            System.out.println("Error!");
        } else {
            if (args[0].isEmpty()) {
                workingDirectory = ROOT;
            } else if (args[0].equals("..")) {
                if (workingDirectory.equals(ROOT)) {
                    System.out.println("Error!");
                    return;
                }
                workingDirectory = workingDirectory.ylihakemisto();
            } else {
                LinkedList<Tieto> tmp = workingDirectory.hae(args[0]);

                if (!tmp.isEmpty()) {
                    if (tmp.getFirst() instanceof Hakemisto) {
                        workingDirectory = (Hakemisto) workingDirectory.hae(args[0]).getFirst();
                    } else {
                        System.out.println("Error!");
                    }
                } else {
                    System.out.println("Error!");
                }
            }
        }
    }

    /**
     * Create a directory.
     *
     * @param args array of arguments
     * @throws IllegalArgumentException
     */
    public void createDirectory(String[] args) throws IllegalArgumentException {

        if (args[0] != null
                && !args[0].isEmpty()
                && !checkIfExists(args[0])
                && args.length == 1) {
            if (args[0].matches(".*#+.*") || args[0].matches("..")) {
                throw new IllegalArgumentException();
            }

            String name = args[0];
            if (workingDirectory.hae(name).isEmpty()) {
                workingDirectory.lisaa(new Hakemisto(new StringBuilder(name), workingDirectory));
            }
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Creates a file.
     * 
     * @param args array of arguments
     */
    public void createFile(String[] args) throws IllegalArgumentException {

        if (args[0] != null
                && !args[0].isEmpty()
                && args.length <= 2
                && !checkIfExists(args[0])) {

            for (int i = 0; i < args.length; i++) {
                if (args[i].matches(".*[#%&+-]+.*")) {
                    throw new IllegalArgumentException();
                }
            }

            if (args.length == 2) {
                String name = args[0];
                int size = Integer.parseInt(args[1]);

                Tiedosto file = new Tiedosto(new StringBuilder(name), size);
                workingDirectory.lisaa(file);
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Renames a file.
     * 
     * @param args array of arguments
     */
    public void rename(String[] args) throws IllegalArgumentException {

        if (!args[0].isEmpty()
                && !args[1].isEmpty()
                && args.length == 2
                && checkIfExists(args[0])
                && !checkIfExists(args[1])) {

            for (int i = 0; i < args.length; i++) {
                if (args[i].matches(".*[#?%&+-]+.*")) {
                    throw new IllegalArgumentException();
                }
            }

            List<Tieto> currentName = workingDirectory.hae(args[0]);
            List<Tieto> newName = workingDirectory.hae(args[1]);

            if (!currentName.isEmpty()) {
                if (newName.isEmpty()) {
                    currentName.get(0).nimi(new StringBuilder(args[1]));
                }
                workingDirectory.sisalto().sort();
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * List all files.
     *
     * @param args array of arguments
     */
    public void listDirectory(String[] args) {

        if (!args[0].isEmpty()) {
            if (checkIfExists(args[0]) && args.length == 1) {

                LinkedList<Tieto> list = workingDirectory.hae(args[0]);

                if (list != null && list.size() > 0) {
                    list.forEach(System.out::println);
                } else if (args[0].equals("*.gif") || args[0].equals("*.txt")) {
                    System.out.println("Error!");
                }
            } else {
                System.out.println("Error!");
            }
        } else {
            workingDirectory.sisalto().forEach(ls -> System.out.println(ls));
        }
    }
    
    /**
     * Deletes a file.
     *
     * @throws IllegalArgumentException
     * @param args
     */
    public void remove(String[] args) throws IllegalArgumentException {

        if (!args[0].isEmpty()
                && args.length == 1
                && checkIfExists(args[0])) {

            LinkedList<Tieto> tmp = workingDirectory.hae(args[0]);

            if (tmp.isEmpty()) {
                throw new IllegalArgumentException();
            }
            for (Tieto item : tmp) {
                workingDirectory.poista(item);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
    * Checks if file/directory already exists in target directory.
     *
    * @param input
    * @return 
    */
    public boolean checkIfExists (String input) {
        if (input.contains("*"))
            return true;
            
        // Iterate through the working directory
        for (Tieto x : workingDirectory.sisalto()) {
            if (x.nimi().toString().equals(input)) {
               return true;
            }
        }
        return false;
    }

    /**
     * Returns path.
     *
     * @return directory path
     */
    public String path() {
        Hakemisto dir = workingDirectory;
        String path = "/";

        while (dir != ROOT) {
            path = "/" + dir.nimi().toString() + path;
            dir = dir.ylihakemisto();
        }
        return path;
    }
}