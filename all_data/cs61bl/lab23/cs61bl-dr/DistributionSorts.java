import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] copy = Arrays.copyOf(arr, arr.length);
		int[] numEach = new int[10];
		for(int i = 0; i < arr.length; i++){
			numEach[arr[i]]++;
		}
		int[] starts = new int[10];		
		for(int i = 1; i < numEach.length; i++){
			starts[i] = numEach[i - 1] + starts[i - 1];
		}
		for(int i = 0; i < arr.length; i++){
			arr[starts[copy[i]]] = copy[i];
			starts[copy[i]]++;			
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
		if(start == end || digit < 0) return;
		else{
			int[] boundaries = countingSortByDigitInBounds(arr, digit, start, end);	
			
//			System.out.println("Boundaries: ");			
//			for(int i : boundaries) System.out.print(i + " ");
//			System.out.println();
						
			MSDRadixSortFromDigitInBounds(arr, digit - 1, start, boundaries[0]);
			for(int j = 0; j < boundaries.length - 1; j++){				
				MSDRadixSortFromDigitInBounds(arr, digit - 1, boundaries[j], boundaries[j + 1]);
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
		
		int[] copy = Arrays.copyOf(arr, arr.length);
		int[] numEach = new int[10];
		
		//System.out.println("digit is: " + digit);
				
		for(int i = start; i < end; i++){
			int dig = arr[i];		
			//System.out.println("number is " + dig);
			for(int j = 0; j < digit; j++){
				dig /= 10;
			}
			dig %= 10;		
			//System.out.println("digit is " + dig);
			numEach[dig]++;
		}
		
//		System.out.println("num each : ");
//		for(int i : numEach) System.out.print(i + " ");
//		System.out.println();		
		
		int[] starts = new int[10];	
		starts[0] = start;
		for(int i = 1; i < 10; i++){
			starts[i] = numEach[i - 1] + starts[i - 1];
		}
		
//		System.out.println("starts : ");
//		for(int i : starts) System.out.print(i + " ");
//		System.out.println();
		
		for(int i = start; i < end; i++){
			int dig = copy[i];
			for(int j = 0; j < digit; j++){
				dig /= 10;
			}
			dig %= 10;		
			arr[starts[dig]] = copy[i];
			starts[dig]++;			
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
