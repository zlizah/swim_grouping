
import java.util.Random;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Main class that does the scheduling
 */
public class GroupScheduler {
    /* Fields */
    private DataPoint[] data_points;
    private Cluster[] clusters;
    
    /* Constructor */
    public GroupScheduler(Swimmer[] swimmers) {
        /* Initialize lists that hold the data points and clusters */
        data_points = new DataPoint[swimmers.length];
        
        /* Fill the list of data points */
        for (int i = 0; i < swimmers.length; i++) { 
            Swimmer s = swimmers[i];
            float[] coords = s.generateGirdCoordinates();
            data_points[i] = new DataPoint(coords, s);
        }
    }
    
    /**
     * Main
     */
    public static void main(String[] args) {
        /* Group some swimmers */
        GroupScheduler gs = new GroupScheduler(
            SwimmerGenerator.generateSwimmers(100)
        );
        Cluster[] groups = gs.scheduleGroups(10);
        
        /* Print results */
        System.out.println("********* RESULTS ***********");
        for(int i = 0; i < groups.length; i++) {
            Cluster c = groups[i];
            ArrayList<DataPoint> dps = c.getDataPoints();
            System.out.println();
            System.out.println("Group " + i + ", size: " + dps.size());
            /* Print some info about all swimmers in this group */
            for(int j = 0; j < dps.size(); j++) {
                Swimmer s = dps.get(j).getSwimmer();
                Calendar bday = s.BIRTHDATE;
                int year = bday.get(Calendar.YEAR);
                int month = bday.get(Calendar.MONTH);
                int day = bday.get(Calendar.DAY_OF_MONTH);
                String bday_str = year + "-" + month + "-" + day;
                System.out.println(s.FIRST_NAMES + ", born: " + bday_str + 
                    ", recommended for: " + s.RECOMENDATION);
            }
        }
    }
        
    /* Main function used to do the scheduling using the K-means algorithm */
    public Cluster[] scheduleGroups(int groupAmount) {
        /* Clear old clusters and generate new */
        clusters = new Cluster[groupAmount];
        initializeClusters(groupAmount);
        
        /* Initial cluster distances */
        int oldClusterDistance = Integer.MAX_VALUE;
        int currentClusterDistance = totalClusterDistance();
        
        /* Iterate k-means until the total distance remains unchanged */
        while(oldClusterDistance > currentClusterDistance) {
            /* Clear all assigned data points to the clusters */
            for (Cluster c : clusters) {
                c.clearDataPoints();
            }
            
            /* Store all data points in its closest cluster */
            int iterations = 0;
            for (DataPoint data_point : data_points) {
                int closest_index = nearestCluster(data_point, clusters);
                clusters[closest_index].addPoint(data_point);
                iterations ++;
            }
            
            /* Move each group to the mean of all its points */
            for (Cluster c : clusters) {
                ArrayList<DataPoint> dps = c.getDataPoints();
                if (dps.size() != 0) {
                    float[] meanPos = meanGridPosition(dps);
                    c.setPosition(meanPos);
                }
            }
            
            /* Update the total cluster distances */
            oldClusterDistance = currentClusterDistance;
            currentClusterDistance = totalClusterDistance();
        }
        return clusters;
    }
    
    /* Calculates the total distance between all data point/cluster pair */
    private int totalClusterDistance() {
        int total = 0;
        for (Cluster c : clusters) {
            ArrayList<DataPoint> dps = c.getDataPoints();
            for(int i = 0; i < dps.size(); i++) {
                DataPoint point = dps.get(i);
                total += distance(point, c);
            }
        }
        return total;
    }
    
    /* Initialize the groups, current done randomly */
    private void initializeClusters(int amount) {
        Random rng = new Random();
        for (int i = 0; i < amount; i++) {
            /* Place each cluster at a random data point */
            int index = rng.nextInt(data_points.length);
            float[] coords = data_points[i].getPosition();
            clusters[i] = new Cluster(coords);
        }
    }
    
    /* Calculates the mean position of the given data points */
    private float[] meanGridPosition(ArrayList<DataPoint> dps) {
        int dimension = dps.get(0).getPosition().length;
        float[] sums = new float[dimension];
        int totalPoints = 0;
        
        /* Summarize each dimension (x, y, z, ...) into an array */
        for (int i = 0; i < dps.size(); i++) {
            DataPoint point = dps.get(i);
            float[] coords = point.getPosition();
            for (int j = 0; j < coords.length; j++) {
                sums[j] += coords[j];
            }
            totalPoints += 1;
        }
        
        /* Then divide each sum with the amount of data points to get the mean */
        for (int i = 0; i < dimension; i++) {
            sums[i] /= totalPoints;
        }      
        return sums;
    }
    
    /**
     * Chose the index of the cluster closest to the given data point in the 
     * given array 
     */
    private int nearestCluster(DataPoint data_point, Cluster[] clusters) {
        float min = -1;
        int nearest = -1;
        int index = 0;
        for (Cluster cluster : clusters) {
            float dis = distance(data_point, cluster);
            if (dis < min || min == -1) {
                nearest = index;
                min = dis;
            }
            index += 1;
        }
        return nearest;
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