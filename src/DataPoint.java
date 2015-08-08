
/**
 * Represents a data point on the grid in the form of a swimmer.
 */
public class DataPoint implements GridPoint {
    /* Fields */
    private Swimmer swimmer;
    private float[] coordinates;
    
    /* Constructor */
    public DataPoint(float[] startPosition, Swimmer swimmer) {
        coordinates = startPosition;
        this.swimmer = swimmer;
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
    
    /* Fetches the swimmer object of this DataPoint */
    public Swimmer getSwimmer() {
        return swimmer;
    }
}