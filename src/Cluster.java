
import java.util.ArrayList;

/**
 * Represents a cluster on the grid.
 */
public class Cluster implements GridPoint {
    /* Fields */
    private float[] coordinates;
    private ArrayList<DataPoint> assignedDataPoints;
    
    /* Constructor */
    public Cluster(float[] startPosition) {
        coordinates = startPosition;
        assignedDataPoints = new ArrayList<DataPoint>();
    }
    
    /* Clears all data points assigned to this cluster */
    public void clearDataPoints() {
        assignedDataPoints = new ArrayList<DataPoint>();
    }
    
    /* Get current position of this point */
    public float[] getPosition() {
        return coordinates;
    }
    
    /* Set the current grid position of this GridPoint */
    public void setPosition(float[] newPosition) {
        if (newPosition.length != coordinates.length) {
            throw new IllegalArgumentException();
        }
        coordinates = newPosition;
    }
    
    /* Appends a DataPoint to this Cluster */
    public void addPoint(DataPoint dp) {
        assignedDataPoints.add(dp);
    }
    
    /* Return a stack of all data points assigned to this cluster */
    public ArrayList<DataPoint> getDataPoints() {
        return assignedDataPoints;
    }
}