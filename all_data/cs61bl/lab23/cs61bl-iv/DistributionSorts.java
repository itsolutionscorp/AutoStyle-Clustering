import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		int[] temp = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			counts[arr[i]]++;
			temp[i] = arr[i];
		}
		
		int[] starts = new int[10];
		for (int i = 1; i < counts.length; i++) {
			starts[i] = starts[i - 1] + counts[i - 1];
		}
		
		for(int i = 0; i < temp.length; i++) {
			int value = temp[i];
			int index = starts[value];
			arr[index] = value;
			starts[value]++;
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
		if (digit == 0) {
			countingSortByDigitInBounds(arr, digit, start, end);
		}
		else {
//			int[] buckets = countingSortByDigitInBounds(arr, digit, start, end);
//			
//			for (int i = 0; i < buckets.length; i++) {
//				System.out.println(buckets[i]);
//			}
//			
//			for (int i = 1; i < buckets.length; i++) {
//				MSDRadixSortFromDigitInBounds(arr, digit - 1, buckets[i-1], buckets[i]);
//			}
//			MSDRadixSortFromDigitInBounds(arr, digit - 1, buckets[buckets.length - 1], arr.length);
			
			int latest = 0;
			int length = 0;
			for (int i = 1; i < arr.length; i++) { // iterate through the entire list
				if (length == arr.length) {
					countingSortByDigitInBounds(arr, digit, start, end);
				}
				if (arr[latest] / Math.pow(10, digit + 1) != arr[i] / Math.pow(10, digit + 1)) {
					countingSortByDigitInBounds(arr, digit, latest, i);
					latest = i;
				} else {
					length++;
				}
			}
			
			MSDRadixSortFromDigitInBounds(arr, digit - 1, start, end);
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
		int[] digitArr = new int[arr.length];
		int[] tempArr = new int[arr.length];
		int[] counts = new int[10];

//		System.out.println("digits");
		for (int i = 0; i < arr.length; i++) {
			tempArr[i] = arr[i];
			digitArr[i] = arr[i]/((int) Math.pow(10, digit));
			while (digitArr[i] > 9) {
				digitArr[i] = digitArr[i] % 10; 
			}
//			System.out.println(digitArr[i]);
			counts[digitArr[i]]++;
		}
		
//		System.out.println("starts");

		int[] starts = new int[10];
		int[] startsToReturn = new int[10];
		for (int i = 1; i < counts.length; i++) {
			starts[i] = starts[i - 1] + counts[i - 1];
			startsToReturn[i] = starts[i];
		}

//		for (int i = 0; i < counts.length; i++) {
//			System.out.println(counts[i] +  " " + starts[i]);
//
//		}
		
//		System.out.println("tempArr");
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(tempArr[i]);
//
//		}
		
		for(int i = 0; i < arr.length; i++) {
			int value = tempArr[i];
			int tempDigit = digitArr[i];
			int index = starts[tempDigit];
			arr[index] = value;
			starts[tempDigit]++;
		}
		
//		System.out.println("merp");
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(arr[i]);
//		}
		
		return startsToReturn;
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
		
		
		
		int[] arr4 = {356, 112, 904, 294, 209, 820, 394, 810};
		System.out.println("Original array: " + Arrays.toString(arr4));
		MSDRadixSort(arr4);
		System.out.println("Should be sorted: " + Arrays.toString(arr4));
	}
}
