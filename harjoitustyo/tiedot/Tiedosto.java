package harjoitustyo.tiedot;

import harjoitustyo.apulaiset.Syvakopioituva;
import java.io.*;

/**
 * @author Jesse Sydänmäki 427665
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

    // Constructor that receives parameters
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
            // Byte-type elements into array - stream.
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            // Stream that converts object into bytes, which is then attached to
            // stream.
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            // Write the object into an array in byte format
            oos.writeObject(this);

            // Empty the buffer and close OOS-stream.
            oos.flush();
            oos.close();

            // Liitetään taulukkoon tavuja lukeva syötevirta.
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());

            // Tavut olioksi muuttava virta, joka liittyy taulukosta lukevaan virtaan.
            ObjectInputStream ois = new ObjectInputStream(bis);

            // Kopio saadaan aikaiseksi lukemalla olion tavut taulukosta.
            Object kopio = ois.readObject();

            // Return the typecast object
            return (Tiedosto) kopio;

        } catch (InvalidClassException ice) {
            ice.printStackTrace();
            return null;
        } catch (NotSerializableException nse) {
            nse.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("EL PANIQUE!");
            e.printStackTrace();
            return null;
        }   
    }
}