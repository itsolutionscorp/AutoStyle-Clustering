import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		// Create a new array of integers from 0 to 9
		// copy how many of each integer there is
		// copy them BACK INTO THE ORIGINAL ARRAY!

		int[] counts = new int[10];
		for (int value : arr) {
			counts[value] += 1;
		}

		int countsIndex = 0;
		int arrIndex = 0;
		while (arrIndex < arr.length) {
			if (counts[countsIndex] == 0) {
				countsIndex++;
				continue;
			} else {
				arr[arrIndex] = countsIndex;
				counts[countsIndex] -= 1;
				arrIndex++;
			}
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
		if (start == end || (start + 1) == end) {
			// System.out.println("Reached base case.");
			return;
		}
		if (digit == -1) {
			return;
		}

		// Pointers is an array of length 10 that stores the indicies of each
		// digit's pointer
		int[] counts = countingSortByDigitInBounds(arr, digit, start, end);
		// MSDRadixSortFromDigitInBounds(arr, digit - 1, start, start +
		// counts[1]);
		// System.out.println(" Recursion: `Arr " + Arrays.toString(arr)
		// + " digit " + digit + " start " + start + " end " + end);
		// System.out.println("Recursion counts " + Arrays.toString(counts));
		MSDRadixSortFromDigitInBounds(arr, digit - 1, start, start + counts[0]
				- 1);
		start += counts[0];
		for (int i = 1; i < 10; i++) {
			if (counts[i] - 1 >= 0) {
				MSDRadixSortFromDigitInBounds(arr, digit - 1, start
						+ counts[i - 1], start + counts[i] - 1);
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

		int[] arr2 = Arrays.copyOf(arr, arr.length);
		int arrIndex = start;
		int[] counts = new int[10];
		while (arrIndex < end) { // can't do a for each because must stay in
									// given range
			int currentValue = (int) (arr[arrIndex] / (Math.pow(10, digit))) % 10;
			counts[currentValue] += 1;
			arrIndex++;
		}
		// System.out.println("Arr " + Arrays.toString(arr) + " digit " + digit
		// + " start " + start + " end " + end);
		// System.out.println("Counts is " + Arrays.toString(counts));
		for (int i = 1; i < 10; i++) {
			counts[i] += counts[i - 1];
		}
		// System.out.println("Counts is " + Arrays.toString(counts));
		for (int i = start; i < end; i++) {
			int item = arr2[i];
			int currentValue = (int) (item / (Math.pow(10, digit))) % 10;
			arr[start + counts[currentValue] - 1] = item;
			counts[currentValue]--;
		}
		return counts;
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

		int[] arr4 = { 5, 15, 17, 6, 20, 38, 4, 347, 346 };

		System.out.println("Original array: " + Arrays.toString(arr4));
		MSDRadixSort(arr4);
		System.out.println("Should be sorted: " + Arrays.toString(arr4));

		int[] arr3 = new int[30];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
	}
}
