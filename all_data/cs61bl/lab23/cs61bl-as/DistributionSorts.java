import java.util.ArrayList;
import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		// since we only have ten number, we create
		int[] counts = new int[10];
		// iterate through your array of and increment
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < 10; j++) {
				if (arr[i] == j) {
					counts[j]++;
				}
			}
		}
		// create a new array that will be the sorted array
		int[] sorted = new int[arr.length];
		int[] starts = new int[10];

		// put in the starts information
		int perSum = 0;
		for (int jj = 0; jj < 10; jj++) {
			starts[jj] = perSum;
			perSum = perSum + counts[jj];
		}

		for (int ii = 0; ii < arr.length; ii++) {
			for (int jj = 0; jj < 10; jj++) {
				if (arr[ii] == jj) {
					sorted[starts[jj]] = jj;
					starts[jj]++;
				}
			}
		}
		// iterate through the
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sorted[i];
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
		countingSortByDigitInBounds(arr, digit, start, end);
		if (digit != 0) { // each larger group should have ten subgroup
			int starts[] = getStarts(arr, digit, start, end);
			//System.out.println("the sub array start" + Arrays.toString(starts));
			for (int i = 0; i < 10; i++) {
				int a = starts[i];
				int b = starts[i + 1];
				MSDRadixSortFromDigitInBounds(arr, digit - 1, a, b);
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
		// only processing the part of array
		ArrayList<Integer>[] partSorted = new ArrayList[10]; // ten boxes to put
		// initialize all the partSorted
		for(int i = 0 ; i <10; i ++){
			partSorted[i] = new ArrayList<Integer>();
		}
		// if the digit
		int m = (int) Math.pow(10, digit+1);
		int n = (int) Math.pow(10, digit);
		// add all the number to a arraylist
		for (int i = start; i < end; i++) {
			int boxID = (arr[i] % m) / n;
			partSorted[boxID].add(arr[i]);
		}
		
		
		// did not put in correctly 
		int counter = start;
		for (int i = 0; i < partSorted.length; i++) {
			for (int j = 0; j < partSorted[i].size(); j++) {
				arr[counter] = partSorted[i].get(j);
				counter++;
			}
		}
		return arr;
	}

	// helper method find the buket loction
	private static int[] getStarts(int[] arr, int digit, int start, int end) {
		ArrayList<Integer>[] partSorted = new ArrayList[10]; // ten boxes to put
		for(int i = 0 ; i <10; i ++){
			partSorted[i] = new ArrayList<Integer>();
		}
		int[] startID = new int[11];
		int m = (int) Math.pow(10, digit+1);
		int n = (int) Math.pow(10, digit);
		for (int i = start; i < end; i++) {
			int boxID = (arr[i] % m) / n;
			partSorted[boxID].add(arr[i]);
		}
		int counter = start;
		for (int i = 0; i < partSorted.length; i++) {
			startID[i] = counter;
			counter = counter + partSorted[i].size();
		}
			startID[10] =end;
		return startID;

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
		System.out.println("..........." +(int) Math.pow(10,0)+" "+(5 / 10));
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
