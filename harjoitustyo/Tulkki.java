package harjoitustyo;

import harjoitustyo.tiedot.*;
import harjoitustyo.omalista.OmaLista;
import java.util.*;

/**
 * @author Jesse Sydänmäki
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 */

public class Tulkki {

    private final String ERROR = "Error!";

    /**
     * Attributes
     */
    private final Hakemisto ROOT;
    private Hakemisto workingDirectory;

    /**
     * Constructor
     *
     * @param ROOT
     */
    public Tulkki(Hakemisto ROOT) {
        this.ROOT = ROOT;
        workingDirectory = ROOT;
    }

    /**
     * Create new directory
     */
    public void createDirectory(String[] args) throws IllegalArgumentException {

        if (args[0] != null
                && !args[0].isEmpty()
                && !checkIfExists(args[0])
                && args.length == 1) {
            try {
                if (args[0].matches(".*#+.*") || args[0].matches("..")) {
                    throw new IllegalArgumentException();
                }
                String name = args[0];
                if (workingDirectory.hae(name).isEmpty()) {
                    workingDirectory.lisaa(new Hakemisto(new StringBuilder(name), workingDirectory));
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR);
            }
        } else {
            System.out.println(ERROR);
            throw new IllegalArgumentException();
        }
    }

    /**
     * Change the working directory
     *
     * @param
     */
    public void changeDirectory(String[] args) {

        if (args.length > 1) {
            System.out.println(ERROR);
        } else {
            if (args[0].isEmpty()) {
                workingDirectory = ROOT;
            } else if (args[0].equals("..") && workingDirectory.ylihakemisto() != null) {
                workingDirectory = workingDirectory.ylihakemisto();
            } else {
                String directoryName = args[0];

                Tieto target = workingDirectory.hae(directoryName).isEmpty()
                        ? null : workingDirectory.hae(directoryName).getFirst();

                if (target instanceof Hakemisto) {
                    workingDirectory = (Hakemisto) target;
                }
            }
        }
    }

    /**
     * Print the working directory
     * @return
     */
    public String path() {
        /**
         * Helper variable.
         */
        Hakemisto helper = workingDirectory;
        String path = "/";

        /**
         * Iterate through the hierarchy
         */
        while (helper != ROOT) {
            path = "/" + helper.nimi().toString() + path;
            helper = helper.ylihakemisto();
        }

        return path;
    }

    /**
     * Create a file.
     * @param args array containing filename and size
     */
    public void createFile(String[] args) throws IllegalArgumentException {

        if (args[0] != null
                && !args[0].isEmpty()
                && args.length <= 2
                && !checkIfExists(args[0])) {
            try {
                if (args[0].matches(".*[#%&+-]+.*")) {
                    throw new IllegalArgumentException();
                }
                if (args.length == 2) {
                    String name = args[0];
                    int size = Integer.parseInt(args[1]);
                    Tiedosto newFile = new Tiedosto(new StringBuilder(name), size);
                    workingDirectory.lisaa(newFile);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR);
            }

        } else {
            System.out.println(ERROR);
            throw new IllegalArgumentException();
        }
    }

    /**
     * Rename
     * @param args
     */
    public void rename(String[] args) {

        if (!args[0].isEmpty()
                && !args[1].isEmpty()
                && args.length == 2
                && checkIfExists(args[0])
                && !checkIfExists(args[1])) {
            try {
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
                    // workingDirectory.sisalto().sort();
                    Collections.sort(workingDirectory.sisalto());
                } else {
                    throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException e) {
                System.out.println(ERROR);
            }
        } else {
            System.out.println(ERROR);
            throw new IllegalArgumentException();
        }

        /*
        // Flag for checking input length
        boolean paramOK = input.length == 3;

        // Read the file names, null if the parameters are invalid
        String fileName = paramOK ? input[1] : null;
        String newFileName = paramOK ? input[2] : null;

        // Check if the given file name is available
        boolean available = workingDirectory.hae(newFileName).isEmpty();

        if (available) {
            for (Tieto item : workingDirectory.sisalto()) {
                if (item.equals(fileName)) {
                    item.nimi(new StringBuilder(newFileName));
                }
            }
        }

         */
    }

    /**
     * List
     */
    public void listDirectory(String[] args) {

        if (!args[0].isEmpty()) {
            if (checkIfExists(args[0]) && args.length == 1) {
                LinkedList<Tieto> haku = workingDirectory.hae(args[0]);

                if (haku != null && haku.size() > 0) {
                    haku.forEach(System.out::println);
                } else if (args[0].equals("*.txt") || args[0].equals("*.gif")) {
                    System.out.println(ERROR);
                }
            } else {
                System.out.println(ERROR);
            }
        } else {
            workingDirectory.sisalto().forEach(info -> System.out.println(info));
        }
    }

    /**
     * Remove
     */
    public void remove(String[] args) {

        String parameter = args.length == 1 ? args[0] : null;
        if (parameter != null) {
            for (Tieto item : workingDirectory.hae(parameter)) {
                workingDirectory.poista(item);
            }
        }
    }

    /**
     * Check if file or directory already exists in the target directory
     * @param arg the name to check
     * @return
     */
    public boolean checkIfExists (String arg) {
        if (arg.contains("*")) {
            return true;
        }
        for (Tieto x : workingDirectory.sisalto()) {
            if (x.nimi().toString().equals(arg)) {
                return true;
            }
        }
        return false;
    }
}
