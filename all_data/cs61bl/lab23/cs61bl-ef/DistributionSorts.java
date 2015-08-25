import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] counts = new int[10];
		for(int i : arr) {
			counts[i] += 1;
		}
		int length = counts.length;
		int index = 0;
		for(int i = 0; i<length; i++) {
			int numTimes = counts[i];
			while(numTimes > 0) {
				arr[index] = i;
				index++;
				numTimes--;
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
			if(digit < 0) {
				return;
			}
			int[] counts = countingSortByDigitInBounds(arr, digit, start, end);

			for(int i = 0; i < counts.length; i++){
				if(i == counts.length-1) {
					MSDRadixSortFromDigitInBounds(arr, digit - 1, counts[i], end);
				}
				else {
					MSDRadixSortFromDigitInBounds(arr, digit - 1, counts[i], counts[i+1]);
				}
			}
	}
	
	public static String prettyPrint(int[] arr) {
		String rtn = "";
		for(int i : arr) {
			rtn = rtn + i + " ";
		}
		return rtn;
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
		int[] count = new int[10];
		
		for(int i = start; i <end; i++) {
			int num = arr[i];
			int d = getDigit(num, digit);
			count[d] +=1;
			i++;
		}
		for(int i = 1; i< count.length; i++) {
			count[i] = count[i] + count[i-1] + start;
		}
		count[0] = start;

		
		
		for(int i = start; i <end; i++) {
			int d = getDigit(arr[i], digit);
			int correct = count[d];
			if(i != correct && correct<arr.length) {
				int temp = arr[correct];
				arr[correct] = arr[i];
				arr[i] = temp;
				count[d] += 1;
				i--;
			}
		}
		return count;
	}

	public static int getDigit(int num, int digit) {
		String number = Integer.toString(num);
		int length = number.length() -1;
		if(length-digit <= 0) {
			return 0;
		}
		else{
			return Integer.parseInt(Character.toString(number.charAt(length-digit)));
		}
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
		int[] arr4 = {1234, 432, 54, 2, 123, 967};
		System.out.println("Original array: " + Arrays.toString(arr4));
		MSDRadixSort(arr4);
		System.out.println("Should be sorted: " + Arrays.toString(arr4));
		
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
	}
}
