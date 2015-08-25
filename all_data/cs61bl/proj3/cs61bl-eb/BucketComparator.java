import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of the Comparator interface.
 * Compares two buckets based on their sizes.
 */

public class BucketComparator implements Comparator<ArrayList<int[]>>{
	
	@Override
	public int compare(ArrayList<int[]> arr1, ArrayList<int[]> arr2) {
		return arr1.size() - arr2.size();
	}
}
