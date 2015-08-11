
import java.util.Random;
import java.util.HashMap;

/**
 * Class used for generating swimmers in testing purposes.
 */
public class SwimmerGenerator {
    /* Generate a random string */
    private static String randStr(Random rng, int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
    
    /* Generates a random recommendation */
    private static String randGroup(Random rng) {
        String[] levels = new String[] {"Bronze", "Silver", "Gold"};
        String group = levels[rng.nextInt(3)];
        int level = rng.nextInt(5) + 1;
        group += " " + level;
        return group;
    }
    
    /* Generates a random birth date */
    private static String randDate(Random rng) {
        int[] years = new int[] {2000, 2001, 2002};
        int year = years[rng.nextInt(3)];
        int month = rng.nextInt(12) + 1;
        int day = rng.nextInt(28) + 1;
        String date = "" + year + "-" + month + "-" + day;
        return date;
    }
    
    /* Generate some swimmers as test data */
    public static Swimmer[] generateSwimmers(int amount) {
        Random rng = new Random();
        Swimmer[] swimmers = new Swimmer[amount];
        
        /* Create the Swimmers */
        for (int i = 0; i < amount; i++) {
            /* Generate a swimmer with random everything */
            HashMap<String, String> attributes = new HashMap<String, String>();
            attributes.put("first_name", randStr(rng, 5));
            attributes.put("last_name", randStr(rng, 5));
            attributes.put("town", "Solna");
            attributes.put("level", randGroup(rng));
            attributes.put("group_name", randStr(rng, 5));
            attributes.put("recommendation", randGroup(rng));
            attributes.put("birth_date", randDate(rng));
            
            /* Add the swimmer */
            swimmers[i] = new Swimmer(attributes);
        }
        return swimmers;
    }
}