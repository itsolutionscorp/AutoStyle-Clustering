public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			System.out.println("Wrong input! ");
			return;
		}
		int p=pos+1;
		for(int i=p;i<values.length;i++){
			values[i-1] = values[i];
		}
		values[values.length - 1] = 0;
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {			
			System.out.println("Wrong input! ");
			return;
		}
		int p=values.length-1;
		for(int i=p;i>=pos;i--){
			if(i==pos){
				values[i]=newInt;
				break;
			}
			values[i]=values[i-1];
			
		}	
	}
	public static int[] zip(int[] array1, int[] array2) {
		int [] tempArray= new int[2*array1.length];
		for(int i=0,j=0,k=1;i<array1.length;i++,j+=2,k+=2){			
			tempArray[j]=array1[i];
			tempArray[k]=array2[i];			
		}
		return tempArray;
				
		
	}
}
