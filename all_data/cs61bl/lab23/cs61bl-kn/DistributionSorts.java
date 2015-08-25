import java.util.Arrays;
public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		
		for (int p : arr) {
			counts[p] = counts[p]+1;
		}
		int p = 0;
		for (int k = 0; k < arr.length; k++) {
			arr[k] = p;
			counts[p] = counts[p] -1;
			if (counts[p] <= 0) {
				p++;
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
		if (start-end > 0) {
			int[] bounds = countingSortByDigitInBounds(arr, digit, start, end);					//sorts by the digit place and boundaries for the next digit place to use in sorting is returned
			for (int k = 0; k < 10; k++) {																					//sorts again by the next digit place for each boundary 
				MSDRadixSortFromDigitInBounds(arr, digit-1, bounds[k], bounds[k+1]);
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
		for (int k = start; k < end; k++) {											// supposed to count up the number of integers between start and end of arr with the certain digit given the digit place
			int p = (int) (arr[k]/Math.pow(10, digit-1) % 10);
			counts[p] = counts[p] + 1;
		}
		int[] starts = new int[11];														
		starts[0] = start;
		for (int k = 1; k < 11; k++) {												//supposed to find the starting index in arr for each kind of digit of the given digit place
			starts[k] = starts[k-1] + counts[k-1];
		}
		int[] currentStarts = new int[11];										//to keep track of where the next integer of a certain digit would go next for each kind of digit in the given digit place
		for (int k = 0; k < 11; k++) {
			currentStarts[k] = starts[k];
		}
		int[] buckets = new int[end - start];
		for (int k = 0; k < buckets.length; k++) {							// copies over all the integers of arr between start and end to a new array
			buckets[k] = arr[k+start];
		}
		for (int k = start; k < end; k++) {											// goes through buckets and puts integer where currentStarts tells it to go next
			int p = (int) (buckets[k]/Math.pow(10, digit-1) % 10);
			arr[currentStarts[p]] = buckets[k];
			currentStarts[p] = currentStarts[p] +1;
		}
		return starts;																				//returns the boundaries of each same-digit bucket
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
