import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		int[] starts = new int[10];
		int[] sorted = new int[arr.length];
		for (int i : arr) {
			counts[i]++;			
		}
		for (int j = 1; j < 10; j++) {
			starts[j] = counts[j-1] + starts[j-1];
		}
		for (int k : arr) {
			sorted[starts[k]] = k;
			starts[k]++;
		}
		for (int l = 0; l < sorted.length; l++) {
			arr[l] = sorted[l];
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
		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		// helper method, given below.
		if (digit != -1) {
			int[] boundary = countingSortByDigitInBounds(arr, digit, start, end);
			for (int bucketStart = start, next = 0; next < boundary.length; bucketStart = boundary[next], next++) {
				if (bucketStart != boundary[next]) {
					MSDRadixSortFromDigitInBounds(arr, digit - 1, bucketStart, boundary[next]);
				}
			}
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
		// TODO your code here!
		int[] counts = new int[10];
		int[] starts = new int[10];
		int[] sorted = new int[arr.length];
		for (int i = start; i < end; i++) {
			int bucket = (arr[i] / (int) Math.pow(10, digit)) % 10;
			counts[bucket]++;
		}
		starts[0] = start;
		for (int j = 1; j < 10; j++) {
			starts[j] = counts[j-1] + starts[j-1];
		}
		for (int k = start; k < end; k++) {
			int bucket = (arr[k] / (int) Math.pow(10, digit)) % 10;
			sorted[starts[bucket]] = arr[k];
			starts[bucket]++;
		}
		for (int l = start; l < end; l++) {
			arr[l] = sorted[l];
		}
		return starts;
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
		
		int[] mine = {23, 51, 213, 1516, 2123, 121, 221, 3, 0, 342, 946, 145, 12, 800, 51321, 12, 3243, 527, 23, 475, 214, 753};
		System.out.println("Original array: " + Arrays.toString(mine));
		MSDRadixSort(mine);
		System.out.println("Should be sorted: " + Arrays.toString(mine));
	}
}
