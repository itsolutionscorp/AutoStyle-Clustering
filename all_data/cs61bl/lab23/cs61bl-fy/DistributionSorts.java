import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] numCounts = new int[10];
		for (int i : arr) {
			numCounts[i] += 1;
		}
		int[] starts = new int[10];
		for (int i = 0; i < 10; i++) {
			if (i == 0) {
				starts[0] = 0;
			} else {
				starts[i] = starts[i - 1] + numCounts[i - 1];
			}
		}
		for (int i : arr.clone()) {
			arr[starts[i]] = i;
			starts[i]++;
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
		if (start == end) {
			return;
		}
		if (digit == 0) {
			countingSortByDigitInBounds(arr, digit, start, end);
		} else {
			int[] boundaries = countingSortByDigitInBounds(arr, digit, start,
					end);
			for (int i = 0; i < boundaries.length - 1; i++) {
				MSDRadixSortFromDigitInBounds(arr, digit - 1, boundaries[i],
						boundaries[i + 1]);
			}
			MSDRadixSortFromDigitInBounds(arr, digit - 1, boundaries[boundaries.length - 1], end);
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

		int[] numCounts = new int[10];
		for (int i = start; i < end; i++) {
			numCounts[arr[i] / (int) (Math.pow(10, digit)) % 10]++;
		}
		int[] starts = new int[10];
		int index = start;
		for (int i = 0; i < 10; i++) {
			starts[i] = index;
			index += numCounts[i];
		}
		int[] rtn = starts.clone();
		int[] clone = arr.clone();
		for (int i = start; i < end; i++) {
			arr[starts[clone[i] / (int) (Math.pow(10, digit)) % 10]] = clone[i];
			starts[clone[i] / (int) (Math.pow(10, digit)) % 10]++;
		}
		return rtn;

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
