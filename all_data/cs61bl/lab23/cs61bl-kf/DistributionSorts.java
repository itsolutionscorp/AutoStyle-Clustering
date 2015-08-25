import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		int[] starts = new int[10];
		for (int i = 0; i < arr.length; i++) {
			counts[arr[i]] ++;
			if (arr[i] < starts.length - 1){
				starts[arr[i] + 1] ++;
			}
		}
		for (int j = 1; j < starts.length; j ++) {
			starts[j] += starts[j-1];
			
		}
		int startTrack = 0;
		for (int k = 0; k < arr.length; k++) {
			if (counts[startTrack] == 0) {
				startTrack ++;
				k --;
			} else if (startTrack < starts.length - 1 && k == starts[startTrack + 1]){
				startTrack ++;
				k --;
			} else{
				arr[k] = startTrack;
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
		int[] starts = new int[arr.length];
		if (digit + 1 == 0) {
			return;
		}
		starts = countingSortByDigitInBounds(arr, digit, start, end);
		for (int i = 0; i < starts.length; i++) {
			if (i < starts.length - 1 && starts[i] != starts[i+1]) {
				MSDRadixSortFromDigitInBounds(arr, digit - 1, starts[i], starts[i+1]);
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
		int[] digits = new int[arr.length];
		int[] counts = new int[10];
		int[] starts = new int[10];
		
		for (int x = start; x < end; x++) {
			int digitTrack = digit + 1;
			int arrValue = arr[x];
			int value = 0;
			while (digitTrack > 0) {
				digitTrack--;
				value = arrValue % 10;
				arrValue /= 10;
			}
			digits[x] = value;
		}
		for (int i = start; i < end; i++) {
			counts[digits[i]] ++;
			if (digits[i] < starts.length - 1){
				starts[digits[i] + 1] ++;
			}
		}
		
		for (int j = start + 1; j < starts.length; j ++) {
			starts[j] += starts[j-1];
			
		}
		int startTrack = 0;
		for (int k = start; k < end; k++) {
			if (startTrack < starts.length - 1 && counts[startTrack] == 0) {
				startTrack ++;
				k --;
			} else if (startTrack < starts.length - 1 && k == starts[startTrack - 1]){
				startTrack ++;
				k --;
			} else {
				digits[k] = arr[k];
			}
			
		}		
		arr = digits;
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
	
	public static void test(int[] arr) {
		int[] rtn = new int[3];
		helper(arr);
	}
	
	public static void helper(int[] arr) {
		arr[2] = 17;
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
