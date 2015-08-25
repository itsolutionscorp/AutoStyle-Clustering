import java.util.ArrayList;
import java.util.Arrays;

public class DistributionSorts {
	
 

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] count= new int[10];
		for (int number: arr){
			count[number]++;
		}
		
	
		
		int f=0;
		for(int i = 0; i <= 9 ; i++){
			while( count[i] > 0){
				arr[f]= i;
				f++;
				count[i]--;
			}
		}
		
	}

	/**
	 * Sorts the given array using MSD radix sort. 
	 */
	public static void MSDRadixSort(int[] arr) {
		int maxDigit = mostDigitsIn(arr) - 1;
		//System.out.println("got here start");
		MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length);
		//System.out.println("got here done");
	}

	/**
	 * Radix sorts the input array only between the indices start and end. Only
	 * considers digits from the input digit on down. This method is recursive.
	 */
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit,
			int start, int end) {
		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		// helper method, given below.
		int[] bounds= new int[2];
		//e.g. d= 3, start at third digit, run two more times
		int d= digit;
		if( d >= 0){
			bounds= countingSortByDigitInBounds(arr, d, start, end);
			for( int i=0; i < bounds.length; i= i + 2){
				MSDRadixSortFromDigitInBounds( arr, d-1, bounds[i], bounds[i+1]);
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
		
		ArrayList< ArrayList < Integer> > count= new ArrayList< ArrayList <Integer> > ();
		for( int i=0; i<10; i++){
			count.add( new ArrayList<Integer>());
		}
		int initialDigit= digit;
		for (int i= start; i< end; i++){
			int lookat= arr[i];
			while ( digit != 0){
				lookat= lookat / 10;
				digit--;
			}
			digit= initialDigit;
			while( lookat > 9){
				lookat= lookat % 10;
			}
			
			count.get(lookat).add(arr[i]);
		}

		ArrayList< Integer> generatedBounds= new ArrayList<Integer>();
		int beginning= start;
		for( ArrayList number : count){		
			
			if( number.size() > 0){
				generatedBounds.add(start);
				generatedBounds.add(start + number.size());
				start= start + number.size();
			}
		}
		int[] bounds= new int[ generatedBounds.size()];
		for( int i=0; i< bounds.length; i++){
			bounds[i]= generatedBounds.get(i);
		}
//		System.out.println( "bound array length: " + bounds.length);
//		for (int i=0; i< bounds.length; i= i+ 2){
//			System.out.println( "bound: " + bounds[i] + " " + bounds[i+1]);
//		}
	
		int f= beginning;
		for(int i = 0; i < 10 ; i++){
			while( count.get(i).size() > 0){
				arr[f]= count.get(i).get(0);
				count.get(i).remove(0);
				f++;
			}
		}

		
		return bounds;
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
		//System.out.println("max digit in this array is:" + maxDigitsSoFar);
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
