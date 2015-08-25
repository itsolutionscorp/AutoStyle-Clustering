import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int[] counts = new int[10];

		for (int i = 0; i < arr.length;i++){			
			counts[arr[i]]+=1;
		}

		int[] starts = new int[10];

		starts[0] = 0;
		for (int i = 1; i < 10; i++){			
			starts[i] = starts[i-1] + counts[i-1];			
		}

		for (int i = 0; i < 10; i++){
			while(counts[i]!=0){
				arr[starts[i]] = i;
				counts[i]--;
				starts[i]++;
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
		if (digit<0){
			return;
		}
		if (start == end){
			return;
		}
		int[] starts = countingSortByDigitInBounds(arr, digit, start, end);
		
		
		
		for (int i=0; i<10; i++){
			int latest = start;
			for (int j = starts[i]; j<starts[i+1]; j++){
				for (int k = latest; k<end; k++){
					String number = ((Integer)arr[k]).toString();
					int num;
					if (number.length()-digit-1<0){
						num = 0;
					} else {
						num = Character.getNumericValue(number.charAt(number.length()-digit-1));
					}
					if (num==i){
						latest = k;
						break;
					}
				}
				if (j != latest) {
					int temp = arr[j];
					arr[j] = arr[latest];
					arr[latest] = temp;
				}
			}
//			MSDRadixSortFromDigitInBounds(arr, digit-1, starts[i], starts[i+1]);
		}
		
		for (int i=0; i<10; i++){
			MSDRadixSortFromDigitInBounds(arr, digit-1, starts[i], starts[i+1]);
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

		for (int i = start; i < end;i++){
			String number = ((Integer)arr[i]).toString();
			if (number.length()-digit-1<0){
				counts[0]++;
			} else {
				counts[Character.getNumericValue(number.charAt(number.length()-digit-1))]+=1;
			}
		}

		int[] starts = new int[11];

		starts[0] = start;
		for (int i = 1; i < 10; i++){			
			starts[i] = starts[i-1] + counts[i-1];			
		}
		starts[10] = end;
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
