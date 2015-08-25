import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] counts = new int[10];
		for(int e: arr){
			counts[e]++;
		}
		int[] starts = new int[10];
		starts[0] = 0;
		for(int i=1; i<10; i++){
			starts[i] = starts[i-1] + counts[i-1];
		}
		int[] sorted = new int[arr.length];
		for(int e: arr){
			int positionToEnter = starts[e];
			sorted[positionToEnter] = e;
			starts[e]++;
		}
		
		for(int i=0; i<arr.length; i++){
			arr[i]=sorted[i];
		}

	}

	
	public static int[] countingSortMod(int[] original, int[] digits, HashMap<Integer,Integer> key) {
		int[] counts = new int[10];
		for(int e: digits){
			counts[e]++;
		}
		int[] starts = new int[10];
		starts[0] = 0;
		for(int i=1; i<10; i++){
			starts[i] = starts[i-1] + counts[i-1];
		}
		int[] sorted = new int[original.length];
		for(int o: original){
			int d = key.get(o);
			int positionToEnter = starts[d];	
			sorted[positionToEnter] = o;
			starts[d]++;
		}
		
		for(int i=0; i<original.length; i++){
			original[i]=sorted[i];
		}

//		System.out.println(Arrays.toString(original));
		
		return starts;
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
		
		if(digit == -1){
			return;
		}
		
		System.out.println("sorting digit "+ digit);
		int [] boundaries = countingSortByDigitInBounds(arr, digit, start, end);

		
		int prevB = 0;
		for(int b: boundaries){
			MSDRadixSortFromDigitInBounds(arr,digit-1,prevB,b);
			prevB = b;
		}
		
//		System.out.println("arr: " + Arrays.toString(arr));
		
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
		

		
		
		int[] toSort = new int[end-start];
		int j=0;
		for(int i=start; i<end; i++, j++){
			toSort[j] = arr[i];
		}
				
		
		int[] keyDigit = new int[end-start];
		HashMap<Integer,Integer> originalToKeyDigit = new HashMap<Integer,Integer>();
		
		for(j=0; j<end-start; j++){
			int element = toSort[j]; 
			int mod = 10;
			int nextNum = 0;
			for(int i=0; i<=digit; i++){
				nextNum = element % mod;
				mod = mod*10;
				element = element - nextNum;
			}
			int divisor = (int) Math.pow(10, digit);
//			System.out.println("divisor: " + divisor);
//			System.out.println("digit: " + digit);
			keyDigit[j] = nextNum/divisor;
			originalToKeyDigit.put(toSort[j], keyDigit[j]);
		}
		
		
		int[] starts = countingSortMod(toSort, keyDigit, originalToKeyDigit);

	
		j=0;
		for(int i=start; i<end; i++, j++){
			System.out.println("arr.length: " + arr.length);
			System.out.println("toSort.length: " + toSort.length);

			arr[i] = toSort[j];
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
		countingSort(arr1);
		if (arr1 != null) {
			System.out.println("Should be sorted: " + Arrays.toString(arr1));
		}

		
		int[] arr2 = new int[3];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr2));		
//		MSDRadixSortFromDigitInBounds(arr2, 0, 0, 3);
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
