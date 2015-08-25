import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] count = new int[10];
		for (int i=0;i<arr.length;i++){
			count[arr[i]]++;
		}
		int current=0;
		for (int i=0;i<10;i++){
		    for(int j=0;j<count[i]; j++){
		    	arr[current] = i;
		    	current++;
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
		
		//sorting into buckets already in this helper by modifying the array
		int[] boundary = countingSortByDigitInBounds(arr, digit, start, end);
		if(digit<0)return;
		for(int i=0; i<boundary.length; i++){
			if(i==0) MSDRadixSortFromDigitInBounds(arr, digit-1, start, boundary[i]);
			else MSDRadixSortFromDigitInBounds(arr, digit-1, boundary[i-1], boundary[i]);
		}
		return;
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
		if(digit<0){return null;}
		int[] boundary = new int[10];
		int[] count = new int[10];
		for(int i = start; i<end; i++){
			count[(arr[i]/((int) Math.pow(10, digit)))
					%10]++;
		}
		boundary[0]=start;
		boundary[1]=count[0]+start;
		for(int i = 2; i<10; i++){
			boundary[i]= boundary[i-1]+count[i-1];
		}
		Stack<Integer> elems = new Stack<Integer>();
		for(int i = start; i<end; i++){
			elems.push(arr[i]);
		}
		while(!elems.isEmpty()){
			int next = elems.pop();
			arr[boundary[(next/((int)Math.pow(10, digit)))%10]]=next;
			boundary[(next/((int)Math.pow(10, digit)))%10]++;
		}
		return boundary;
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
