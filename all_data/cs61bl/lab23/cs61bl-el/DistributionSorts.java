import java.util.Arrays;

public class DistributionSorts {
	public static int level = 0;

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		int[] starts = new int[10];
		int[] sorted = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
			counts[arr[i]]++;
		}

		starts[0] = 0;
		for (int j = 1; j < starts.length; j++) {
			starts[j] = starts[j - 1] + counts[j - 1];
		}

		for (int i = 0; i < arr.length; i++) {
			int value = arr[i];
			sorted[starts[value]] = value;
			starts[value]++;
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

		if (start < 0 || end > arr.length) {
			return;
		}

		int[] starts = countingSortByDigitInBounds(arr, digit, start, end);
		int[] temp = starts.clone();
		int[] sorted = new int[arr.length];
		// System.out.println("START: " + start + "  END: " + end);
		for (int i = start; i < end; i++) {
			int value = (int) ((arr[i] / Math.pow(10, digit)) % 10);
			// System.out.println("ACTUAL VALUE :   " + arr[i]);
			// System.out.println("VALUE BEING COMPARED: " + value);
			sorted[starts[value]] = arr[i];
			starts[value]++;
		}

		int count = 0;
		for (int k = start; k < end; k++) {
			arr[k] = sorted[count];
			count++;
		}
		// System.out.println("\n\nlevel=" + level++);
		if (digit > 0) {
			digit--;
			// System.out.println("digit:[" + digit + "] start:[" + start
			// + "] end:[" + end + "]");
			for (int j = 0; j < 10; j++) {

				if (temp[j + 1] - temp[j] > 1) {
					// System.out.println("digit:[" + digit + "] start:["
					// + temp[j] + "] end:[" + temp[j + 1] + "] j:[" + j
					// + "]");
					MSDRadixSortFromDigitInBounds(arr, digit, start + temp[j],
							start + temp[j + 1]);
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
		int[] starts = new int[11];

		if (start < 0 || end > arr.length) {
			return starts;
		}

		for (int i = start; i < end; i++) {
			int num = (int) ((arr[i] / Math.pow(10, digit)) % 10);
			counts[num]++;
		}

		starts[0] = 0;

		for (int j = 1; j < starts.length; j++) {
			starts[j] = starts[j - 1] + counts[j - 1];
		}

		starts[10] = end;
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

		// int[] arr3 = new int[] { 4850, 8331, 7912, 8893, 8805, 9892 };
		int[] arr3 = new int[6];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));

	}

}
