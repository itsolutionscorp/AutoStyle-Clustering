import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] counts = new int[10];
		int[] starts = new int[10];
		int[] sorted = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			counts[arr[i]] += 1;
		}
		for(int i = 1; i < counts.length; i++) {
			starts[i] = starts[i-1] + counts[i-1];
		}
		for(int i = 0; i < arr.length; i++) {
			sorted[starts[arr[i]]] = arr[i];
			starts[arr[i]] += 1;
		}
		for(int i = 0; i < arr.length; i++) {
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
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit, int start, int end) {
		int[] partitions = countingSortByDigitInBounds(arr, digit, start, end);
		//System.out.println(Arrays.toString(arr));
		//System.out.println(Arrays.toString(partitions));
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(countingSortByDigitInBounds(arr, 2, 0, 5)));
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(countingSortByDigitInBounds(arr, 1, 1, 3)));
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(countingSortByDigitInBounds(arr, 1, 3, 5)));
		//System.out.println(Arrays.toString(partitions));
		//System.out.println(digit);
		if(digit <= 0) {
			return;
		}
//		for(int i = 0; i < partitions.length; i++){
//			MSDRadixSortFromDigitInBounds(arr, digit - 1, partitions[i], partitions[i+1]);
//		}
		for(int i = 0; i < partitions.length; i++) {
			if(i == 0) {
//				if (arr.length - 1 - partitions[i] > 1) {
					//System.out.println("" + partitions[i] + (partitions.length - 1));
					MSDRadixSortFromDigitInBounds(arr, digit - 1, 0, partitions[i]);
//				}
			}
			else {
//				if (partitions[i + 1] - partitions[i] > 1) {
					//System.out.println("" + partitions[i] + (partitions[i + 1]));
//				if(i == partitions.length - 1) {
//					MSDRadixSortFromDigitInBounds(arr, digit - 1, partitions[i - 1], partitions[i] - 1);
//					MSDRadixSortFromDigitInBounds(arr, digit - 1, partitions[i], end);
//				}
//				else {
					MSDRadixSortFromDigitInBounds(arr, digit - 1, partitions[i - 1], partitions[i]);
//				}
//				}
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
	private static int[] countingSortByDigitInBounds(int[] arr, int digit, int start, int end) {
		String[] res = new String[arr.length];
		for(int i = start; i<end; i++){
			res[i] = "" + arr[i];
		}
		int[] counts = new int[10];
		int[] starts = new int[10];
		int[] sorted = new int[arr.length];
		//int[] dummyStarts = new int[10];
		for(int i = start; i < end; i++) {
			counts[getDigit(arr[i], digit)] += 1;
		}
		for(int i = 1; i < counts.length; i++) {
			starts[i] = starts[i-1] + counts[i-1];
			//dummyStarts[i] = dummyStarts[i-1] + counts[i - 1];
		}
		for(int i = start; i < end; i++) {
			sorted[starts[getDigit(arr[i], digit)] + start] = arr[i];
			starts[getDigit(arr[i], digit)] += 1;
		}
		for(int i = start; i < end; i++) {
			arr[i] = sorted[i];
		}
		return starts;
	}
	
	private static int getDigit(int num, int digit) {
		int res = -1;
		String str = "" + num;
		try{
			res = Integer.parseInt(""+str.charAt(str.length()-1-digit));
		}
		catch(StringIndexOutOfBoundsException lame){
			res = 0;
		}
		return res;
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
		
//		int[] arr3 = new int[] {6782, 6781, 4102, 4000, 4369, 4359, 9987, 9632, 4125, 7741};
//		System.out.println("Original array: " + Arrays.toString(arr3));
//		MSDRadixSort(arr3);
//		System.out.println("Should be sorted: " + Arrays.toString(arr3));
	}
}
