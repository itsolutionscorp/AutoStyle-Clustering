import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int l = arr.length;
		int[] a = new int[l];

		int max = arr[0];
		int min = arr[0];
		for (int i : arr) {
			if (i > max)
				max = i;
			if (i < min)
				min = i;
		}

		int k = max - min + 1; // to simplify the size of array c

		int[] c = new int[k]; // position number array
		for (int i : arr)
			c[i - min] += 1; // initialize the position number array

		for (int i = 1; i < c.length; i++)
			c[i] += c[i - 1]; // count the position number

		for (int i = 0; i < l; i++)
			a[--c[arr[i] - min]] = arr[i]; // --c cuz array starts from 0 not 1

		for (int i = 0; i < l; i++)
			arr[i] = a[i];
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

		int[] a = countingSortByDigitInBounds(arr, digit, start, end);
		for (int i = 0; i < end - start; i++)
			arr[i] = a[i];

		digit--;
		if (digit >= 0)
			MSDRadixSortFromDigitInBounds(arr, digit, start, end);

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
		int[] a = new int[end - start];
		int tens = 1; // compute 10^digit
		for (int i = 0; i < digit; i++)
			tens *= 10;

		for (int i = 0; i < end - start; i++)
			a[i] = arr[i];

		int max = arr[start];
		int min = arr[start];
		for (int i = 0; i < end - start; i++) {
			if (arr[i] > max)
				max = arr[i];
			if (arr[i] < min)
				min = arr[i];
		}
		max /= tens; // only digits
		min /= tens; // only digits

		int k = max - min + 1;

		int[] c = new int[k];
		for (int i = 0; i < end - start; i++)
			c[arr[i] / tens - min] += 1; // divided by 10^digit to only deal
											// with digits

		for (int i = 1; i < c.length; i++)
			c[i] += c[i - 1];

		for (int i = 0; i < end - start; i++)
			a[--c[arr[i] / tens - min]] = arr[i]; // divided by 10^digit to only
													// deal with digits

		return a;
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
