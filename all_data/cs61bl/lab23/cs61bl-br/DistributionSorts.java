import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int start = 0;
		int end = arr.length;
		int digit = 0;
		int[] count = new int[10];
		int[] solved = new int[end - start];
		for (int i = start; i < end; i++) {
			count[(int) ((arr[i] / Math.pow(10, digit)) % 10)] += 1;
		}
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}
		for (int i = start; i < end; i++) {
			solved[count[(int) ((arr[i] / Math.pow(10, digit)) % 10)] - 1] = arr[i];
			count[(int) ((arr[i] / Math.pow(10, digit)) % 10)] -= 1;
		}
		for (int i = start, j = 0; i < end; i++, j++) {
			arr[i] = solved[j];
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
		if (digit == -1) {
			return;
		}
		if (end - start <= 1) {
			return;
		}
		int[] boundaries = countingSortByDigitInBounds(arr, digit, start, end);
		// for (int i = boundaries.length - 2; i >= 0; i--) {
		// MSDRadixSortFromDigitInBounds(arr, digit - 1, boundaries[i + 1],
		// boundaries[i]);
		// }
		int prev = start;
		for (int i = 0; i < boundaries.length; i++) {
			MSDRadixSortFromDigitInBounds(arr, digit - 1, prev,
					boundaries[i]);
			prev = boundaries[i];
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
		int[] count = new int[10], positions = new int[10];
		int[] solved = new int[end - start];
		int total = 0;
		for (int i = start; i < end; i++) {
			count[(int) ((arr[i] / Math.pow(10, digit)) % 10)] += 1;
		}
		for (int i = 0; i < 10; i++) {
			positions[i] = total + start;
			total += count[i];
		}
		for (int i = start; i < end; i++) {
			int item = arr[i];
			int position = positions[getDigit(item, digit)];
			solved[position - start] = item;
			positions[getDigit(item, digit)]++;
		}
		for (int i = start; i < end; i++) {
			arr[i] = solved[i - start];
		}
		return positions;
	}
	
//	private static int[] countingSortByDigitInBounds(int[] arr, int digit,
//			int start, int end) {
//
//		int[] counts = new int[10];
//		for (int i = start; i < end; i++) {
//			counts[getDigit(arr[i], digit)]++;
//		}
//
//		int[] positions = new int[10];
//		int totalSoFar = 0;
//		for (int i = 0; i < 10; i++) {
//			positions[i] = totalSoFar + start;
//			totalSoFar += counts[i];
//		}
//
//		int[] copy = new int[end - start];
//		for (int i = start; i < end; i++) {
//			int item = arr[i];
//			int position = positions[getDigit(item, digit)];
//			copy[position - start] = item;
//			positions[getDigit(item, digit)]++;
//		}
//		for (int i = start; i < end; i++) {
//			arr[i] = copy[i - start];
//		}
//		return positions;
//	}
//	
	public static int getDigit(int num, int digit) {
		return (int) (num / (Math.pow(10, digit))) % 10;
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
		int[] arr4 = new int[11];
		// arr4[0] = 1200;
		// arr4[1] = 1243;
		// arr4[2] = 2563;
		// arr4[3] = 1203;
		// arr4[4] = 2553;
		// arr4[5] = 1223;
		// arr4[6] = 2543;
		// arr4[7] = 1253;
		// arr4[8] = 2333;
		// arr4[9] = 2323;
		// arr4[10] = 2313;
		arr4[0] = 110;
		arr4[1] = 343;
		arr4[2] = 423;
		arr4[3] = 313;
		arr4[4] = 253;
		arr4[5] = 53;
		arr4[6] = 324;
		arr4[7] = 15;
		arr4[8] = 633;
		arr4[9] = 422;
		arr4[10] = 82;
		System.out.println("Original array: " + Arrays.toString(arr4));
		MSDRadixSort(arr4);
		System.out.println("Should be sorted: " + Arrays.toString(arr4));
	}
}
