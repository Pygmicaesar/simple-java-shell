package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Syvakopioituva;
import java.io.*;

/**
 * @author Jesse Sydänmäki (js427665)
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 */


public class Tiedosto extends Tieto implements Syvakopioituva<Tiedosto> {

    /**
     * Attributes
     */

    private int koko;

    /**
     * Constructors
     */

    // Default constructor
    public Tiedosto() {
        super();
        koko(0);
    }

    // Constructor that takes parameters
    public Tiedosto(StringBuilder newName, int newSize) throws IllegalArgumentException {
        super(newName);
        koko(newSize);
    }

    /**
     * Access methods
     */

    public int koko() {
        return koko;
    }

    public void koko(int newSize) throws IllegalArgumentException {
        if (newSize < 0) {
            throw new IllegalArgumentException();
        } else {
            koko = newSize;
        }
    }

    /**
     * Class methods
     */

    @Override
    public String toString() {
        return super.toString() + " " + koko();
    }

    /**
     * Implemented methods
     */

    @Override
    public Tiedosto kopioi() {

        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(this);
            oos.flush();
            oos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            Object copy = ois.readObject();

            return (Tiedosto) copy;

        } catch (InvalidClassException ice) {
            ice.printStackTrace();
            return null;
        } catch (NotSerializableException nse) {
            nse.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Time to panic!");
            e.printStackTrace();
            return null;
        }
    }
}