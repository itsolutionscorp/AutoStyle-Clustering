import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] counts = new int[10];
		for (int i: arr) {
			counts[i] += 1;
		}
		int[] indexes = new int[10];
		indexes[0] = 0;
		indexes[1] = counts[0];
		indexes[2] = indexes[1] + counts[1];
		indexes[3] = indexes[2] + counts[2];
		indexes[4] = indexes[3] + counts[3];
		indexes[5] = indexes[4] + counts[4];
		indexes[6] = indexes[5] + counts[5];
		indexes[7] = indexes[6] + counts[6];
		indexes[8] = indexes[7] + counts[7];
		indexes[9] = indexes[8] + counts[8];
		
		int[] lol = new int[arr.length];
		for (int i: arr) {
			lol[indexes[i]] = i;
			indexes[i] += 1;
		}
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = lol[i];
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
		if (digit <0){
			return;
		}
		int[] indexes=countingSortByDigitInBounds(arr,digit,start,end);
		for(int x=0; x<indexes.length-1; x++){
			MSDRadixSortFromDigitInBounds(arr, digit-1, indexes[x], indexes[x+1]);
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
		int[] counts = new int[10];
		for (int x=start; x<end; x++) {
			if (digit==0){
				counts[arr[x]%10] += 1;
			}
			else{
			counts[(arr[x]%(int)(Math.pow(10,digit+1))/((int)(Math.pow(10,digit))))] += 1;
		}}
		int[] indexes = new int[10];
		indexes[0] = start;
		indexes[1] = indexes[0] + counts[0];
		indexes[2] = indexes[1] + counts[1];
		indexes[3] = indexes[2] + counts[2];
		indexes[4] = indexes[3] + counts[3];
		indexes[5] = indexes[4] + counts[4];
		indexes[6] = indexes[5] + counts[5];
		indexes[7] = indexes[6] + counts[6];
		indexes[8] = indexes[7] + counts[7];
		indexes[9] = indexes[8] + counts[8];
		
		int[] lol = new int[arr.length];
		int[] toReturn= new int[11];
		for(int x=0; x<indexes.length; x++){
			toReturn[x]=indexes[x];
		}
		toReturn[10]=toReturn[9]+counts[9];
		
		if (digit==0){
		for (int x= start; x<end; x++) {
			lol[indexes[arr[x]%10]] = arr[x];
			indexes[arr[x]%10] += 1;
		}
		for (int x = start; x < end; x++) {
			arr[x] = lol[x];
		}
		}
		else{
			for (int x= start; x<end; x++) {
				lol[indexes[(arr[x]%(int)(Math.pow(10,digit+1))/((int)(Math.pow(10,digit))))]] = arr[x];
				indexes[(arr[x]%(int)(Math.pow(10,digit+1))/((int)(Math.pow(10,digit))))] += 1;
			}
			for (int x = start; x < end; x++) {
				arr[x] = lol[x];
			}
		}
		return toReturn;
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
