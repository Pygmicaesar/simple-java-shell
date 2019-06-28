package harjoitustyo.apulaiset;


// Otetaan k�ytt��n Javan linkitetty lista.
import java.util.LinkedList;

/**
 * Hakemiston k�sittelyyn soveltuvia metodeja. Kiinnit� geneerinen tyyppi T
 * tyypiksi Tieto, kun toteutat t�m�n rajapinnan metodit Hakemisto-luokassa.
 * <p>
 * Harjoitusty�, Olio-ohjelmoinnin perusteet I, kev�t 2019.
 * <p>
 * @author Jorma Laurikkala (jorma.laurikkala@tuni.fi), Informaatioteknologian
 * ja viestinn�n tiedekunta, Tampereen yliopisto.
 *
 */

public interface Sailova<T> {
   /**
    * Hakee hakemistosta tiedostoja ja alihakemistoja, joiden nimi vastaa annettua
    * hakusanaa. Hakusana voi olla tiedon nimi sellaisenaan (esimerkiksi
    * "grumpy_cat.jpeg") tai yhden tai kahden jokerimerkin avulla muodostettu ilmaus
    * (esimerkiksi "*.jpeg").
    * <p>
    * Paluuarvona palautettavalla listalla on yksi viite, jos parametri on hakemistossa
    * olevan tiedon nimi. Jokerimerkkien avulla voidaan l�yt�� useampia tietoja.
    * <p>
    * Hy�dynt�� Tieto-luokassa toteutettavaa {@link Tietoinen#equals(String)}
    * -metodia siten, ett� palautettavalle listalle lis�t��n jokainen hakemiston
    * listalla oleva viite x = get(ind), jolle lauseke "x.equals(hakusana)" on totta.
    * Haku alkaa hakemiston ensimm�isest� alkiosta ja p��ttyy hakemiston viimeiseen
    * alkioon. Viitteet l�ydettyihin alkioihin ovat palautettavalla listalla samassa
    * j�rjestyksess� kuin hakemiston listalla.
    * <p>
    * @param hakusana nimi tai ilmaus, johon hakemiston tiedostojen ja alihakemistojen
    * nimi� verrataan.
    * @return lista, jolla on viitteet l�ydettyihin tietoihin, joiden nimet vastaavat
    * parametria. Lista on tyhj� eli nolla viitett� sis�lt�v� lista, jos hakemistossa
    * ei ole tietoja, joiden nimet vastavat parametria, parametri on null-arvoinen,
    * ilmauksessa on k�ytetty jokerimerkkej� v��rin tai hakemisto on tyhj�.
    * @see Tietoinen#equals(String)
    */
   abstract public LinkedList<T> hae(String hakusana);

   /**
    * Lis�� hakemistoon tiedoston tai alihakemiston. Kutsuu hakemiston listan
    * toteuttamaa Ooperoiva-rajapinnan lisaa-metodia, jolla tieto saadaan lis�tyksi
    * oikeaan paikkaan listalla. Lis�ys onnistuu, jos parametri liittyy olioon,
    * jonka luokalla on Comparable-rajapinnan compareTo-metodin toteutus. Null-arvon
    * lis�ys ep�onnistuu aina.
    *
    * @param lisattava viite lis�tt�v��n tietoon.
    * @return true, jos lis�ys onnistui. False, jos lis�ys ep�onnistui.
    */
   abstract public boolean lisaa(T lisattava);

   /**
    * Poistaa hakemistosta tiedoston tai alihakemiston. Kutsuu hakemiston listan
    * toteuttamaa Ooperoiva-rajapinnan poista-metodia.
    *
    * @param poistettava viite poistettavaan tietoon.
    * @return true, jos alkio poistettiin. False, jos poistettavaa alkiota ei l�ydetty
    * tai parametri on null.
    */
   abstract public boolean poista(T poistettava);
}
