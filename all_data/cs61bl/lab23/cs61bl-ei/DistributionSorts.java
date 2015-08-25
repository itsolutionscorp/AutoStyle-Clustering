import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int[] counts=new int[10];
		for(int i=0; i<arr.length; i++){
			int j=arr[i];
			counts[j]++;	
		}
		int[] sorted=new int[arr.length];
		int j=0;
		for(int i=0; i<=9; i++){
			int h=counts[i];
			while(h>0){
				sorted[j]=i;
				h--;
				j++;
			}
		}
		for (int i=0; i<arr.length; i++){
			arr[i]=sorted[i];
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
	public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit, int start, int end) {
		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		// helper method, given below.
		if (digit<0){
			return;
		}
		int[] something = countingSortByDigitInBounds(arr, digit, start, end);
		for (int i =0; i< something.length; i++){
			if (i==something.length-1){
				MSDRadixSortFromDigitInBounds(arr, digit-1, start+something[i], end);
			} else {
				MSDRadixSortFromDigitInBounds(arr, digit-1, start+something[i], start+something[i+1]);
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
	private static int[] countingSortByDigitInBounds(int[] arr, int digit, int start, int end) {
		// TODO your code here!
		int d = digit;
		int something = 1;
		while (d>0){
			something = something*10;
			d--;
		}
		int[] temp=new int[end-start];
		for(int i=start, j=0; i<end;i++){
			temp[j]=(arr[i]/something) % 10;
			j++;
		}
		countingSort(temp);

		
		int num =1;
		int arrPos = start;
		for (int tempPos = 0; tempPos<temp.length; tempPos++){
			if (tempPos>0&& (temp[tempPos-1]/something)%10 != temp[tempPos]){
				arrPos= start;
				num++;
			}
			while ((arr[arrPos]/something)%10 != temp[tempPos]){
				arrPos++;
			}
			temp[tempPos] = arr[arrPos];
			arrPos++;
		}
		
		int[] startIndex=new int[num];
		int numPos=0;
		for(int i=0;i<temp.length;i++){
			if(i==0 || (temp[i]/something)%10 !=  (temp[i-1]/something)%10){
				startIndex[numPos]=i;
				numPos++;
			}
			arr[i+start]=temp[i];
		}
				
		return startIndex;
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
