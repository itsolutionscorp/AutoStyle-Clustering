import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		for (int a : arr) {
			counts[a] = counts[a] + 1;
		}
		int[] starts = new int[10];
		for (int i = 1; i < 10; i++) {
			starts[i] = starts[i - 1] + counts[i - 1];
		}
		int now = 0;
		for (int j = 0; j < arr.length; j++) {
			if (now == 9) {
				arr[j] = 9;
				continue;
			}
			if (starts[now + 1] <= j) {
				now++;
			}
			arr[j] = now;
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
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit, int start, int end) {
		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		// helper method, given below.
		if (digit < 0) {
			return;
		}
		int[] bound = countingSortByDigitInBounds(arr, digit, start, end);
		if (bound == null)
			return;
		int curr = 0;
		while (curr < bound.length - 1) {
			MSDRadixSortFromDigitInBounds(arr, digit - 1, bound[curr], bound[curr + 1]);
			curr++;
		}
		MSDRadixSortFromDigitInBounds(arr, digit - 1, bound[curr], end);
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
		// TODO your code here!
		if (start == end || start == end - 1) {
			return null;
		}
		int[] counts = new int[10];
		HashMap<Integer, ArrayList<Integer>> table = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = start; i < end; i++) {
			int find = arr[i];
			for (int j = 0; j < digit; j++) {
				find = find / 10;
			}
			counts[find % 10] = counts[find % 10] + 1;
			if (!table.containsKey(find % 10)) {
				table.put(find % 10, new ArrayList<Integer>());
				table.get(find % 10).add(arr[i]);
			} else
				table.get(find % 10).add(arr[i]);
		}
		int[] boundary = new int[10];
		boundary[0] = start;
		for (int i = 1; i < 10; i++) {
			boundary[i] = boundary[i - 1] + counts[i - 1];
		}
		int num = 0;
		for (int i = start; i < end; i++) {
			if (table.isEmpty()) {
				break;
			}
			while (num < 9 && boundary[num + 1] <= i) {
				num++;
			}
			if (num == 9) {
				if (!table.containsKey(9)) {
					break;
				}
			}
			arr[i] = table.get(num).get(0);
			table.get(num).remove(0);
		}
		return boundary;
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
