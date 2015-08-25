import java.util.Arrays;

public class DistributionSorts {

	private static int step = 0;
	
	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] counts = new int[10];
		int[] sortedArr = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			counts[arr[i]]++;
		}
		for (int j = 1; j < counts.length; j++) {
			counts[j] += counts[j - 1];
		}
		for (int k = 0; k < arr.length; k++) {
			counts[arr[k]]--;
			sortedArr[counts[arr[k]]] = arr[k];
		}
		for (int p = 0; p < arr.length; p++) {
			arr[p] = sortedArr[p];
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
		int newStart;
		int newEnd;
		if (digit == 0) {
			countingSortByDigitInBounds(arr, digit, start, end);
			return;
		} else {
			int[] counts = countingSortByDigitInBounds(arr, digit, start, end);
			for (int i = 0; i < counts.length; i++) {
				if (i == counts.length - 1) {
					if (counts[i] >= arr.length) {
						return;
					}
//					System.out.println("digit: " + digit);
//					System.out.println("start: " + start);
//					System.out.println("number here: " + ((int) (arr[counts[i]] / Math.pow(10, digit) % 10)));
//					System.out.println("Reached end...");
//					System.out.println("9 here: " + ((int) (arr[counts[i]] / Math.pow(10, digit) % 10) == 9));
//					System.out.println();
					if ((int) (arr[counts[i]] / Math.pow(10, digit) % 10) == 9) {
						newStart = counts[i] + start;
						newEnd = end;
//						System.out.println("new start: " + newStart);
//						System.out.println("new end: " + newEnd);
//						System.out.println();
					} else {
						return;
					}
				} else {
					newStart = counts[i] + start;
					newEnd = counts[i + 1] + start;
				}
//				System.out.println("current counts array: " + Arrays.toString(counts));
//				System.out.println("start: " + newStart);
//				System.out.println("end: " + newEnd);
				if (newEnd > newStart) {
//					System.out.println("what we're passing in:");
//					System.out.println("arr: " + Arrays.toString(arr));
//					System.out.println("digit: " + (digit - 1));
//					System.out.println("start: " + newStart);
//					System.out.println("end: " + newEnd);
//					System.out.println();
					MSDRadixSortFromDigitInBounds(arr, digit - 1, newStart, newEnd);
				} 
//				else {
//					System.out.println("start and end are equal, keep going");
//					System.out.println();
//				}
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
		int[] sortedArr = new int[arr.length];
		for (int digitToCount = start; digitToCount < end; digitToCount++) {
			int toIncrement = (int) (arr[digitToCount] / Math.pow(10, digit) % 10);
			counts[toIncrement]++;
		}
		for (int countsPos = 1; countsPos < counts.length; countsPos++) {
			counts[countsPos] += counts[countsPos - 1];
		}
		for (int sortPos = start; sortPos < end; sortPos++) {
			int toDecrement = (int) ((arr[sortPos] / Math.pow(10, digit)) % 10);
			counts[toDecrement]--;
			int countsValue = counts[toDecrement];
			sortedArr[countsValue + start] = arr[sortPos];
		}
		for (int updatePos = start; updatePos < end; updatePos++) {
			arr[updatePos] = sortedArr[updatePos];
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

		int[] arr2 = new int[10];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr2));
		MSDRadixSort(arr2);
		System.out.println("Should be sorted: " + Arrays.toString(arr2));

		int[] arr3 = new int[10];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
	}
}
