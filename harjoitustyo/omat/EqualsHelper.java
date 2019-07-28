package harjoitustyo.omat;

/**
 * @author Jesse Sydänmäki (js427665)
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 *
 * The purpose of this class is to avoid complete SPAGHETT in Tieto.java
 */

final public class EqualsHelper {

    public static boolean checkEquals(String searchTerm, StringBuilder name) {
        String tmp = searchTerm.replaceAll("\\*", "");
        int asterisks = checkAsterisks(searchTerm);
       
        if (asterisks == 2) {
            return name.toString().contains(tmp);
        } else if (asterisks == 1) {
            if (searchTerm.startsWith("*")) {
                return name.toString().endsWith(tmp);
            } else {
                return name.toString().startsWith(tmp);
            }
        } else {
            return false;
        }
    }

    /**
     * Counts the number of asterisks in a string
     * @param input
     * @return int number of asterisks found in input
     */
    private static int checkAsterisks(String input) {
        int found = 0;
        
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '*') {
                found++;
                if (i < input.length() - 1) {
                    if (input.charAt(i+1) == '*' 
                        || (found == 2 && i != input.length() - 1)) {
                        return -1;
                    }
                }
            }
        }
        
        if (found > 2)
            return -1;
        
        return found;
    }
}