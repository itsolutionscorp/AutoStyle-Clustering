import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		if (arr.length == 1 || arr.length == 0) {
			return;
		} else {
			
			int[] counts = new int[10];
			for (int i = 0; i < arr.length; i++) {
				counts[arr[i]]++;
			}

			int[] starts = new int[counts.length];
			starts[0] = counts[0];
			for (int i = 1; i < counts.length; i++) {
				
				starts[i] = starts[i - 1] + counts[i];
			}
			
			int[] result = new int[arr.length];
			for (int i = 0; i < result.length; i++) {
				
				starts[arr[i]] --;
				result[starts[arr[i]]] = arr[i];
			}
			
			for (int i = 0; i < arr.length; i ++){
				arr[i] = result[i];
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
		if (digit == 0){
			countingSort(arr);
			return;
		}
		else{
		boolean completed = true;
		
		for (int i = 0; i < arr.length - 1; i ++){
			if (arr[i] > arr[i + 1]){
				completed = false;
			}
		}
		if(completed){
			return ;
		}else{
			countingSortByDigitInBounds(arr, digit, start, end);
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
		int[] temp = new int [end - start];
		//System.out.println(" Temp Array length " + temp.length);
		for (int i = 0; i < temp.length; i ++){
			int sd = (int) (arr[i] % Math.pow(10, digit));
			temp[0] = arr[i];
			//System.out.println("Digit " + digit  + " Inside temp Array index " + i + " -> " + temp[0]);
		}
		//MSDRadixSortFromDigitInBounds(temp,digit,start,end);
		return temp;
		
		
		
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
		System.out.println("Original array  : " + Arrays.toString(arr1));
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
