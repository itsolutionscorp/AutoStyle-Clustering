public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int i;
		for(i=pos;i<values.length-pos+1;i++){
		if(i<values.length-1)values[i]=values[i+1];
	

		}
		values[values.length-1]=0;// YOUR CODE HERE
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int i;
		for(i=values.length-1;i>pos;i--)
		{
		if(i>pos)
			values[i]=values[i-1];

		}
          values[pos]=newInt;
	}
public static int[] zip(int[] array1,int[] array2 ){
	int i;
	int[] result=new int [2*array1.length] ;
	for(i=0;i<2*array1.length;i++){
if(i%2==0){result[i]=array1[i/2];
}

	if(i%2==1){
	
			result[i]=array2[i/2];
		
		}		
	}
	return result;
}
}