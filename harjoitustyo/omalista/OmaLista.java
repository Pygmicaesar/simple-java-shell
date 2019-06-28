package harjoitustyo.omalista;

import java.util.LinkedList;
import harjoitustyo.apulaiset.Ooperoiva;

/**
 * @author Jesse Sydänmäki (js427665)
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 */

public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    public OmaLista() {
        super();
    }

    /**
     * Implemented methods
     */

    /**
     * Adds elements to OmaLista.
     *
     * @return boolean based on whether the element was added successfully.
     */

    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean lisaa(E uusi) {
        boolean success = false;

        if (uusi instanceof Comparable) {
            int size = this.size();

            for (int i = 0; i < size && !success; i++) {
                if (((Comparable) uusi).compareTo(super.get(i)) < 0) {
                    super.add(i, uusi);
                    success = true;
                }
            }

            if (!success) {
                super.add(uusi);
                success = true;
            }
        }
        return success;
    }

    /**
     * Removes specified element from list.
     *
     * @return the number of removed elements as an integer.
     */
    @SuppressWarnings({"unchecked"})
    public int poista(E poistettava) {
        int count = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(poistettava)) {
                super.remove(i);
                i--;
                count++;
            }
        }
        return count;
    }
}