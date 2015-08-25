import java.util.Arrays;
 
public class DistributionSorts {
    private static int maxDigit;
    /**
     * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
     */
    public static void countingSort(int[] arr) {
        // TODO your code here!
        int [] counts = new int [10];
        int [] sorted = new int [arr.length];
        //Increment the counts of the corresponding indexes in 
        for(int j = 0; j < arr.length; j++){
            counts[arr[j]]++;
        }
        //Add up the consecutive values in counts to convert it to starts
        for(int i = 1; i < counts.length; i++){
            counts[i]+=counts[i-1];
        }
         
        for(int i=0;i<arr.length;i++){
            counts[arr[i]] = counts[arr[i]]-1;
            int index1 = counts[arr[i]];
            sorted[index1] = arr[i];
        }for(int i=0;i<arr.length;i++){
            arr[i]=sorted[i];
        }
    }
     
    public static int max (int [] arr){
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max)
                max = arr[i];
        }return max;
    }
 
    /**
     * Sorts the given array using MSD radix sort. 
     */
    public static void MSDRadixSort(int[] arr) {
        maxDigit = mostDigitsIn(arr) - 1;
        System.out.println(maxDigit);
        MSDRadixSortFromDigitInBounds(arr, maxDigit, 0, arr.length);
    }
 
    /**
     * Radix sorts the input array only between the indices start and end. Only
     * considers digits from the input digit on down. This method is recursive.
     */
    public static void MSDRadixSortFromDigitInBounds(int[] arr, int digit,int start, int end) {
        // TODO your code here! Make sure to use the countingSortByDigitInBounds
        // helper method, given below.
         
        //Base case 
         
        if (digit==-1 || end <= start+1) 
            return;
        int[] myCounts = countingSortByDigitInBounds(arr, digit, start, end);
        if (myCounts[0] > 0) 
            MSDRadixSortFromDigitInBounds(arr, digit-1, 0, myCounts[0]);
        for (int i=1;i<10;i++) {
            if(myCounts[i-1]!=myCounts[i])
                MSDRadixSortFromDigitInBounds(arr, digit-1, start + myCounts[i-1], start + myCounts[i]);
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
    private static int[] countingSortByDigitInBounds(int[] arr, int digit,int start, int end) {
        // TODO your code here!
        int[] sorted = new int[arr.length];//sorted portion of array between 
        int[] starts = new int[10];//starts array to return
        int[] buckets = new int[10];
        for (int i=start; i<end; i++) {
            int num = arr[i];
            int importantDigit = (int) ((num % Math.pow(10, digit+1)) / Math.pow(10, digit)) ;
            buckets[importantDigit] += 1;
        }
         
        starts[0] = buckets[0];
        for (int i=1; i<buckets.length; i++) {
            buckets[i] = buckets[i-1] + buckets[i];
            starts[i] = buckets[i];
        }
        for (int j=end-1; j>=start; j--) {
            int num = arr[j];
            int realNum = (int) ((num % Math.pow(10, digit+1)) /  Math.pow(10, digit));
            buckets[realNum] = buckets[realNum] -1;
            int index = buckets[realNum]+start;
            sorted[index] = num;
        }
        for (int i=start; i<end; i++)
            arr[i] = sorted[i];
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