import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] counts = new int[10];
		int[] newArr = new int[arr.length];
		
		//counts contains the occurrence of each value
		for(int i = 0; i < arr.length; i++) {
			
			counts[arr[i]] += 1;
			
		}
		
		int[] starts = new int[counts.length];
		starts[0] = 0;
		
		for (int i = 0; i < counts.length - 1; i++) {
			starts[i+1] = counts[i];
			counts[i+1] += counts[i];
		}
		
		for(int i = 0; i < arr.length; i++) {
			switch(arr[i]) {
			case 0: newArr[starts[0]] = 0;
					starts[0] +=1;
					break;
			
					
			case 1: newArr[starts[1]] = 1;
			starts[1] +=1;
			break;
			
			case 2: newArr[starts[2]] = 2;
			starts[2] +=1; 
			break;
			
			case 3: newArr[starts[3]] = 3;
			starts[3] +=1;
			break;
			
			case 4: newArr[starts[4]] = 4;
			starts[4] +=1; 
			break;
			
			case 5: newArr[starts[5]] = 5;
			starts[5] +=1; 
			break;
			case 6: newArr[starts[6]] = 6;
			starts[6] +=1;
			break;
			case 7: newArr[starts[7]] = 7;
			starts[7] +=1;
			break;
			
			case 8: newArr[starts[8]] = 8;
			starts[8] +=1; 
			break;
			case 9: newArr[starts[9]] = 9;
			starts[9] +=1; 
			break;
			}
		}          
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = newArr[i];
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
		if (digit == -1) {
			return;
		}
		if (end - start <= 1) {
			return;
		}
		int[] edges = countingSortByDigitInBounds(arr, digit, start, end);
	
		int prev = start;
		for (int i = 0; i < edges.length; i++) {
			MSDRadixSortFromDigitInBounds(arr, digit - 1, prev,
					edges[i]);
			prev = edges[i];
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
		int[] numbers = new int[10], places = new int[10];
		int[] resolved = new int[end - start];
		int sum = 0;
		for (int i = start; i < end; i++) {
			numbers[(int) ((arr[i] / Math.pow(10, digit)) % 10)] += 1;
		}
		for (int i = 0; i < 10; i++) {
			places[i] = sum + start;
			sum += numbers[i];
		}
		for (int i = start; i < end; i++) {
			int item = arr[i];
			int place = places[findDig(item, digit)];
			resolved[place - start] = item;
			places[findDig(item, digit)]++;
		}
		for (int i = start; i < end; i++) {
			arr[i] = resolved[i - start];
		}
		return places;
	}

	
	public static int findDig(int num, int digit) {
		return (int) (num / (Math.pow(10, digit))) % 10;
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
