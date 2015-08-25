import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		//		int[] counts = new int[10];
		//		for (int i = 0; i < arr.length; i++) {
		//			counts[arr[i]]++;
		//		}
		//		int[] starts = new int[10];
		//		int currentStart = 0;
		//		for (int i = 0; i < 10; i++) {
		//			starts[i] = currentStart;
		//			currentStart += counts[i];
		//		}
		//		int[] result = new int[arr.length];
		//		for (int i = 0; i < arr.length; i++) {
		//			result[starts[arr[i]]] = arr[i];
		//			starts[arr[i]]++;
		//		}
		//		arr = result;
		int[] counts = new int[10];
		for (int i = 0; i < arr.length; i++) {
			counts[arr[i]]++;
		}
		int counter = 0;
		int currentIndex = 0;
		while (counter < arr.length) {
			if (counts[currentIndex] > 0) {
				arr[counter] = currentIndex;
				counts[currentIndex]--;
				counter++;
			} else {
				currentIndex++;
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
		if (digit == -1 || start == end) {
			return;
		}
		int[] boundaries = countingSortByDigitInBounds(arr, digit, start, end);
		for (int i = 0; i < boundaries.length; i++) {
			if (i == boundaries.length - 1) {
				MSDRadixSortFromDigitInBounds(arr, digit - 1, boundaries[i], end);
			} else {
				MSDRadixSortFromDigitInBounds(arr, digit - 1, boundaries[i], boundaries[i+1]);
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
		//		int[] counts = new int[10];
		//		for (int i = start; i < end; i++) {
		//			counts[getDigit(arr[i], digit)]++;
		//		}
		//		int counter = start;
		//		int currentIndex = 0;
		//		while (counter < end) {
		//			if (counts[currentIndex] > 0) {
		//				arr[counter] = currentIndex;
		//				counts[currentIndex]--;
		//				counter++;
		//			} else {
		//				currentIndex++;
		//			}
		//		}
		int[] counts = new int[10];
		for (int i = start; i < end; i++) {
			counts[getDigit(arr[i], digit)]++;
		}
		int[] starts = new int[10];
		int[] boundaries = new int[10];
		int currentStart = start;
		for (int i = 0; i < 10; i++) {
			starts[i] = currentStart;
			boundaries[i] = currentStart;
			currentStart += counts[i];
		}
		int[] result = new int[arr.length];
		for (int i = start; i < end; i++) {
			result[i] = arr[i];
		}
		for (int i = start; i < end; i++) {
			result[starts[getDigit(arr[i], digit)]] = arr[i];
			starts[getDigit(arr[i], digit)]++;
		}
		for (int i = start; i < end; i++) {
			arr[i] = result[i];
		}
		return boundaries;
		
	}

	/**
	 * Returns digit d of x, where d == 0 is the rightmost digit, d == 1 is next-to-rightmost digit, etc.
	 */
	private static int getDigit(int x, int d) {
		int remainder = 0;
		for (int i = 0; i <= d; i++) {
			remainder = x % 10;
			x = x / 10;
		}
		return remainder;
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
