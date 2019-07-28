package harjoitustyo.omalista;

import java.util.LinkedList;
import harjoitustyo.apulaiset.Ooperoiva;
import java.util.Iterator;
import java.util.Collections;

/**
 * @author Jesse Sydänmäki 427665
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 */

public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {

    /**
     * Implemented methods
     */

    /**
     * Adds elements to OmaLista
     * @return boolean based on whether the addition was successful
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean lisaa(E uusi) {
        boolean success = false;
        
        if (uusi instanceof Comparable) {
            int size = this.size();
            
            for (int i = 0; i < size && !success; i++) {
                // Iterate through the list
                if (((Comparable) uusi).compareTo(super.get(i)) < 0) {
                    super.add(i, uusi);
                    success = true; // Could be a better way to do this
                } 
            }

            if (!success) {
                // If list was empty, add a new element to the list
                super.add(uusi);
                success = true;
            }
        }
        return success;
    }

    /**
     * Removes the specified element.
     * The ist is iterated through using an iterator.
     * @return int number of elements removed
     */
    @SuppressWarnings({"unchecked"})
    public int poista(E poistettava) {
        // Counter
        int removed = 0;
        
        // Current element being iterated
        E currentElement = null;

        Iterator<E> iterator = iterator();
        
        // Use iterator to iterate through the list
        while (iterator.hasNext()) {
            currentElement = iterator.next();
            if (currentElement == poistettava) {
                iterator.remove();
                removed++;
            }
        }
        return removed;
    }
    
    /**
     * Sorting method for OmaLista
     */
    @SuppressWarnings({"unchecked"})
    public void sort() {
        Collections.sort(this, (e, t1) -> {

        if (e instanceof Comparable && t1 instanceof Comparable) {
            Comparable t = (Comparable) e;
            Comparable t2 = (Comparable) t1;
            return t.compareTo(t2);
        }
        return 0;
        });
    }
}