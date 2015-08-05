
/**
 * Represents a point on the k-means grid.
 * 
 * Should be designed with easily changed dimension.
 *
 * Can store an associated object (such as a swimmer)
 */
public class GridPoint {
    /* Fields */
    //private static final DIMENSIONS = 2;
    public final Object ASSOCIATED_OBJECT;
    private float[] coordinates;
    
    /* Constructor */
    public GridPoint(float[] startPosition, Object assoc_object) {
        coordinates = startPosition;
        ASSOCIATED_OBJECT = assoc_object;
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
}