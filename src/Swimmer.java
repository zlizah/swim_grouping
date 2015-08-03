
import java.util.Calendar;
import java.util.HashMap;
import java.math.BigDecimal;

/**
 * Represents a swimmer that will be put into a group.
 */
public class Swimmer {
    /* Fields */
    public final Calendar BIRTHDATE;
    public final string NAME;
    public final string TOWN;
    public final string CURRENT_LEVEL;
    public final string CURRENT_GROUP;
    public final string RECOMENDATION;
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
    public generateGirdCoordinates() {
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
        
        //TODO Figure out how to represent recommendation
    }
}