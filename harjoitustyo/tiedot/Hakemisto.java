package harjoitustyo.tiedot;

import harjoitustyo.iteraattorit.HakemistoIteraattori;
import harjoitustyo.omalista.OmaLista;
import harjoitustyo.apulaiset.Sailova;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Jesse Sydänmäki (js427665)
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 */

public class Hakemisto extends Tieto implements Sailova<Tieto>, Iterable {

    /**
     * Attributes
     */

    private OmaLista<Tieto> sisalto;
    private Hakemisto ylihakemisto;

    /**
     * Constructors
     */

    // Default constructor
    public Hakemisto() {
        super();
        sisalto = new OmaLista<>();
        ylihakemisto = null;
    }

    // Constructor that receives parameters
    public Hakemisto(StringBuilder newName, Hakemisto parentDir) throws IllegalArgumentException {
        super(newName);
        sisalto = new OmaLista<>();
        ylihakemisto = parentDir;
    }

    /**
     * Access methods
     */

    public OmaLista<Tieto> sisalto() {
        return sisalto;
    }

    public void sisalto(OmaLista<Tieto> list) {
        sisalto = list;
    }

    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }

    public void ylihakemisto(Hakemisto parentDir) {
        ylihakemisto = parentDir;
    }


    /**
     * Class methods
     */

    /**
     * toString method
     *
     * @return number of files and subdirectories inside the directory
     */
    @Override
    public String toString() {
        return super.toString() + (!nimi().toString().equals("/") ? "/ " : " ") + sisalto.size();
    }

    @Override
    public boolean equals(String searchTerm) {
        boolean equal = super.equals(searchTerm);
        if (equal) {
            return equal;
        }
        return nimi().toString().equals(searchTerm);
    }

    /**
     * Implemented methods
     */

    /**
     * @return OmaLista<Tieto> list of all elements that match the search term
     * and are of type Tieto
     */
    @Override
    public LinkedList<Tieto> hae(String searchTerm) {

        OmaLista<Tieto> found = new OmaLista<>();

        if (searchTerm == null) {

            return found;

        } else {

            for (Tieto item : sisalto) {
                if (item.equals(searchTerm)) {
                    found.lisaa(item);
                }
            }
            return found;
        }
    }

    /**
     * Adds something to a directory.
     *
     * @param lisattava
     * @return boolean based of whether the addition was successful
     */
    @Override
    public boolean lisaa(Tieto lisattava) {
        if (lisattava != null) {
            sisalto.lisaa(lisattava);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes file from directory.
     *
     * @param poistettava
     * @return boolean based on whether something was deleted
     */
    @Override
    public boolean poista(Tieto poistettava) {
        return sisalto.poista(poistettava) > 0;
    }

    @Override
    public Iterator<Tieto> iterator() {
        return new HakemistoIteraattori<>(this);
    }
}