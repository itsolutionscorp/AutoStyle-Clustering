import java.util.Arrays;

public class DistributionSorts {
	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] count = new int[10];
		for (int i: arr) {
			count[i]++;
		}
		int[] start = new int[10];
		int index = 0;
		for (int i= 0; i < count.length; i++) {
			start[i] = index;
			index += count[i];
		}
		
		int value = 0;
		for (int j = 0; j < arr.length; j ++) {	
			while (start.length > value && start[value] == j ) {
				value++;
			}
			arr[j] = value - 1;
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
		int[] myStart = countingSortByDigitInBounds(arr, digit, start, end);
		

		if (digit >= 0) {

			int[] newArray = new int[arr.length];
			for (int j = start; j < end ; j ++) {
				int myDigit = (int) (  (arr[j] / (int) Math.pow(10, digit)) % 10  ) ;
				newArray[ start + myStart[myDigit] ] = arr[j];
				myStart[myDigit] ++;
			}
			for (int i = 0; i < newArray.length; i++) {
				if (newArray[i] != 0) {
					arr[i] = newArray[i];
				}

			}
			
		}

		if (digit >= 1) { 
			int startValue = start;			
			for (int i = 0; i  < myStart.length -1; i++ ) {
				if (myStart[i+ 1] - myStart[i] >= 1 ) {
					MSDRadixSortFromDigitInBounds(arr, digit-1, startValue, myStart[i + 1]);
				}
				startValue = myStart[i + 1] ;
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
		int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = start; i < end  ; i ++) {
			
			int myDigit = (int) (  ( (arr[i] / (int) Math.pow(10, digit)) ) % 10 );
			count[myDigit]++;
		}

		int[] myStart = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int index = 0;
		for (int i= 0; i < count.length; i++) {
			myStart[i] = index;
			index += count[i];
		}

		
		return myStart;
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

		int[] arr3 = new int[7];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));



	}
}
