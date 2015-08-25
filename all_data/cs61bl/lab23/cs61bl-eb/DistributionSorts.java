import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] counts = new int[10];
		for (int item : arr) {
			counts[item]++;
		}
		int curr = 0;
		for (int i = 0; i < counts.length; i++) {
			for (int j = 0; j < counts[i]; j++) {
				arr[curr] = i;
				curr++;
			}
		}
	}

	/**
	 * Sorts the given array using MSD radix sort. 
	 */
	public static void MSDRadixSort(int[] arr) {
		int maxDigit = mostDigitsIn(arr);
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
		int[] partition = countingSortByDigitInBounds(arr, digit, start, end);
		for (int i = 0; i < partition.length - 1; i++) {
			// partition.length - 1 buckets
			if ((partition[i + 1] != partition[i] + 1) && (noDuplicate(arr, start, end))) {
				MSDRadixSortFromDigitInBounds(arr, digit - 1, partition[i], partition[i + 1]);
			}
		}
		
	}
	
	private static boolean noDuplicate(int[] arr, int start, int end) {
		for (int i = start; i < end - 1; i++) {
			if (arr[i] == arr[i + 1]) {
				return false;
			}
		}
		return true;
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
		
		int[] counts = new int[10]; // always 10 digits
		for (int i = start; i < end; i++) {
			int item = arr[i];
			int correspondingDigit = getDigit(item, digit);
			counts[correspondingDigit]++;
		}
		
		int nonZeroCount = 0;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] != 0) {
				nonZeroCount++;
			}
		}
		
		int[] mapping = new int[10];
		int c = 0;
		for (int i = 0; i < 10; i++) {
			if (counts[i] != 0) {
				mapping[i] = c;
				c++;
			}
		}
		
		int[] rtn = new int[nonZeroCount + 1];
		int curr = 0;
		// fill in rtn
		rtn[0] = 0; // always insert starting from index 0
		curr++;
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] != 0) {
				rtn[curr] = counts[i] + rtn[curr - 1];
				curr++;
			}
		}
		
		
		int[] starts = new int[nonZeroCount];
		// get starts from rtn
		for (int i = 0; i < starts.length; i++) {
			starts[i] = rtn[i];
		}
		
		// start putting stuff into new array according to starts
		int[] newStuff = new int[end -start];
		for (int i = start; i < end; i++) {
			int item = arr[i];
			int correspondingDigit = getDigit(item, digit);
		
			int bucket = mapping[correspondingDigit];
			newStuff[starts[bucket]] = item;
			starts[bucket]++;
		}
		
		// replace arr
		for (int i = start; i < end; i++) {
			arr[i] = newStuff[i - start];
		}
		
		// increase each item in rtn by start
		for (int i = 0; i < rtn.length; i++) {
			rtn[i] += start;
		}
		return rtn;
	}

	
	private static int getDigit(int item, int digit) {
		int correspondingDigit = 0;
		for (int i = 0; i < digit - 1; i++) {
			// divide by 10 for digit - 1 times
			item = item / 10;
		}
		correspondingDigit = item;
		return correspondingDigit % 10;
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
//		int[] arr0 = new int[] {1, 10, 500, 1000, 54, -7};
//		System.out.println("Original array: " + Arrays.toString(arr0));
//
//		System.out.println("mostDigitsIn: " + mostDigitsIn(arr0));
		
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
		
//		int[] a = new int[] {822, 867, 62, 52, 0, 234, 861};
//		MSDRadixSort(a);
//		System.out.println("Should be sorted: " + Arrays.toString(a));
		
//		int[] a = new int[] {7848, 4431, 2011, 2245, 641, 6172, 3488, 4217, 5573, 9347, 1502, 9244, 7199, 8881, 6706, 6034, 8435, 2691, 468, 3494, 7592, 2077, 100, 2093, 1911, 7133, 1090, 1376, 2093, 4970};
//		MSDRadixSort(a);
//		System.out.println("Should be sorted: " + Arrays.toString(a));
	}
}
