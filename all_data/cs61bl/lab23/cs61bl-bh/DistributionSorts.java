import java.util.Arrays;

public class DistributionSorts {

    /**
     * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
     */
    public static void countingSort(int[] arr) {
        int[] counts = new int[10];
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i]]++;
        }
        int[] starts = new int[10];
        int index = 0;
        for (int i = 0; i < 10; i++) {
            starts[i] = index;
            index += counts[i];
        }
        for (int i = 0; i < 9; i++) {
            while (starts[i] < starts[i+1]) {
                arr[starts[i]] = i;
                starts[i]++;
            }
        }
        while (starts[9] < arr.length) {
            arr[starts[9]] = 9;
            starts[9]++;
        }
    }

    /**
     * Sorts the given array using MSD radix sort. 
     */
    public static void MSDRadixSort(int[] arr) {
        int maxDigit = mostDigitsIn(arr);
        MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length);
    }

    /**
     * Radix sorts the input array only between the indices start and end. Only
     * considers digits from the input digit on down. This method is recursive.
     */
    public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit, int start, int end) {
        int[] starts = countingSortByDigitInBounds(arr, digit, start, end);
        if (starts != null) {
            for (int i = 0; i < 9;) {
                MSDRadixSortFromDigitInBounds(arr, digit-1, starts[i], starts[++i]);
            }
            MSDRadixSortFromDigitInBounds(arr, digit-1, starts[9], end);
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
        if (digit == 0 || start == end) {
            return null;
        }
        int[] counts = new int[10];
        for (int i = start; i < end; i++) {
            counts[ithDigit(arr[i], digit)]++;
        }
        int[] starts = new int[10];
        int[] rtn = new int[10];
        for (int i = 0, index = start; i < 10; i++) {
            starts[i] = index;
            rtn[i] = index;
            index += counts[i];
        }
        for (int i = 0; i < 9; i++) {
        	while (starts[i] < starts[i+1]) {
        		for (int j = start; j < end; j++) {
            		if (ithDigit(arr[j], digit) == i) {
            			int temp = arr[starts[i]];
            			arr[starts[i]] = arr[j];
            			arr[j] = temp;
            			starts[i]++;
            		}
            	}
            }
        }
        for (int j = start; j < end; j++) {
        	if (ithDigit(arr[j], digit) == 9) {
    			int temp = arr[starts[9]];
    			arr[starts[9]] = arr[j];
    			arr[j] = temp;
    			starts[9]++;
        	}
        }
        return rtn;
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
    
    private static int ithDigit(int number, int digit) {
        for (int i = digit-1; i > 0; i--) {
            number = number / 10;
        }
        return number % 10;
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


