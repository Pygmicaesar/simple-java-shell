package harjoitustyo.iteraattorit;

import harjoitustyo.tiedot.Hakemisto;
import harjoitustyo.tiedot.Tieto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jesse Sydänmäki
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 */

public class HakemistoIteraattori<E> implements Iterator<E> {

    /**
     * Attributes
     */
    private Hakemisto sisalto;
    private List<E> tulostettava;
    private int index;

    /**
     * Constructors
     */
    public HakemistoIteraattori(Hakemisto contents) {
        sisalto = contents;
        tulostettava = new ArrayList<>();
        preorder(this.sisalto, tulostettava);
    }

    @Override
    public boolean hasNext() {
        return index < tulostettava.size();
    }

    @Override
    public E next() {
        E returned = tulostettava.get(index);
        index++;
        return returned;
    }

    /**
     * List directory structure
     */
    private void preorder(Hakemisto dir, List data) {
        int i = 0;
        while (i < dir.sisalto().size()) {
            Tieto item = dir.sisalto().get(i);
            tulostettava.add((E) item);
            if (item instanceof Hakemisto) {
                preorder((Hakemisto) item, data);
            }
            i++;
        }
    }
}
