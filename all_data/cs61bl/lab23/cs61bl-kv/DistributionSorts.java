import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		for (int k: arr) {
			counts[k] ++;
		}
		int[] starts = new int[10];
		starts[0] = 0;
		for (int k = 1; k < 10; k++) {
			starts[k] = starts[k-1] + counts[k-1];
		}
		int[] added = new int[10];
		int[] sorted = new int[arr.length];
		for (int m : arr) {
			sorted[starts[m] + added[m]] = m;
			added[m] ++;
		}
		for (int k = 0; k < arr.length; k++) {
			arr[k] = sorted[k];
		}
	}

	/**
	 * Sorts the given array using MSD radix sort. 
	 */
	public static void MSDRadixSort(int[] arr) {
		int maxDigit = mostDigitsIn(arr) - 1;
//		System.out.println(maxDigit);
		MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length);
	}

	/**
	 * Radix sorts the input array only between the indices start and end. Only
	 * considers digits from the input digit on down. This method is recursive.
	 */
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit, int start, int end) {
		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		// helper method, given below.
		int[] sortedByDigits = countingSortByDigitInBounds(arr, digit, start, end);
		digit--;
		if (digit < 0) {
			return;
		}
		for (int k = 0; k < 9; k++) {
			if (sortedByDigits[k] < sortedByDigits[k + 1]) {
				MSDRadixSortFromDigitInBounds(arr, digit, sortedByDigits[k], sortedByDigits[k + 1]);
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
		int digitFinder = (int) Math.pow(10, (double) digit);
		for (int k = start; k < end; k++) {
			int m = arr[k];
			m = (m/digitFinder)%10;
			counts[m] ++;
		}
		int[] starts = new int[10];
		starts[0] = start;
		for (int k = 1; k < 10; k++) {
			starts[k] = starts[k-1] + counts[k-1];
		}
		int[] sortedByDigit = new int[arr.length];
		int[] added = new int[10];
		for (int m = start; m < end; m++) {
			int n = arr[m];
			int k = (n/digitFinder)%10;
			sortedByDigit[starts[k]+added[k]] = n;
			added[k]++;
		}
		for (int k = start; k < end; k++) {
			arr[k] = sortedByDigit[k];
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
	}
}
