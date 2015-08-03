
/**
 * Represents a point on the k-means grid.
 * 
 * Should be designed with easily changed dimension.
 *
 * Should have the ability to store an associated object (swimmer)
 */
public class GridPoint {
    /* Fields */
    private static final DIMENSIONS = 2;
    private float[] coordinates;
    
    /* Constructor */
    public GridPoint(float[] startPosition) {
        coordinates = new float[DIMENSIONS]();
        
        //TODO Update coordinates to starting point
    }
    
    /* Get current position of this point */
    public float[] getPosition() {
        return coordinates;
    }
    
    /* */
    public void setPosition(float[] newPosition) {
        //TODO
    }
}