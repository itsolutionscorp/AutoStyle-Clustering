import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		int[] starts = new int[10];
		for (int i = 0, n = arr.length; i < n; i++) {
			counts[arr[i]]++;
		}
		starts[0] = counts[0];
		for (int j = 1; j < 10; j++) {
			starts[j] = counts[j] + starts[j - 1];
		}

		int p = 0;
		for (int k = 0; k < 10; k++) {
			for (int l = p; l < starts[k]; l++) {
				arr[p++] = k;
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
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit, int start, int end) {
		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		List<Integer>[] bucket = new ArrayList[10];
		for (int i = 0; i < bucket.length; i++) {
			bucket[i] = new ArrayList<Integer>();
		}

		boolean maxLength = false;
		int tmp = -1, placement = 1;
		while (!maxLength) {
			maxLength = true;
			for (Integer i : arr) {
				tmp = i / placement;
				bucket[tmp % 10].add(i);
				if (maxLength && tmp > 0) {
					maxLength = false;
				}
			}
			int a = 0;
			for (int b = 0; b < 10; b++) {
				for (Integer i : bucket[b]) {
					arr[a++] = i;
				}
				bucket[b].clear();
			}
			placement *= 10;
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
		// TODO your code here!
		List<Integer>[] bucket = new ArrayList[10];
		for (int i = 0; i < 10; i++) {
			bucket[i] = new ArrayList<Integer>();
		}
		for (int i = 0, n = arr.length; i < n; i++) {
			bucket[getDigit(arr[i], 10, digit)].add(arr[i]);
		}
		int p = 0;
		for (List<Integer> l : bucket) {
			for (int i = 0; i < l.size(); i++) {
				arr[p++] = l.get(i);
			}
		}

		return arr;
	}

	public static int getDigit(int number, int base, int n) {
		return (int) ((number / Math.pow(base, n - 1)) % base);
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
		countingSortByDigitInBounds(arr3,2,0,arr3.length);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));


	}
}
