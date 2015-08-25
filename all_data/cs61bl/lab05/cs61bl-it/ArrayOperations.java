public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {


		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		if (pos == values.length - 1) {
			values[values.length - 1] = 0; 
		}
		else {
			int lastValue = values[values.length - 1]; 

			for (int i = pos; i < values.length; i ++){
				if (i == values.length - 2) {
					break;
				}
				else {
					values[i] = values[i+1]; 
				}
			}
			values[values.length - 2] = lastValue;
			values[values.length - 1] = 0; 
		}

	}

	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		int[] newArray = new int[values.length];
		for (int i = 0; i < pos; i++){
			newArray[i] = values[i];
			
		}
		newArray[pos] = newInt;
		
		for (int i = pos + 1; i < values.length; i++){
			newArray[i] = values[i - 1]; 
		}
		for(int i = 0; i < values.length; i++){
			values[i] = newArray[i];
		}
		

	}
	public static int[] zip (int[] array1, int[] array2){

		int arraySize = 2 * array1.length;
		int[] newArray = new int[arraySize];

		int array1Counter  = 0; 
		int array2Counter = 0; 

		for (int i = 0; i < arraySize; i++ ){
			if(i % 2 == 0){
				newArray[i] = array1[array1Counter]; 
				array1Counter ++ ;

			}else{
				newArray[i] = array2[array2Counter];
				array2Counter ++;
			}
		}
		for (int i = 0; i < newArray.length; i++){
			System.out.println(newArray[i]);
		}
		return newArray;

	}
//	public static void main (String [] args){
//		int [] ar = {1,2,3,4,5};
//		insert(ar,0,0);
//		
//		
//	}
	}




