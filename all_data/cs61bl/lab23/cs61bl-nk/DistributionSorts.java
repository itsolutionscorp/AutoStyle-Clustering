import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		for (int i = 0; i < arr.length; i++) {
			counts[arr[i]]++;
		} int[] sorted = new int[arr.length];
		int[] starts = new int[10];
		starts[0] = 0;
		for (int i = 1; i < starts.length; i++) {
			starts[i] = starts[i - 1] + counts[i - 1];
		} for (int i = 0; i < 10; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[j] == i) {
					sorted[starts[i]] = i;
					starts[i]++;
				}
			}
		} for (int j = 0; j < arr.length; j++) {
			arr[j] = sorted[j];
		}
	}

	/**
	 * Sorts the given array using MSD radix sort. 
	 */
	public static void MSDRadixSort(int[] arr) {
		int maxDigit = mostDigitsIn(arr) - 1;
		MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length);
	}

	/**
	 * Radix sorts the input array only between the indices start and end. Only
	 * considers digits from the input digit on down. This method is recursive.
	 */
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit,
			int start, int end) {
		if (digit<=0) {
			countingSortByDigitInBounds(arr, digit, start, end);
			return;
		}
		
		int[] boundaries = countingSortByDigitInBounds(arr, digit, start, end);
		for (int i = 0; i < boundaries.length - 1; i++) {
			int newstart = boundaries[i];
		    int newend = boundaries[i+1];
		    countingSortByDigitInBounds(arr, digit-1, newstart, newend);
		}
	}
	
	/**
	 * A helper method for radix sort. Modifies arr to be sorted according to
	 * digit. Only sorts the portion of the arr between the indices start
	 * (inclusive) and end (exclusive).
	 * 
	 * Does NOT return the sorted array. Returns an array containing the
	 * boundary of each same-digit bucket in the array. This will be useful for
	 * radix sort.
	 */
	private static int[] countingSortByDigitInBounds(int[] arr, int digit,
			int start, int end) {
		// TODO your code here
	    int[] rtn = new int[end-start];
	    Queue<Integer> newarray = new LinkedList<Integer>();
	    Queue<Integer> count = new LinkedList<Integer>();
	    for (int i = 0; i < 10; i++) {
	    	for (int j = start; j < end; j++) {
	    		int d = (int) Math.floor(arr[j] / Math.pow(10, digit));
	    		int m = (int) ( d % 10);
	    		if (m == i) {
	    			newarray.offer(arr[j]);
	    			count.offer(m);
	    		}
	    	}
	    }
	    int k = 0;
	    while (!newarray.isEmpty()) {
	    	rtn[k] = newarray.poll();
	    	k++;
	    }
	    for (int x = start, y = 0; x < end; x++, y++) {
	    	arr[x] = rtn[y];
	    }
	    Queue<Integer> temp = new LinkedList<Integer>();
	    int p = 0;
	    temp.offer(p);
	    int starting = count.poll();
	    while (!count.isEmpty()) {
	    	if (starting != count.peek()) {
	    		starting = count.poll();
	    		p++;
	    		temp.offer(p);
	    		continue;
	    	}
	    	count.poll();
	    	p++;
	    }
	    int[] returned = new int[temp.size()+1];
	    for (int l = 0; l < returned.length-1; l++) {
	    	returned[l] = temp.poll();
	    } 
	    returned[returned.length-1] = arr.length;
	    return returned;
	}

	/**
	 * Returns the highest number of digits that any integer in arr happens to
	 * have.
	 */
	private static int mostDigitsIn(int[] arr) {
		int maxDigitsSoFar = 0;
		for (int num : arr) {
			int numDigits = (int) (Math.log10(num) + 1);
			if (numDigits > maxDigitsSoFar) {
				maxDigitsSoFar = numDigits;
			}
		}
		return maxDigitsSoFar;
	}

	/**
	 * Returns a random integer between 0 and 9999.
	 */
	private static int randomInt() {
		return (int) (10000 * Math.random());
	}

	/**
	 * Returns a random integer between 0 and 9.
	 */
	private static int randomDigit() {
		return (int) (10 * Math.random());
	}

	/**
	 * Runs some very basic tests of counting sort and radix sort.
	 */
	public static void main(String[] args) {
		int[] arr1 = new int[20];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr1));
		countingSort(arr1);
		if (arr1 != null) {
			System.out.println("Should be sorted: " + Arrays.toString(arr1));
		}

		int[] arr2 = new int[3];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr2));
		MSDRadixSort(arr2);
		System.out.println("Should be sorted: " + Arrays.toString(arr2));

		int[] arr3 = new int[30];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
	}
}
