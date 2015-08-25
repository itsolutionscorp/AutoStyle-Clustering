import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] counts = new int[10];
		int[] starts = new int[10];
		for (int i: arr) {
			counts[i] += 1;
		}
		int k = 0;
		for (int j = 0; j < counts.length; j ++) {
			while (starts[j] < counts[j]) {
				arr[k] = j;
				starts[j] += 1;
				k ++;
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
		int[] bound = countingSortByDigitInBounds(arr, digit, start, end);
		if (digit == 0) {
			return;
		}
		for (int i = 0; i <= 9; i++) {
			if (i == 9) {
				MSDRadixSortFromDigitInBounds(arr, digit-1, bound[i], end);
			} else if (bound[i+1] == bound[i]) {
				 int temp = i;
					while (bound[temp] == bound[i] && temp < bound.length - 1) {
						temp ++;
					}
				if (bound[temp] == 0) {
					MSDRadixSortFromDigitInBounds(arr, digit-1, bound[i], end);
				} else {
					MSDRadixSortFromDigitInBounds(arr, digit-1, bound[i], bound[temp]);
				}
			} else {
				MSDRadixSortFromDigitInBounds(arr, digit-1, bound[i], bound[i + 1]);
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
		int[] counts = new int[10];
		int[] starts = new int[10];
		int[] help = new int[10];
		for (int i = start; i < end; i ++) {
			counts[(arr[i]/((int) Math.pow(10, digit))) % 10] += 1;
		}
		int j = start;
		for (int i = 0; i < 10; i ++) {
			starts[i] = j;
			help[i] = starts[i];
			j += counts[i];
		}
		int[] tempArr = new int[arr.length];
		for (int i = start; i < end; i ++) {
			int k = (arr[i]/((int) Math.pow(10, digit))) % 10;
			tempArr[help[k]] = arr[i];
			help[k] += 1;
		}
		for (int i = start; i < end; i ++) {
			arr[i] = tempArr[i];
		}
		return starts;
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

		int[] arr3 = new int[60];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
		for (int i=1; i < arr3.length; i ++){
			if (arr3[i] < arr3[i-1]){
				System.out.println(arr3[i] + " invalid");
			}
		}
//		int[] arr3 = new int[2];
//		arr3[0] = 27;
//		arr3[1] = 21;
////		arr3[2] = 9579;
////		arr3[3] = 9121;
////		arr3[4] = 9575;
//		System.out.println("Original array: " + Arrays.toString(arr3));
//		MSDRadixSort(arr3);
//		System.out.println("Should be sorted: " + Arrays.toString(arr3));

	}
}
