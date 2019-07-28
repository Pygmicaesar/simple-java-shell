package harjoitustyo;

import harjoitustyo.tiedot.*;
import harjoitustyo.omalista.OmaLista;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Jesse Sydänmäki
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 */

public class Tulkki {

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
    public void createDirectory(String[] input) {
        String nimi = input.length == 2 ? input[1] : null;
        workingDirectory.lisaa(new Hakemisto(new StringBuilder(nimi), workingDirectory));
    }

    /**
     * Change the working directory
     *
     * @param
     */
    public void changeDirectory(String[] input) {

        if (input.length < 2) {
            workingDirectory = ROOT;
        } else if (input[1].equals("..") && workingDirectory.ylihakemisto() != null) {
            workingDirectory = workingDirectory.ylihakemisto();
        } else {
            String directoryName = input[1];

            Tieto target = workingDirectory.hae(directoryName).isEmpty()
                    ? null : workingDirectory.hae(directoryName).getFirst();

            if (target instanceof Hakemisto) {
                workingDirectory = (Hakemisto) target;
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
         * Iterate thrugh the hierarchy
         */
        while (helper != ROOT) {
            path = "/" + helper.nimi().toString() + path;
            helper = helper.ylihakemisto();
        }

        return path;
    }

    /**
     * Create a file.
     * @param input
     */
    public void createFile(String[] input) {
        String name = input.length <= 3 ? input[1] : null;

        if (name != null) {
            int size = input.length == 3 ? Integer.parseInt(input[2]) : 0;
            Tiedosto newFile = new Tiedosto(new StringBuilder(name), size);
            workingDirectory.lisaa(newFile);
        }
    }

    /**
     * Rename
     * @param input
     */
    public void rename(String[] input) {
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
    }

    /**
     * List
     */
    public void listDirectory(String[] input) {
        if (input.length == 1) {
            workingDirectory.sisalto().forEach(ls -> System.out.println(ls));
        } else if (input.length == 2) {
            LinkedList<Tieto> haku = workingDirectory.hae(input[1]);
            if (haku != null && haku.size() > 0)
                haku.forEach(System.out::println);
            else if (input[1].equals("*.txt") || input[1].equals("*.gif")) {
                System.out.println("Error!");
            }
        }
    }

    /**
     * Copy
     */
    public void copy(String[] input) {
        String fileName = input[1];
        String newFileName = input[2];

        OmaLista<Tieto> targets = new OmaLista();

        for (Tieto item : workingDirectory.hae(fileName)) {
            if (item instanceof Tiedosto) {
                Tiedosto copy = ((Tiedosto) item).kopioi();
                copy.nimi(new StringBuilder(newFileName));
                targets.add(copy);
            }
        }
        targets.forEach(item -> workingDirectory.lisaa(item));
    }

    /**
     * Remove
     */
    public void remove(String[] input) {
        String parameter = input.length == 2 ? input[1] : null;
        if (parameter != null) {
            for (Tieto item : workingDirectory.hae(parameter)) {
                workingDirectory.poista(item);
            }
        }
    }

    public void printRecursive() {
        Iterator itr = workingDirectory.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
