
import java.util.LinkedList;

/**
 * Main class the does the scheduling
 */
public class GroupScheduler {
    /* Fields */
    private LinkedList<GridPoint> data_points;
    private LinkedList<GridPoint> clusters;
    
    /* Constructor */
    public GroupScheduler(Swimmer[] swimmers) {
        /* Initialize lists that hold the data points and clusters */
        data_points = new LinkedList<GridPoint>();
        clusters = new LinkedList<GridPoint>();
        
        /* Fill the list of data points */
        for (Swimmer s : swimmers) { 
            float[] coords = s.generateGirdCoordinates();
            data_points.add(new GridPoint(coords, s));
        }
        
        //TODO Figure out how to represent groups (clusters)
    }
    
    /* Main function used to do the scheduling */
    public void scheduleGroups() {
        //TODO Implement k-means
    }
    
    /* Calculate the distance between two points on the grid */
    private float calculateDistance(GridPoint p1, GridPoint p2) {
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