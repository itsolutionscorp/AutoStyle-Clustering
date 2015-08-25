import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		int size = arr.length;
		int jindex = 0;
		int[] temp = new int[10];
		int[] result = new int[size];
		for(int num:arr){
			temp[num]++;
		}
        for(int index = 0 ;index <10;index++){
        	for(int j = 0;j<temp[index];j++){
        		arr[jindex] = index;
        		jindex++;
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
		// TODO your code here! Make sure to use the countingSortByDigitInBounds
		// helper method, given below.
		if(digit < 0){
			return ;
		}
		int[] index;
		index = countingSortByDigitInBounds(arr,digit,start,end);
		for(int i = 0;i<index.length;i++){
			if(i == 9){
				MSDRadixSortFromDigitInBounds(arr,digit-1,index[9],end);
			}else{
			MSDRadixSortFromDigitInBounds(arr,digit-1,index[i],index[i+1]);}
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
		int index = 1;
		int currdigit;
		int currindex = start;
		int[] currArray = new int[10];
		for(int i =0;i< digit;i++){
			index *= 10;
		}
		ArrayList<PriorityQueue> record = new ArrayList<PriorityQueue>();
		for(int j =0;j<10;j++){
			record.add(new PriorityQueue());
		}
		for(int i = start;i<end;i++){
			if(arr[i]<index){
				record.get(0).offer(arr[i]);
			}else{
			currdigit = helper(digit+1,arr[i]);
			record.get(currdigit).offer(arr[i]);}
		}
		currArray[0] = start;
		currdigit = 0;
		while(currdigit<=9){
			currindex += record.get(currdigit).size();
			currArray[currdigit] = currindex;
			currdigit += 1;
		}
		currdigit = 0;
		currindex = start;
		while(currdigit<= 9){
			while(!record.get(currdigit).isEmpty()){
				arr[currindex]=(int)record.get(currdigit).poll();
				currindex++;
			}
			currdigit++;
		}
		return currArray;
	    
	}
    public static int helper(int digit,int num){
    	int index = num;
    	for(int i = 0;i<digit-1;i++)
    	{  index /= 10;
    		}
    	return index%10;
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
