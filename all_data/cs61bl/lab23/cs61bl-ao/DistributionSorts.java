import java.util.ArrayList;
import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		//initializing the array
		int[] countArray = new int[10];
		
		//getting number of occurences for each number
		for (int i = 0; i< arr.length; i++) {
			countArray[arr[i]]++;
		}
//		for (int j = 1; j< countArray.length; j++) {
//			countArray[j] = countArray[j] + countArray[j-1];
//		}

		int index = 0;
		for (int a = 0; a < countArray.length; a++) {
			for (int b = 0; b < countArray[a]; b++) {
				arr [index] = a;
				index++;
			}
		}
		
//		// transferring over the values to a new Array
//		int[] tempArray = new int[arr.length];
//		for (int k = 0; k < arr.length; k++) {
//			int number = tempArray[k];
//			int index = countArray[number] - 1;
//			countArray[number]--;
//			arr[index] = number;
//		}
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
		if (arr.length <= 1) return;
		if (digit < 0) return;
		int[] startingPositions = countingSortByDigitInBounds(arr, digit, start, end);
		int[][] what = new int[10][];
		for (int i = 0; i < what.length; i++) {
			if (i!= what.length-1) {
				int value = startingPositions[i+1]-startingPositions[i];
				what[i] = new int[value];
			} else {
				int value = arr.length-startingPositions[i];
				what[i] = new int[value];
			}
		}
		double divisor;
		if (digit == 0) divisor = 1.0;
		else divisor = Math.pow(10, digit);
		for (int j = 0; j < arr.length; j++) {
			int index = 0;
			int number = (int) (arr[j]/divisor);
			while (number > 9) number-= 10;
			while(what[number][index] != 0) index++;
			what[number][index] = arr[j];
		}
		for (int[] a : what) {
			if (a.length > 1) MSDRadixSortFromDigitInBounds(a, digit -1, start, end);
		}
		// merge everything together
		int[] answer = new int[arr.length];
		int index = 0;
		for (int a = 0; a < what.length; a++) {
			int counter = 0;
			int size = what[a].length;
			while (size != 0) {
				arr[index] = what[a][counter];
				counter++;
				index++;
				size--;
			}
		}
		arr = answer;
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
		double divisor = Math.pow(10, digit);
		if (divisor == 0) divisor = 1.0;
		int [] newArr = new int[10];
		for (int i = 0; i < arr.length; i ++) {
			int number = (int)(arr[i]/divisor);
			while (number > 9) number-= 10;
			newArr[number] ++;
		}
		int[] secondNewArr = new int[10];
		for (int i = 0; i < secondNewArr.length; i ++) {
			if (i == 0) secondNewArr[i] = 0;
			else secondNewArr[i] = secondNewArr[i-1] + newArr[i-1];
		}
		return secondNewArr;
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
//		arr2[0] = 3;
//		arr2[1] = 7;
//		arr2[2] = 3;
		System.out.println("Original array: " + Arrays.toString(arr2));
		MSDRadixSort(arr2);
		System.out.println("Should be sorted: " + Arrays.toString(arr2));

		int[] arr3 = new int[10];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
	}
}
