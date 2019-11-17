package harjoitustyo;

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
     * @param arg target directory, oh my god why does this not work
     */
    public void changeDirectory(String arg) {

        if (arg.isEmpty() || arg == null) {
            workingDirectory = ROOT;
        } else if (arg.equals("..")) {
            if (workingDirectory.equals(ROOT)) {
                System.out.println("Error!");
                return;
            }
            workingDirectory = workingDirectory.ylihakemisto();
        } else {
            LinkedList<Tieto> tmp = workingDirectory.hae(arg);

            if (!tmp.isEmpty()) {
                if (tmp.getFirst() instanceof Hakemisto) {
                    workingDirectory = (Hakemisto) workingDirectory.hae(arg).getFirst();
                } else {
                    System.out.println("Error!");
                }
            } else {
                System.out.println("Error!");
            }
        }
    }

    /**
     * Create a directory.
     *
     * @param arg directory name
     * @throws IllegalArgumentException
     */
    public void createDirectory(String arg) throws IllegalArgumentException {

        if (arg.matches(".*#+.*") || arg.matches("..")) {
            throw new IllegalArgumentException();
        }

        if (workingDirectory.hae(arg).isEmpty()) {
            workingDirectory.lisaa(new Hakemisto(new StringBuilder(arg), workingDirectory));
        }

        /*
        if (args.length == 2) {

        } else {
            throw new IllegalArgumentException();
        }
        */
    }

    /**
     * Creates a file.
     * 
     * @param args file name and size
     */
    public void createFile(String[] args) throws IllegalArgumentException {
        for (int i = 1; i < args.length; i++) {
            if (args[i].matches(".*[#%&+-]+.*")) {
                throw new IllegalArgumentException();
            }
        }

        if (args.length == 3) {
            String name = args[1];
            int size = Integer.parseInt(args[2]);

            Tiedosto file = new Tiedosto(new StringBuilder(name), size);
            workingDirectory.lisaa(file);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Renames a file.
     * 
     * @param args current name and new name
     */
    public void rename(String[] args) throws IllegalArgumentException {
        for (int i = 1; i < args.length; i++) {
            if (args[i].matches(".*[#?%&+-]+.*")) {
                throw new IllegalArgumentException();
            }
        }

        List<Tieto> currentName = workingDirectory.hae(args[1]);
        List<Tieto> newName = workingDirectory.hae(args[2]);

        if (!currentName.isEmpty()) {
            if (newName.isEmpty()) {
                currentName.get(0).nimi(new StringBuilder(args[2]));
            }
            workingDirectory.sisalto().sort();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * List all files.
     *
     * @param arg target directoryyyyy
     */
    public void listDirectory(String arg) {

        if (arg.isEmpty()) {
            workingDirectory.sisalto().forEach(ls -> System.out.println(ls));
            return;
        }

        LinkedList<Tieto> list = workingDirectory.hae(arg);

        if (list != null && list.size() > 0) {
            list.forEach(System.out::println);
        } else if (arg.equals("*.gif") || arg.equals("*.txt")) {
            System.out.println("Error!");
        }
    }
    
    /**
     * Deletes a file.
     *
     * @throws IllegalArgumentException
     * @param arg target file
     */
    public void remove(String arg) throws IllegalArgumentException {

        LinkedList<Tieto> tmp = workingDirectory.hae(arg);

        if (tmp.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (Tieto item : tmp) {
            workingDirectory.poista(item);
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