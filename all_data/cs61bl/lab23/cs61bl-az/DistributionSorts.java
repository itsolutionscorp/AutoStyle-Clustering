
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
        int[] counts = new int[10];
        int[] starts = new int[10];
        int[] arrCopy = arr.clone();
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i]]++;
        }
        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            pos += counts[i];
            starts[i] = pos;
        }
        int[] index = new int[10];
        for (int i = 0; i < arr.length; i++) {
            int v = arrCopy[i];
            if (v == 0) {
                arr[index[0]] = 0;
            } else {
                arr[index[v] + starts[v - 1]] = v;
            }
            index[v]++;
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
        int[] starts = countingSortByDigitInBounds(arr, digit, start, end);
        digit = digit - 1;
        if (digit < 0) {
            return;
        }
        int index = 0;
        for (int i = 0; i < starts.length; i++) {
            MSDRadixSortFromDigitInBounds(arr, digit, index + start, starts[i] + start);
            index = starts[i];
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

    private static int[] countingSortByDigitInBounds(int[] arr, int digit, int start, int end) {
        // TODO your code here!
        int pow = 1;
        for (int i = 0; i < digit; i++)
            pow *= 10;

        int[] counts = new int[10];
        for (int i = start; i < end; i++) {
            int NmInDigit = (arr[i] / pow) % 10;
            counts[NmInDigit]++;
        }
        
        int[] starts = new int[10];
        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            pos += counts[i];
            starts[i] = pos;
        }
        
        int[] arrCopy = arr.clone();
        int index[] = new int[10];
        for (int i = start; i < end; i++) {
            int NmInDigit = (arrCopy[i] / pow) % 10;
            if (NmInDigit == 0) {
                arr[index[NmInDigit] + start] = arrCopy[i];
                index[NmInDigit]++;
            } else {
                arr[index[NmInDigit] + start + starts[NmInDigit-1]] = arrCopy[i];
                index[NmInDigit]++;
            }
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
		System.out.println("countingSort!");
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
