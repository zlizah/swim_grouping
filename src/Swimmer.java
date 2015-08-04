
import java.util.Calendar;
import java.util.HashMap;
import java.math.BigDecimal;

/**
 * Represents a swimmer that will be put into a group.
 *
 * Recommendations are represented as:
 *     Bronze 1-5 -> 1-5
 *     Silver 1-5 -> 11-15
 *     Gold 1-5 -> 21-25
 * This makes it difficult to group people recommended for bronze into silver
 * (but not impossible)
 */
public class Swimmer {
    /* Fields */
    public final Calendar BIRTHDATE;
    public final String NAME;
    public final String TOWN;
    public final String CURRENT_LEVEL;
    public final String CURRENT_GROUP;
    public final String RECOMENDATION;
    public final HashMap PRACTICE_TIMES;
    
    /* Constructor */
    public Swimmer(HashMap params) {
        //TODO Set fields
    }
    
    /**
     * Round to certain number of decimals
     */
    private static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    
    /* Generate coordinates for placement in a swimmer grid */
    public float[] generateGirdCoordinates() {
        /* Calculate age */
        Calendar now = Calendar.getInstance();
        float age = now.get(Calendar.YEAR) - BIRTHDATE.get(Calendar.YEAR);
        float monthDelta = now.get(Calendar.MONTH) - BIRTHDATE.get(Calendar.MONTH);
        if (monthDelta < 0) {
            age -= 1;
            monthDelta += 12;
        }
        age += (monthDelta / 12);
        age = round(age, 2);
        
        /* Calculate skill based on recommendation */
        float skill = 0;
        String[] recomWords = RECOMENDATION.split(" ");
        if (recomWords[0].equals("Silver")) {
            skill += 10;
        } else if (recomWords[0].equals("Gold")) {
            skill += 20;
        }
        skill += Integer.parseInt(recomWords[1]);
        
        /* Format and return */
        float[] ret = {age, skill};
        return ret;
    }
}