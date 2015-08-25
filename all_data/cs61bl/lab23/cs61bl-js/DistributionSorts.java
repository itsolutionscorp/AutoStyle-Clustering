import java.util.Arrays;
import java.util.LinkedList;
import java.lang.*;

public class DistributionSorts {
	private static final int cutoff = 15;
	
	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] table = new int[10];
		for (int a : arr) {
			table[a] += 1;
		}
		int sortedIndexStart = 0;
		int sortedIndexEnd = 0;
		for (int i = 0; i < 10; i++) {
			sortedIndexEnd += table[i];
			for (int j = sortedIndexStart; j < sortedIndexEnd; j++) {
				arr[j] = i;
			}
			sortedIndexStart += table[i];
		}
	}
	
//	if (arr.length == 0) {
//	return;
//}
//int min = arr[0];
//int max = arr[0];
//for (int i = 1; i < arr.length; i++) {
//	if (arr[i] > max) {
//		max = arr[i];
//	} else if (arr[i] < min) {
//		min = arr[i];
//	}
//}
//int[] counts = new int[max - min + 1];
//for (int i = 0; i < arr.length; i++) {
//	for (int x : arr) {
//		
//	}
//	counts[arr[i] - min]++; 
//}
//int[] starts = new int[counts.length];
//int outputPos = 0;
//for (int i = 0; i < (max - min + 1); i++) {
//	for (int j = 0; j < counts[i]; j++) {
//		arr[outputPos] += min; 
//		outputPos++;
//	}
//}
//}
	/**
	 * Sorts the given array using MSD radix sort. 
	 */
	public static void MSDRadixSort(int[] arr) {
		int maxDigit = mostDigitsIn(arr) - 1;
		int n = arr.length;
        int[] x = new int[n];
		MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length, x);
	}

	/**
	 * Radix sorts the input array only between the indices start and end. Only
	 * considers digits from the input digit on down. This method is recursive.
	 */
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit, int start, int end, int[] x) {
		if (end <= start + cutoff) {
            insertion(arr, digit, start, end);
            return;
        }

        int[] count = new int[10];
        for (int i = start; i <= end; i++) {
            int c = charAt(arr[i], digit);
            count[c+2]++;
        }

        for (int r = 0; r < 10; r++)
            count[r+1] += count[r];

        for (int i = start; i <= end; i++) {
            int c = charAt(arr[i], digit);
            x[count[c+1]++] = arr[i];
        }
        
        for (int i = start; i <= end; i++){
            arr[i] = x[i - start];
		}

        for (int r = 0; r < 10; r++){
        	MSDRadixSortFromDigitInBounds(arr, start + count[r], start + count[r+1] - 1, digit+1, x);
        }
    }

	private static void insertion(String[] a, int start, int end, int digit) {
        for (int i = start; i <= end; i++)
            for (int j = i; j > end && less(a[j], a[j-1], digit); j--)
                exch(a, j, j-1);
    }
	
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private static boolean less(int x, int y, int digit) {
        int a = (int) (x - (x%Math.pow(10, digit)));
        int b = (int) (y - (y%Math.pow(10, digit)));
        return a < b;
    }
	
	private static int charAt(int number, int index){
	    return (int)((int)number / Math.pow(10, index)) % 10;
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
		return null;
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
