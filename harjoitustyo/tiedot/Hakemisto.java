package harjoitustyo.tiedot;

import harjoitustyo.omalista.OmaLista;
import harjoitustyo.apulaiset.Sailova;
import java.util.*;

/**
 * @author Jesse Sydänmäki 427665
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 */

public class Hakemisto extends Tieto implements Sailova<Tieto>
{
    /**
     * Attributes
     */

    private OmaLista<Tieto> sisalto;
    private Hakemisto ylihakemisto;

    /**
     * Constructors
     */
    
    // Default contructor
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

    public void sisalto(OmaLista<Tieto> list) throws IllegalArgumentException {
        if (list != null)
            sisalto = list;
        else
            throw new IllegalArgumentException();
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
     * @return number of files and subdirectories inside the directory
     */
    @Override
    public String toString() {
        return super.toString() + "/ " + sisalto.size();
    }
    
    /**
     * Implemented methods
     */

    /**
     * @return list of all elements that match 
     * the search term and are of type Tieto
     */
    @Override
    public LinkedList<Tieto> hae(String searchTerm) {
        LinkedList<Tieto> found = new LinkedList<>();
        
        for (Tieto item : sisalto) {
            if (item.equals(searchTerm))
                found.add(item);
        }
        return found;  
    }


    /**
     * Adds something to a directory.
     * 
     * @param lisattava data to add into Hakemisto
     * @return boolean based on whether the addition was successful
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
     * Deletes a file from the directory
     * 
     * @param poistettava the file to remove
     * @return boolean based on whether something was deleted
     */
    @Override
    public boolean poista(Tieto poistettava) {
        if (poistettava != null) {
            for (Tieto item : sisalto) {
                if (item == poistettava) {
                    sisalto.remove(poistettava);
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }
}