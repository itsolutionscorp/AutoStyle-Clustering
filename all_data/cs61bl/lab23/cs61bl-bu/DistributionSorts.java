import java.util.ArrayList;
import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] count = new int[10];
		for (int i : arr) {
			count[i] += 1;
		}
		int[] starting_positions = new int[10];
		int[] copy = new int[arr.length];
		System.arraycopy(arr, 0, copy, 0, arr.length);
		for (int i=0; i<10; i++) {
			if (i == 0) {
				starting_positions[i] = 0;
			} else {
				starting_positions[i] = starting_positions[i-1] + count[i-1];
			}
		}
		for (int i : copy) {
			arr[starting_positions[i]] = i;
			starting_positions[i] += 1;
		}
	}

	/**
	 * Sorts the given array using MSD radix sort. 
	 */
	public static void MSDRadixSort(int[] arr) {
		int maxDigit = mostDigitsIn(arr) - 1;
		// System.out.println("MAXDIGIT: " + maxDigit);
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
		int[] boundaries = countingSortByDigitInBounds(arr, digit, start, end);
		if (start == end || digit < 0) {
			return;
		}
		for (int i = 0; i < boundaries.length; i++) {
			if (i < boundaries.length-1) {
				MSDRadixSortFromDigitInBounds(arr, digit-1, boundaries[i], boundaries[i+1]);
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
	private static int[] countingSortByDigitInBounds(int[] arr, int digit, int start, int end) {

		// make a copy of arr 
		int[] copy = new int[arr.length];
		System.arraycopy(arr, 0, copy, 0, arr.length);

		// filter arr to intrested digits by mutating copy 
		for (int k = start; k < end; k++) {
			copy[k] = (int) (copy[k]/Math.pow(10, digit))%10;
		}

		// sort the interested digits 
		int[] short_copy = Arrays.copyOfRange(copy, start, end);
		int[] positions = startPositions(short_copy);
		// System.out.println(Arrays.toString(positions));
		countingSort(short_copy);

		int[] intervals = new int[positions.length];
		// System.out.println(Arrays.toString(intervals));
		System.arraycopy(positions, 0, intervals, 0, positions.length);

		int[] ref = new int[arr.length];
		System.arraycopy(arr, 0, ref, 0, arr.length);
		for (int i = start; i < end; i++) {
			int curr = (int) (ref[i]/Math.pow(10, digit))%10;
			arr[start + positions[curr]] = ref[i];
			positions[curr] += 1;
		}

		return intervals;


	}

	public static int[] startPositions(int[] arr) {
		// TODO your code here!
		int[] count = new int[10];
		for (int i : arr) {
			count[i] += 1;
		}
		int[] starting_positions = new int[10];
		for (int i=0; i<10; i++) {
			if (i == 0) {
				starting_positions[i] = 0;
			} else {
				starting_positions[i] = starting_positions[i-1] + count[i-1];
			}
		}
		return starting_positions;
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
	
	public static void printArray(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	/**
	 * Runs some very basic tests of counting sort and radix sort.
	 */
	public static void main(String[] args) {
//		int[] arr1 = new int[20];
//		for (int i = 0; i < arr1.length; i++) {
//			arr1[i] = randomDigit();
//		}
//		System.out.println("Original array: " + Arrays.toString(arr1));
//		countingSort(arr1);
//		if (arr1 != null) {
//			System.out.println("Should be sorted: " + Arrays.toString(arr1));
//		}
		System.out.println("CASE 1");
		int[] arr2 = new int[3];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr2));
		MSDRadixSort(arr2);
		System.out.println("Should be sorted: " + Arrays.toString(arr2));
		System.out.println();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println();
		System.out.println("CASE 2");
		int[] arr3 = new int[30];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
	}
}
