
/**
 * Represents a point on the k-means grid.
 * 
 * Should be designed with easily changed dimension.
 */
public interface GridPoint {
    /* Get current position of this point */
    public float[] getPosition();
    
    /* Set the current grid position of this GridPoint */
    public void setPosition(float[] newPosition);
}