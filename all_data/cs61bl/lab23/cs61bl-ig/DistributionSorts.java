import java.util.ArrayList;
import java.util.Arrays;



public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] count = new int[10];
		for(int num : arr) {
			count[num]++;
		}
		
		
		int index = 0;
		int indexCount = 0;
		for(int i = 0; i < arr.length; i++) {
			
			while(indexCount == count[index]) {
				index++;
				indexCount = 0;
			}
			arr[i] = index;
			indexCount++;
		}
	}

	/**
	 * Sorts the given array using MSD radix sort. 
	 */
	public static void MSDRadixSort(int[] arr) {

		int maxDigit = mostDigitsIn(arr) - 1;
		MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length);
	}
	
	public static ArrayList<Integer> digits(int num) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		while(num != 0) {
			int val = num % 10;
			digits.add(val);
			num /= 10;
		}
		return digits;
	}

	/**
	 * Radix sorts the input array only between the indices start and end. Only
	 * considers digits from the input digit on down. This method is recursive.
	 */
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit, int start, int end) {
		if(start == end || digit == 0) return;
	
		int[] list = countingSortByDigitInBounds(arr, digit, start, end);
		MSDRadixSortFromDigitInBounds(arr, digit - 1, start, start + list[0]);
		for(int i = 1; i < list.length; i++) {
			MSDRadixSortFromDigitInBounds(arr, digit - 1, start + list[i - 1], start + list[i]);
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
		ArrayList<Integer>[] nums = new ArrayList[10];
		int[] bounds = new int[10];
		for(int i = 0; i < nums.length; i++) {
			nums[i] = new ArrayList<Integer>();
		}
		for(int i = start; i < end; i++) {
			int num = arr[i];
			String numStr = String.valueOf(num);
			int numDigit = 0;
			if(numStr.length() > digit) {
				numDigit = Integer.valueOf(String.valueOf(numStr.charAt(numStr.length() - 1 - digit)));
			}
			nums[numDigit].add(num);
		}
		int total = 0;
		for(int i = 0; i < nums.length; i++) {
			ArrayList<Integer> list = nums[i];
			for(int n : list) {
				arr[start] = n;
				start++;
			}
			int size = nums[i].size();
			total += size;
			bounds[i] = total;
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

	public static class Test implements Comparable<Test> {
		
		public int i, val;

		
		public Test(int i, int val) {
			this.i = i;
			this.val = val;
		}
		@Override
		public int compareTo(Test t2) {
			if(t2.val <= val) {
				return 1;
			} else if(t2.val == val) {
				return 0;
			}
			return -1;
		}
		
		
		
		
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
			arr2[i] = randomInt();
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
