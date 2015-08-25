import java.util.Arrays;

public class DistributionSorts {

	/**
	 * Modify arr to be sorted. Assume arr only contains 0, 1, ..., 9
	 */
	public static void countingSort(int[] arr) {
		// TODO your code here!
		int [] counts = new int[10];
		int [] StartIndex = new int[10];
		int Index;
		for (int i=0; i<10; i++) {
			counts[i] = 0; 
		}
		for  (int a: arr) {
			counts[a] = counts[a]+1;
		}
		
		Index = 0;
		StartIndex[0] = Index;
		
		for (int i=1; i<10; i++) {
			Index = Index + counts[i-1];
			StartIndex[i] = Index;
		}

		int n = 0 ;
		for (int b=0; b<10; b++) {
			while (counts[b]>0) {
				arr[n] = b;
				n=n+1;
				counts[b] = counts[b] - 1;
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
		if (start==end||start>end||start==end-1) {
			return;
		}
		
		for (int place = 0; place <=digit; place++) {
			int[] temp = countingSortByDigitInBounds(arr, place, start, end);
			
			for (int i=start ; i<end; i++) {
				arr[i] = temp[i-start];//finish counting sort of the given section of given digit
			}
		}
		
		
		
//		for (int i=0 ; i<arr.length; i++) {
//			count[(int)(arr[i]/(Math.pow(10, digit)) % 10)+1] += 1; 
//		}
//		
//		for (int k=1 ; k<11; k++) {
//			count[k] += count[k-1]; 
//		}
//		
//		for (int i = 0; i<10; i++) {  
//				int place = digit-1;
//				int[] Output, Next;
//				Output = countingSortByDigitInBounds(arr, digit-1, count[i], count[i+1]);
//				
//				for (int j=count[i]; j<count[i+1]; j++) {
//					arr[j] = Output[j-count[i]];
//				}
//		}
//				
//		for (int i = 0; i<10; i++) {  
//			if (count[i]<=count[i+1]) {
//				continue;
//			}
//			int [] Input = new int[count[i+1]-count[i]];
//			for (int a=count[i]; a< count[i+1]; a++) {
//				Input[a-count[i]] = arr[a];
//			}
//			
//			int[] count2 = new int[11];
//
//			for (int j=0 ; j<Input.length; j++) {
//				count2[(int)(Input[j]/(Math.pow(10, digit-1)) % 10)+1] += 1; 
//			}
//			
//			for (int k=1 ; k<11; k++) {
//				count2[k] += count2[k-1]; 
//			}
//			
//			for (int b = 0; b<10; b++) {  
//				int[] Output;
//				Output = countingSortByDigitInBounds(Input, digit-2, count2[b], count2[b+1]);
//			
//				for (int j=count[i]; j<count[i+1]; j++) {
//					Input[j] = Output[j-count[i]];
//				}
//			}
//			
//			for (int a=count[i]; a< count[i+1]; a++) {
//				arr[a] = Input[a-count[i]];
//			}
			
//*****************************************************************************************************

//				while (place>0) {
//					
//					int[] count2 = new int[11];
//
//					for (int j=0 ; j<Output.length; j++) {
//					count2[(int)(Output[j]/(Math.pow(10, digit)) % 10)+1] += 1; 
//					}
//					
//					for (int k=1 ; k<11; k++) {
//					count2[k] += count2[k-1]; 
//					}
//					
//					int Next[];
//					place--;
//					Next = countingSortByDigitInBounds(Output, place, 0,  );
//				}
				
				
				

				
//					int place = digit-1;
//
//				
//					int[] count2 = new int[11];
//
//					for (int j=0 ; j<arr.length; j++) {
//					count2[(int)(arr[j]/(Math.pow(10, digit)) % 10)+1] += 1; 
//					}
//					
//					for (int k=1 ; k<11; k++) {
//					count2[k] += count2[k-1]; 
//					}
//				
//					for (int z = 0; z<10; z++) {  
//
//						int[] NextOutput;
//						NextOutput = countingSortByDigitInBounds(arr, place-1, count2[z], count2[z+1]);
//						
//						
//						for (int j=count2[z]; j<count2[z+1]; j++) {
//							arr[j] = NextOutput[j-count2[z]];
//						}
//				
//					}
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
		
		if (start == end) {
			return arr;
		}
		
		int [] Output = new int[end-start];
		int [] count = new int[11];
		
		for (int i = start; i < end; i++) {
			count[(int) ((arr[i]/(Math.pow(10, digit))) % 10)+1 ]++;
		}
		
		for (int k = 1; k < 11; k++) {
			count[k] += count[k-1];
			
		}

		
		for (int i = start; i < end; i++) {
			int n;
			n = count[ (int) ((arr[i]/(Math.pow(10, digit))) % 10) ];
			if (Output[n]==0){
				Output[n] =arr[i];
			}else{
				while (Output[n]!=0) {
					n=n+1;
				}
				Output[n] =arr[i];
			}

		}
		
//		for (int i = start; i < end; i++) {
//	        count[ (int) ((arr[i]/(Math.pow(10, digit))) % 10) ]++;
//
//		}
//		
//		int n=0;
//		for (int b=0; b<10; b++) {
//			while (count[b]>0) {
//				Output[n] = b;
//				n=n+1;
//				count[b] = count[b] - 1;
//			}
//			 System.out.println(Arrays.toString(Output));
//		}
//		System.out.println(Arrays.toString(count));
//	    for (int i = end - 1; i >= start; i--) {
//	        Output[count[ (int) ((arr[i]/(Math.pow(10, digit)))%10) ] ] = arr[i];
//	        count[(int) ((arr[i]/(Math.pow(10, digit)))%10) ]--;
//	        System.out.println(Arrays.toString(Output));
//	    }
//		System.out.println(Arrays.toString(count));

		return Output;
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

		int[] arr2 = new int[10];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = randomDigit();
		}
		System.out.println("Original array: " + Arrays.toString(arr2));
		MSDRadixSort(arr2);
		System.out.println("Should be sorted: " + Arrays.toString(arr2));

		int[] arr3 = new int[20];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = randomInt();
		}
		System.out.println("Original array: " + Arrays.toString(arr3));
		MSDRadixSort(arr3);
		System.out.println("Should be sorted: " + Arrays.toString(arr3));
	}
}
