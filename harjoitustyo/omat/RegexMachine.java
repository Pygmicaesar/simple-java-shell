package harjoitustyo.omat;

/**
 * @author Jesse Sydänmäki (js427665)
 * Email: jesse.sydanmaki@tuni.fi
 * Github: Pygmicaesar
 *
 * The purpose of this class is to avoid complete SPAGHETT in Tieto.java
 */

final public class RegexMachine {

    public static String createRegex(String searchTerm) {
        String regex = searchTerm.contains("*") ? "" : searchTerm;

        boolean starts = searchTerm.startsWith("*");
        boolean ends = searchTerm.endsWith("*");
        boolean wildcardCheck = !searchTerm.matches(".*([*])\\1.*");

        /*
         * SUMMON THE DEMONS
         */
        if (wildcardCheck) {

            if (searchTerm.equals("*")) {
                regex = "(.*?)";
            } else if (starts && ends) {
                regex = ".+?" + searchTerm.substring(1, searchTerm.length() - 1) + ".+?$";
            } else if (starts) {
                regex = ".*" + searchTerm.substring(1) + "(\\d)?$";
            } else if (ends) {
                regex = "^" + searchTerm.substring(0, searchTerm.length() - 1) + ".*$";
            }
        }
        return regex;
    }
}
