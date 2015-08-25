import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int counts[] = new int[10];
		for (int i = 0; i < arr.length; i++) {
			counts[arr[i]]++;
		}
		
		int startpoint[] = new int[10];
		startpoint[0] = 0;
		for (int i = 1; i <= 9; i++) {
			startpoint[i] = counts[i - 1] + startpoint[i - 1];
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = startpoint[i]; j < startpoint[i + 1]; j++) {
				arr[j] = i;
			}
		}
		
		for (int i = startpoint[9]; i < arr.length; i++) {
			arr[i] = 9;
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
		if (start >= end) {
			return;
		}
		
		int[] temp = new int[arr.length];
		int[] counter = countingSortByDigitInBounds(arr, digit, start, end);
		int multiplier = 1;
		
		for (int i = 0; i < digit; i++) {
			multiplier *= 10;
		}

		for (int i = start; i < end; i++) {
			temp[counter[arr[i] / multiplier]] = arr[i] % multiplier;
			counter[arr[i] / multiplier]++;
		}

		if (digit != 0) {
			MSDRadixSortFromDigitInBounds(temp, digit - 1, 0, counter[0]);
			for (int i = 0; i < 9; i++) {
				MSDRadixSortFromDigitInBounds(temp, digit - 1, counter[i], counter[i + 1]);
			}
		}
		
		for (int i = 1; i < 10; i++) {
			for (int j = counter[i - 1]; j < counter[i]; j++) {
				temp[j] += multiplier * i;
			}
		}
		
		for (int i = start; i < end; i++) {
			arr[i] = temp[i];
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
		int[] array = new int[10];
		
		int multiplier = 1;
		for (int i = 0; i < digit; i++) {
			multiplier *= 10;
		}
		
		
		for (int i = start; i < end; i++) {
			if (arr[i] / multiplier != 9) {
				array[arr[i] / multiplier + 1]++;
			}
		}
		
		array[0] = start;
		
		for (int i = 0; i < 9; i++) {
			array[i + 1] = array[i] + array[i + 1];
		}
		
		return array;
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
