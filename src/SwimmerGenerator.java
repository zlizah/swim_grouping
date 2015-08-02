
import java.util.Random;

/**
 *
 */
public class SwimmerGenerator() {
    /* Generate a random string */
    private static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
    
    /* */
    public static Swimmer[] generateSwimmers(int amount) {
        Random rng = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < amount; i++) {
            //TODO Generate swimmers
        }
        return new Swimmer[1]();
    }
}