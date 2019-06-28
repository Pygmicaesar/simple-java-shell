package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.*;
import harjoitustyo.omat.RegexMachine;
import java.io.Serializable;

/**
 * @author Jesse Sydänmäki (js427665)
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

    // Constructor that takes parameters
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

        boolean success = false;

        if (newName != null) {
            String nameString = newName.toString();
            if (nameString.matches("^[._/\\w]*$") && !nameString.matches(".*([.])\\1.*")) {
                nimi = new StringBuilder(newName);
                success = true;
            }
        }
        if (!success) {
            throw new IllegalArgumentException();
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
        StringBuilder compare = obj.nimi();
        int value = nimi.toString().compareTo(compare.toString());

        if (value < 0) {
            value = -1;
        } else if (value > 0) {
            value = 1;
        } else {
            value = 0;
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
     * Compares two object and returns true if objects are the same
     *
     * @return true if objects are the same.
     */
    @Override
    public boolean equals(Object object) throws IllegalArgumentException {
        try {
            Tieto data = (Tieto) object;
            return this.nimi().toString().equals(data.nimi().toString());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Implemented methods
     */

    @Override
    public boolean equals(String searchTerm) throws IllegalArgumentException {
        try {
            return this.nimi().toString().matches(RegexMachine.createRegex(searchTerm));
        } catch (Exception e) {
            return false;
        }
    }
}