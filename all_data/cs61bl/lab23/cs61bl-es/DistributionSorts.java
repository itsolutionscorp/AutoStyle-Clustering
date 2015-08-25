import java.util.Arrays;
import java.util.Collections;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] counts = new int[10];
		for (int i : arr) {
			counts[i]++;
		}

		int[] starts = new int[10];
		for (int i = 1; i < 10; i++) {
			starts[i] = starts[i - 1] + counts[i - 1];
		}

		int[] sorted = new int[arr.length];
		for (int i : arr) {
			sorted[starts[i]] = i;
			starts[i]++;
		}

		// modify arr
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sorted[i];
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
		if (digit < 0 || start >= end - 1) {
			return;
		}
		int[] boundaries = countingSortByDigitInBounds(arr, digit, start, end);
		for (int b = 0; b < boundaries.length - 1; b++) {
			int recursiveStart = boundaries[b] + start;
			int recursiveEnd = boundaries[b + 1] + start;
			MSDRadixSortFromDigitInBounds(arr, digit - 1, recursiveStart, recursiveEnd);
		}
		MSDRadixSortFromDigitInBounds(arr, digit - 1, boundaries[9] + start, end);
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
		int powerOfTen = (int) Math.pow(10, digit);

		int[] counts = new int[10];
		for (int i = start; i < end; i++) {
			counts[(arr[i] / powerOfTen) % 10]++;
		}

		int[] starts = new int[10];
		int[] startsRtn = new int[10];
		for (int i = 1; i < 10; i++) {
			starts[i] = starts[i - 1] + counts[i - 1];
			startsRtn[i] = startsRtn[i - 1] + counts[i - 1];
		}

		int[] sorted = new int[arr.length];
		for (int i = start; i < end; i++) {
			sorted[starts[(arr[i] / powerOfTen) % 10]] = arr[i];
			starts[(arr[i] / powerOfTen) % 10]++;
		}

		// modify arr
		for (int i = start; i < end; i++) {
			arr[i] = sorted[i - start];
		}

		return startsRtn;
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
	
	private static boolean isSorted(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				return false;
			}
		}
		return true;
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
		System.out.println(isSorted(arr1) + "\n"); // should be true

		int[] arr2 = new int[3];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr2));
		MSDRadixSort(arr2);
		System.out.println("Should be sorted: " + Arrays.toString(arr2));
		System.out.println(isSorted(arr2) + "\n"); // should be true

		int[] arr3 = new int[30];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
		System.out.println(isSorted(arr3) + "\n"); // should be true
		
		// tests that the list will be properly sorted even if there aren't unique values
		int[] arr4 = new int[]{8104, 6, 3, 2433, 6, 1, 8104, 8103, 77, 2, 9, 0, 77, 3};
		System.out.println("Original array: " + Arrays.toString(arr4));
		MSDRadixSort(arr4);
		System.out.println("Should be sorted: " + Arrays.toString(arr4));
		System.out.println(isSorted(arr4)); // should be true
	}
}
