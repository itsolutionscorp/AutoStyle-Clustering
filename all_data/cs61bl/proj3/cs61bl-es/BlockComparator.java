import java.util.*;

/**
 * An implementation of the Comparator interface.
 * Compares two blocks based on their coordinates.
 */

public class BlockComparator implements Comparator<int[]>{
	
	@Override
	public int compare(int[] arr1, int[] arr2) {
		if (arr1[0] < arr2[0]) {
			return -1;
		} else if (arr1[0] > arr2[0]) {
			return 1;
		} else if (arr1[1] < arr2[1]) {
			return -1;
		} else if (arr1[1] > arr2[1]) {
			return 1;
		} else if (arr1.length == 2) {
			// empty squares have only 2 numbers
			return 0; 
		} else if (arr1[2] < arr2[2]) {
			return -1;
		} else if (arr1[2] > arr2[2]) {
			return 1;
		} else if (arr1[3] < arr2[3]) {
			return -1;
		} else if (arr1[3] > arr2[3]) {
			return 1;
		} else {
			return 0;
		}
	}
}
