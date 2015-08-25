public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		
		if (pos < 0 || pos >= values.length) {
			return;
		} else { 
			for (int i = 0; i < (values.length - 1); i++) {
				if (i < pos){
					values[i] = values[i];
				} else if ((i == pos) || (i > pos)) {
					values[i] = values[i + 1];
					
				}
			}
			
			values[values.length - 1] = 0;
			}
		}
		 
		// YOUR CODE HERE
	
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		int a = 0; 
		int b = 0;
		int c = 0; 
		if (pos < 0 || pos >= values.length) {
			return;
		} else { 
			for (int i = 0; i < (values.length); i++) {
				if (i <= pos){
					values[i] = values[i];
				} else if(i > pos){
					
					if (a == 0) {
						b = values[i];
						values[i] = values[i - 1];
						a = 1;
					}else if (a == 1) { 
						c = values[i]; 
						values[i] = b;
						a = 2;
					}
					else if (a == 2) { 
						b = values[i];
						values[i] = c; 
						a = 1;
					}
				}
			}
			values[pos] = newInt;	

		// YOUR CODE HERE
	}
	}
	public static int[] zip(int[] array1,int[] array2) {
		int firstIndex = 0; 
		int secondIndex = 0;
		int [] result = new int[array1.length + array2.length];
		for (int i = 0; i < (array1.length + array2.length);i++) {
			if (firstIndex == secondIndex) {
				result[i] = array1[firstIndex];
				firstIndex++;
			} else {
				result[i] = array2[secondIndex];
				secondIndex++;
			}
		}
		return result;
	}
}
