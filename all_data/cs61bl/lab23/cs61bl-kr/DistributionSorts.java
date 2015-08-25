import java.util.Arrays;
import java.util.HashSet;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
			int count[] = new int[10];
			int start[] = new int[10];
			for (int i = 0; i < arr.length; i++) {
				int a = arr[i]; 
				count[a] = count[a] + 1;
			}
			int counter = 0;
			for (int j = 0; j < 10; j++) {
				start[j] = counter;
				counter = counter + count[j];
			}
			int[] temp = new int[arr.length];
			for (int k = 0; k < arr.length; k++) {
				temp[start[arr[k]]] = arr[k];
				start[arr[k]] = start[arr[k]] + 1;
				//System.out.println(Arrays.toString(temp));
			}
			counter = 0;
			for (int i: temp) {
				arr[counter] = i;
				counter++;
			}
	}
	
	public static void countingSort(int[][] arr) { 
		int count[] = new int[10];
		int start[] = new int[10];
		
		for (int i = 0; i < arr[0].length; i++) {
			int a = arr[0][i]; 
			count[a] = count[a] + 1;
		}
		
		int counter = 0;
		for (int j = 0; j < 10; j++) {
			start[j] = counter;
			counter = counter + count[j];
		}

		int[][] temp = new int[2][arr[0].length];
		
		for (int k = 0; k < arr[0].length; k++) {
			int x = arr[0][k];
			int y = arr[1][k];
			temp[0][start[x]] = x;
			temp[1][start[x]] = y;
			start[x] = start[x] + 1;
//			System.out.println(Arrays.toString(temp[1]) + "temp 1");

		}
		
		counter = 0;
		for(int i: temp[0]) {
		arr[0][counter] = i;
		counter++;
		}
		
		counter = 0;
		for(int i: temp[1]) {
		arr[1][counter] = i;
		counter++;
		}
//		System.out.println(Arrays.toString(arr[1]) + "arr");
}

	/**
	 * Sorts the given array using MSD radix sort. 
	 */
	public static void MSDRadixSort(int[] arr) {
		int maxDigit = 0;
		MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length);
	}

	/**
	 * Radix sorts the input array only between the indices start and end. Only
	 * considers digits from the input digit on down. This method is recursive.
	 */
	
	public static int getDigit(int number, int n) {    
		  return (int) ((number / Math.pow(10, n - 1)) % 10);
		}
	
//	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit,
//		int start, int end) {
//		int[][] temp = new int[2][end];
//		
//		int newCounter = 0;
//		
//		for(int number: arr) {
//			temp[0][newCounter] = getDigit(number, mostDigitsIn(arr) - digit);
//			temp[1][newCounter] = newCounter;
//			newCounter++;
//		}
//		countingSort(temp);
//	
//		int[] tempArr = new int[end];
//		for (int i = 0; i < end; i++) {
//			tempArr[i] = arr[temp[1][i]];
//		}
//		
//		int counter = 0;
//		for (int i: tempArr) {
//			arr[counter] = i;
//			counter ++;
//		}
//		
//		if(digit > 0) {
//			MSDRadixSortFromDigitInBounds(arr, digit -1, start, end);
//		}
//	}
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit,
			int start, int end) {
			int[][] temp = new int[2][end-start];
			int newCounter = start;
			for(int begin = 0; begin < end-start; begin++) {
				temp[0][begin] = getDigit(arr[newCounter], mostDigitsIn(arr) - digit);
				temp[1][begin] = newCounter;
				newCounter++;
			}
			countingSort(temp);
		
			int[] tempArr = new int[end-start];
			for (int i = 0; i < (end-start); i++) {
				tempArr[i] = arr[temp[1][i]];
			}
			
			int counter = start;
			for (int i: tempArr) {
				arr[counter] = i;
				counter ++;
			}
			for(int j= mostDigitsIn(arr) - digit; j > 0 ; j--){
				for(int i = start; i < end; i++) {
					if (getDigit(arr[start], mostDigitsIn(arr) -digit) != getDigit(arr[i], mostDigitsIn(arr) - digit)) {
							MSDRadixSortFromDigitInBounds(arr, digit +1, start, i);
							start = i;
					}
					else if (i + 1 == arr.length) {
						MSDRadixSortFromDigitInBounds(arr, digit +1, start, end);

					}
				}
			}
		}
	
	// first order the list
	// split the numbers based on first number 
	// enter the for loop, if the mod of the number is the same number then order them based off the next number
	// else place it into the final array

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
		System.out.println(getDigit(5,1));
		System.out.println(getDigit(1000,4));
		int[] arr1 = new int[20];
		
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = randomDigit();
		}
		System.out.println(mostDigitsIn(arr1));
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
