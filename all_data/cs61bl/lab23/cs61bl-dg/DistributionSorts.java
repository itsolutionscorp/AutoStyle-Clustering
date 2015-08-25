import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] myCounts = new int[10];
		int[] myStarts = new int[10];
		int counter_1 = 1;
		int counter_0 = 0;
		int item_place = 0;
		for (int myInt: arr) {
			myCounts[myInt]++;
		}
		myStarts[0] = myCounts[0];
		while (counter_1 < 10) {
			myStarts[counter_1] = myStarts[counter_1-1] + myCounts[counter_1];
			counter_1++;
		}
		while (counter_0 < arr.length) {
			if (myCounts[item_place] == 0) {
				item_place++;
				continue;
			}
			arr[counter_0] = item_place;
			if ((counter_0+1) == myStarts[item_place]) {
				item_place++;
			}
			counter_0++;
		}
	}

	/**
	 * Sorts the given array using MSD radix sort.
	 */
	public static void MSDRadixSort(int[] arr) {
		// for (int j = arr.length - 1; j > 0; j--) {
		// 	int latestPos = 0;
		// 	for (int k = 1; k <= j; k++) {
		// 		if (arr[latestPos] <= arr[k] ) {
		// 			latestPos = k;
		// 		}
		// 	}
		// 	if (j != latestPos) {
		// 		int temp = arr[latestPos];
		// 		for (int i = latestPos; i < j; i++) {
		// 			arr[i] = arr[i+1];
		// 		}
		// 		arr[j] = temp;
		// 	}
		// }
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
		int[] bounds = countingSortByDigitInBounds(arr,digit,start,end);
		// System.out.println(Arrays.toString(arr));
		// System.out.println("Bounds Array: " + Arrays.toString(bounds));
		// System.out.println("After sorting on digit" + " " + digit);
		if (digit >= 1) {
			int mostRecent = 0;
			for (int bound: bounds) {
				MSDRadixSortFromDigitInBounds(arr,digit-1,mostRecent,bound);
				mostRecent = bound;
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
		int[] countArray = new int[10];
		int[] returnArray = new int[10];
		int counter = 1;
		int inc_array = start;
		int inc_count = 0;
		int index = start;
		int divider = (int) Math.pow(10,digit);
		while (index < end) {
			if (arr[index] < 10) {
				countArray[arr[index]]++;
				index++;
			}
			else {
				countArray[(arr[index]/divider)%10]++;
			}
			index++;
		}

		//System.out.println("Count Array: " + Arrays.toString(countArray));

		returnArray[0] = countArray[0];
		while (counter < 10) {
			returnArray[counter] = returnArray[counter-1] + countArray[counter];
			counter++;
		}

		for (int lastPos = end - 1; lastPos > start; lastPos--) {
			int recentPos = 0;
			for (int selectedPos = start + 1; selectedPos <= lastPos; selectedPos++) {
				if (arr[recentPos] <= arr[selectedPos]) {
					recentPos = selectedPos;
				}
			}
			if (lastPos != recentPos) {
				int temp = arr[recentPos];
				for (int i = recentPos; i < lastPos; i++) {
					arr[i] = arr[i+1];
				}
				arr[lastPos] = temp;
			}
		}

		return returnArray;
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
