import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		for (int num : arr) {
			counts[num] = counts[num] + 1;
		}
		int[] sorted = new int[arr.length];
		int[] starts = new int[counts.length];
		int index = 0;
		for (int num = 0; num < counts.length; num++) {
			starts[num] = index;
			index += counts[num];
		}
		int totalTimesPut = 0;
		for (int item = 0; item < counts.length - 1; item++) {
			while (starts[item + 1] > totalTimesPut) {
				sorted[totalTimesPut] = item;
				totalTimesPut++;
			}
		}
		while (totalTimesPut < arr.length) {
			sorted[totalTimesPut] = counts.length - 1;
			totalTimesPut++;
		}
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
		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		// helper method, given below.
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
		int floor = 1;
		int mod = 10;
		while (digit != 0) {
			floor *= 10;
			digit--;
		}

		for (int num = start; num < end; num++) {
			int digitInNum = arr[num];
			digitInNum = digitInNum / floor;
			digitInNum = digitInNum % mod;
			counts[digitInNum] = counts[digitInNum] + 1;
		}
		int[] sorted = new int[end - start];
		int[] starts = new int[counts.length];
		int index = start;
		for (int num = 0; num < counts.length; num++) {
			starts[num] = index;
			index += counts[num];
		}
		int totalTimesPut = start;
		for (int item = 0; item < counts.length - 1; item++) {
			while (starts[item + 1] > totalTimesPut) {
				sorted[totalTimesPut] = item;
				totalTimesPut++;
			}
		}
		while (totalTimesPut < arr.length) {
			sorted[totalTimesPut] = counts.length - 1;
			totalTimesPut++;
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sorted[i];
		}

		return null;
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

		// int[] arr2 = new int[3];
		// for (int i = 0; i < arr2.length; i++) {
		// arr2[i] = randomDigit();
		// }
		// System.out.println("Original array: " + Arrays.toString(arr2));
		// MSDRadixSort(arr2);
		// System.out.println("Should be sorted: " + Arrays.toString(arr2));
		//
		// int[] arr3 = new int[30];
		// for (int i = 0; i < arr3.length; i++) {
		// arr3[i] = randomInt();
		// }
		// System.out.println("Original array: " + Arrays.toString(arr3));
		// MSDRadixSort(arr3);
		// System.out.println("Should be sorted: " + Arrays.toString(arr3));
	}
}
