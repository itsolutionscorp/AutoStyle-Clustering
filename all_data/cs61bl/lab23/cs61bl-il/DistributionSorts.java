import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int min = 0;
		int max = 9;
		int[] counts = new int[max - min + 1];
		for (int num : arr){
			counts[num - min]++;
		}		
		
		int index = 0;
		for (int i = min; i <= max; i++){
			while(counts[i-min]>0){
				arr[index] = i;
				index++;
				counts[i - min]--;
			}
		}		
	}

	/**
	 * Sorts the given array using MSD radix sort. 
	 */
	public static void MSDRadixSort(int[] arr) {
		int maxDigit = mostDigitsIn(arr) - 1;
		//System.out.println(maxDigit);
		MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length);
	}


	
	/**
	 * Radix sorts the input array only between the indices start and end. Only
	 * considers digits from the input digit on down. This method is recursive.
	 */
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit, int start, int end) {
		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		// helper method, given below.
		
		int[] boundaries = countingSortByDigitInBounds(arr, digit, start, end);
		
		int newD = digit-1;	
		if ( newD!=-1){
		int[][] boundsArr = new int[10][10];
		
		int lastBound=0;
			for (int i = 0; i < 10; i++){
				if (boundaries[i] !=0 ){
					int nextBound = lastBound + boundaries[i]; 
//					System.out.println(lastBound);
//					System.out.println(nextBound);
//					if ( newD!=-1){
					boundsArr[i] = countingSortByDigitInBounds(arr, newD, lastBound, nextBound);
					//MSDRadixSortFromDigitInBounds(arr, newD, lastBound, nextBound);
					lastBound = nextBound;
//					}
						
				//countingSortByDigitInBounds(arr, digit-1, lastBound, nextBound+boundaries[1]);
				}
			}
//		}
		//int[] subArray = Arrays
			

		int[][] boundsArr1 = new int[10][10];		
//			
			lastBound = 0;
			for (int i = 0 ; i < 10; i++){
				int[] bound = boundsArr[i];
				for (int j = 0; j< 10; j++){
					int nextBound = lastBound + bound[j];
					boundsArr[i]=countingSortByDigitInBounds(arr, newD-1, lastBound, nextBound);
					lastBound = nextBound;
				}
			}
			
		
//		lastBound = 0;
//		for (int i = 0 ; i < 10; i++){
//			int[] bound = boundsArr1[i];
//			for (int j = 0; j< 10; j++){
//				int nextBound = lastBound + bound[j];
//				countingSortByDigitInBounds(arr, newD-2, lastBound, nextBound);
//				lastBound = nextBound;
//			}
//		}
	}
			
			
			
		//System.out.println(Arrays.toString(boundsArr[0]));
		
		
		
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
		
//		if (start==end){
//			return null;
//		}
 
		int[] sortPort = new int[end-start];
		
		int[] counts = new int[10];
		
		for (int i = start; i < end; i++){
			int sd = (int) Math.floor(arr[i]/Math.pow(10, digit))%10;
			counts[sd]++;		
		}
		
		
		int index = 0;
		for (int i = 0; i < 10; i ++){
			for (int k = start; k < end; k++){
				if (i == (int) Math.floor(arr[k]/Math.pow(10, digit))%10){
					sortPort[index]=arr[k];
					index++;
				}				
			}
		}

		for (int k = start; k < end; k++){
			arr[k]=sortPort[k-start];
		}
			
//		System.out.println(Arrays.toString(sortPort));
//		System.out.println(Arrays.toString(counts));
		return counts;
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
	private static boolean isSorted(int[] arr){
		for (int i =0; i < arr.length-1; i++){
			
			if (arr[i]>arr[i+1]){
				System.out.println(arr[i]);
				System.out.println(arr[i+1]);
				return false;
				
			}
		}
		return true;
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
		//System.out.println(arr3.length);
		System.out.println(isSorted(arr3));
	}
}