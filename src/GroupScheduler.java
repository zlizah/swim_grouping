
import java.util.LinkedList;
import java.util.Random;

/**
 * Main class that does the scheduling
 */
public class GroupScheduler {
    /* Fields */
    private GridPoint[] data_points;
    private GridPoint[] clusters;
    
    /* Constructor */
    public GroupScheduler(Swimmer[] swimmers) {
        /* Initialize lists that hold the data points and clusters */
        data_points = new GridPoint[swimmers.length];
        
        /* Fill the list of data points */
        for (int i = 0; i < swimmers.length; i++) { 
            Swimmer s = swimmers[i];
            float[] coords = s.generateGirdCoordinates();
            data_points[i] = new GridPoint(coords, s);
        }
    }
    
    /* Initialize the groups, current done randomly */
    private void initializeClusters(int amount) {
        Random rng = new Random();
        for (int i = 0; i < amount; i++) {
            /* Place each cluster at a random data point */
            int index = rng.nextInt(data_points.length);
            float[] coords = data_points[i].getPosition();
            clusters[i] = new GridPoint(coords, null);
        }
    }
    
    /* Main function used to do the scheduling */
    public void scheduleGroups(int groupAmount) {
        /* Clear old clusters and generate new */
        clusters = new GridPoint[groupAmount];
        initializeClusters(groupAmount);
        
        /* TODO Implement k-means */
    }
    
    /* Calculate the distance between two points on the grid */
    private float distance(GridPoint p1, GridPoint p2) {
        /* Fetch the point coordinates */
        float[] pos1 = p1.getPosition();
        float[] pos2 = p2.getPosition();
        if (pos1.length != pos2.length) {
            throw new IllegalArgumentException();
        }
        
        /* Store the squared coordinate delta for each dimension */
        float[] diffs = new float[pos1.length];
        for (int i = 0; i < pos1.length; i++) {
            float delta = pos1[i] - pos2[i];
            delta = delta * delta;
            diffs[i] = delta;
        }

        /* Sum and sqrt the squared diffs */
        double sum = 0;
        for (int i = 0; i < diffs.length; i++) {
            sum += diffs[i];
        }
        return (float)Math.sqrt(sum);
    }
}