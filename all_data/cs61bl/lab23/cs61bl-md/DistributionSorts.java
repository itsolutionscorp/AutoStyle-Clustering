import java.util.Arrays;
import java.util.*;
import java.lang.*;


public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10];
		int[] starts = new int[10];
		int[] copy = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			counts[arr[i]]++;
			copy[i] = arr[i];
		}
		int cumulStart = 0;
		for (int i = 0; i < 10; i++) {
			starts[i] = cumulStart;
			cumulStart += counts[i];
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < counts[i]; j++) {
				arr[starts[i]] = i;
				starts[i]++;
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
		int[] endsP = countingSortByDigitInBounds(arr,digit,start,end);
		int k = start;
		for(int i : endsP){
			MSDRadixSortFromDigitInBounds(arr,digit-1,k,i);
			k = i;
		}

		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		// helper method, given below.
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
//		countingSort2(arr,digit,1,start,end)
//		return 

		int compVal = (int) Math.pow(10,digit);
		int[] counts = new int[10];
		int[] starts = new int[10];
		int[] ends = new int[10];
		ArrayList<ArrayList<Integer>> copy = new ArrayList<ArrayList<Integer>>(10);
		for (int z = 0; z<10;z++){
			copy.add(new ArrayList<Integer>());
		}
		for (int i = start; i < end; i++) {
			counts[(arr[i]/compVal)%10]++;
			copy.get((arr[i]/compVal)%10).add(arr[i]);
		}
		int cumulStart = start;
		for (int i = 0; i < 10; i++) {
			starts[i] = cumulStart;
			cumulStart += counts[i];
		    ends[i] = cumulStart;
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < counts[i]; j++) {
				arr[starts[i]] = copy.get(i).get(j);
				starts[i]++;
			}
		}
		if(digit==0){
			return new int[0];
		} else {
			return ends;
		}
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
