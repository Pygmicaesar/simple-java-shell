package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.*;
import java.io.Serializable;
import java.lang.*;
import harjoitustyo.omat.*;

/**
 * @author Jesse Sydänmäki 427665
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 */

public abstract class Tieto implements Tietoinen, Serializable, Comparable<Tieto> {

    /**
     * Attributes
     */

    private StringBuilder nimi;

    /**
     * Constructors
     */

    // Default constructor
    public Tieto() throws IllegalArgumentException {
        nimi = new StringBuilder("");
    }

    // Constructor that receives params
    public Tieto(StringBuilder newName) throws IllegalArgumentException {
        if (newName == null) {
            throw new IllegalArgumentException();
        } else {
            nimi(newName);
        }
    }

    /**
     * Access methods
     */

    public StringBuilder nimi() {
        return nimi;
    }

    public void nimi(StringBuilder newName) throws IllegalArgumentException {
        if (newName == null) {
            throw new IllegalArgumentException();
        } else {
            nimi = new StringBuilder(newName);
        }
    }

    /**
     * Class methods
     */

    @Override
    public String toString() {
        return nimi.toString();
    }

    public int compareTo(Tieto obj) {
        String name = this.toString();
        String objName = obj.toString();

        int value = name.compareTo(objName);

        if (value < 0) {
            value = -1;
        } else if (value > 0) {
            value = 1;
        }

        return value;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + nimi.toString().hashCode();
        return result;
    }

    /**
     * Compares two objects and returns true if objects are same
     * 
     * @return true if objects are the same
     */
    @Override
    public boolean equals(Object obj) throws IllegalArgumentException {
        try {
            Tieto data = (Tieto)obj;
            return this.nimi().toString().equals(data.nimi().toString());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Implemented methods
     */
    
    /**
     * @param searchTerm search term
     * @return boolean true if matches, false if not.
     */
    @Override
    public boolean equals(String searchTerm) {
        if (searchTerm == null) {
            return false;
        } else if (this.nimi().toString().equals(searchTerm)) {
            return true;
        } else if (searchTerm.equals("*")) {
            return true;
        }
        
        return EqualsHelper.checkEquals(searchTerm, nimi());
    }
}