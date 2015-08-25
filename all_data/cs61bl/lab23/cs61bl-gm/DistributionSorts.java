import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		for (int i: arr) {
			counts[i]++;
		}
		int[] starts = new int[10];
		starts[0] = 0;
		for (int i = 1; i < 10; i++) {
			starts[i] = starts[i - 1] + counts[i - 1];
		}
		for (int i = 0; i < 10; i++) {
			if (i == 9) {
				while (starts[i] != arr.length) {
					arr[starts[i]] = 9;
					starts[i]++;
				}
				break;
			}
			while (starts[i] < arr.length && starts[i] < starts[i + 1]) {
				arr[starts[i]] = i;
				starts[i]++;
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
		if (digit < 0 || start == end) {
			return;
		}
		int[] a = countingSortByDigitInBounds(arr, digit, start, end);
		for (int i = 0; i < 9; i++) {
			MSDRadixSortFromDigitInBounds(arr, digit - 1, a[i], a[i + 1]);
		}
		MSDRadixSortFromDigitInBounds(arr, digit - 1, a[a.length - 1], end -1);
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
		if (start == end) {
			return new int[] {start, end};
		}
		int tenDivider = 10;
		if (digit == 0) {
			tenDivider = 1;
		}
		while (digit > 1) {
			tenDivider = tenDivider * 10;
			digit--;
		}
		int[] counts = new int[10];
		for (int i = start; i < end; i++) {
			counts[(arr[i]/tenDivider) % 10]++;
		}
//		for (int i: counts) {
//			System.out.println(i);
//		}
//		System.out.println("#######");
		int[] starts = new int[10];
		starts[0] = start;
		for (int i = 1; i < 10; i++) {
			starts[i] = starts[i - 1] + counts[i - 1];
		}
		int[] rvStarts = new int[starts.length];
		for (int i = 0; i < starts.length; i++) {
			rvStarts[i] = starts[i];
		}
		int[] copy = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			copy[i] = arr[i];
		}
		for (int i = start; i < end; i++) {
			int index = (copy[i]/tenDivider)%10;
				arr[starts[index]] = copy[i];
				starts[index]++;
		} 
		return rvStarts;
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
