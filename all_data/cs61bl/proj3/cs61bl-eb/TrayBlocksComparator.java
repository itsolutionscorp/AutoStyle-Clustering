import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of the Comparator interface.
 * Compares two trays represented by blocks based on their 
 * buckets except the last one due to efficiency concern.
 */

public class TrayBlocksComparator implements Comparator<ArrayList<ArrayList<int[]>>> {

	@Override
	public int compare(ArrayList<ArrayList<int[]>> t1, ArrayList<ArrayList<int[]>> t2) {
		for (int i = 0; i < t1.size() - 1; i++) {
			// do NOT need to compare the last bucket
			// because it all the previous buckets are identical,
			// they must be the same tray!
			// suppose the last bucket contains 9999 1-by-1 blocks,
			// this algorithm will save a lot of time

			// compare bucket i
			ArrayList<int[]> a1 = t1.get(i);
			ArrayList<int[]> a2 = t2.get(i);
			for (int j = 0; j < a1.size(); j++) {
				int result = compareTwoBlocks(a1.get(j), a2.get(j));
				if (result != 0) {
					return result;
				}
			}
		}
		// has passed all the previous checks, which means that the given trays are identical
		return 0;
	}

	private int compareTwoBlocks(int[] arr1, int[] arr2) {
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
