public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		
		for (int i =pos;i<values.length-1;i++){
			values[i]=values[i+1];	
		}
		values[values.length-1]=0;

	}
	public static int[] zip(int array1[], int array2[]){
		int length = array1.length + array2.length;
		int [] result = new int [length];
		int counter1=0;
		int counter2=0;
		for (int i =0; i<length; i++){
				if (i%2==0){
					result[i]=array1[counter1];
					counter1++;
				}
				else{
					result[i]=array2[counter2];
					counter2++;
				}
			}
		return result;
		
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i =values.length-1;i>pos;i--){
			values[i]=values[i-1];	
		}
		values[pos]=newInt;
	}
}
