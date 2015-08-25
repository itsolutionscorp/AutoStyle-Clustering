public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for (int count = 0, save; count < values.length; count ++){
			if (count < pos) {
				continue;
				}
			else if (count == values.length - 1) {
				save = 0;
				values[count] = 0;
				}
			else if (count == pos){
				save = values[count + 1];
				values[count] = save;
				pos++;
				
				}

		}
	
for (int c = 0; c < values.length; c++){ 
			System.out.print(values[c] + " ");
			}
System.out.println();
}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for (int count = 0, save = values[pos], temp= 0; count < values.length; count++){
			
			if (count < pos) {
				continue;
				}
			else if (count == pos) {
				values[count] = newInt; 
				}
				else if (count == values.length - 1){
					values[count] = save;

					}
				else{
					temp = values[count];
					values[count] = save;
					save = temp;

					
				}
		}


for (int c=0; c < values.length; c++){ 
		System.out.print(values[c] + " ");
		}
System.out.println();
	}
	
	public static int[] zip(int[] array1, int[] array2){
		int length = array1.length + array2.length; 
		int[] result = new int[length];
		for(int c = 0, i = 0; i < result.length - 1; c++, i += 2){
			result[i] = array1[c];
			result[i + 1] = array2[c];
		}
	
		return result;
		
	}
	
}
		
