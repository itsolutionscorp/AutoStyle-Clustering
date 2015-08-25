import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts = new int[10]; //holds the counts of the contents of arr 
		for (int i = 0; i < arr.length ; i++) {
			if(arr[i] == 0) {
				counts[0]++; 
			} else if(arr[i] == 1) {
				counts[1]++; 
			} else if(arr[i] == 2) {
				counts[2]++; 
			} else if(arr[i] == 3) {
				counts[3]++; 
			} else if(arr[i] == 4) {
				counts[4]++; 
			} else if(arr[i] == 5) {
				counts[5]++; 
			} else if(arr[i] == 6) {
				counts[6]++; 
			} else if(arr[i] == 7) {
				counts[7]++; 
			} else if(arr[i] == 8) {
				counts[8]++; 
			} else if(arr[i] == 9) {
				counts[9]++; 
			}
		}
		int[] starts = new int[10]; //holds the starting index of the new sorted array 
//		starts[0] = 0; 
		for (int j = 1; j < counts.length; j ++) {
			starts[j] = counts[j - 1] + starts[j - 1];
		}
		
		
		for (int j = 0; j < counts.length; j++){
			
		}
		
		int[] sorted = new int[arr.length];
		for(int k = 0; k < arr.length; k++) {
			if(arr[k] == 0){
				sorted[starts[0]] = 0;
				starts[0]++; 
			} else if(arr[k] == 1) {
				sorted[starts[1]] = 1;
				starts[1]++; 
			}else if(arr[k] == 2) {
				sorted[starts[2]] = 2;
				starts[2]++; 
			}else if(arr[k] == 3) {
				sorted[starts[3]] = 3;
				starts[3]++; 
			}else if(arr[k] == 4) {
				sorted[starts[4]] = 4;
				starts[4]++; 
			}else if(arr[k] == 5) {
				sorted[starts[5]] = 5;
				starts[5]++; 
			}else if(arr[k] == 6) {
				sorted[starts[6]] = 6;
				starts[6]++; 
			}else if(arr[k] == 7) {
				sorted[starts[7]] = 7;
				starts[7]++; 
			}else if(arr[k] == 8) {
				sorted[starts[8]] = 8;
				starts[8]++; 
			}else if(arr[k] == 9) {
				sorted[starts[9]] = 9;
				starts[9]++; 
			}
		}
		for(int l = 0; l < sorted.length; l++) {
			arr[l] = sorted[l];
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
		if (start == end){
			return;
		}
		if (digit == 0 || arr.length == 0){
			return;
		}
		
		
		int mod = (int) Math.pow(10, digit - 1);
				
		int s = start;
		int e = end;
		int j = 0;
		int k = 1;

		int[] bounds = countingSortByDigitInBounds(arr, mod, s, e);
		
//		for (int myMod = 1; myMod < mods.length; myMod ++){
			while(k < bounds.length - 1){
				MSDRadixSortFromDigitInBounds(arr, digit - 1, s, e);
				s = bounds[j];
				e = bounds[k];
				j = k;
				k++;
			}
			
			
//		}
		
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
		int[] toReturn = new int[arr.length];
		int[] bounds = new int[arr.length];
		bounds[0] = 0;
		int boundCount = 1;
		
		for (int i = 0; i < 10; i ++){
			int count = 0;
			for (int j = start; j < end; j ++){
				if (arr[j] / digit == i){
					int temp = arr[count];
					arr[count] = arr[j];
					arr[j] = temp;
					count++;
				}
			}
			bounds[boundCount] = count;
			boundCount ++;
		}
		arr = toReturn;
		
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
