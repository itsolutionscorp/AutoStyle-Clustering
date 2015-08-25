public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static int [] zip (int [] a1, int [] a2)
	{
		int []result = new int [a1.length*2];
		if (a1.length!=a2.length)
		{
			return result;
		}
		else
		{
			for (int i =0; i<a1.length ;i++)
			{
				result[2*i]=a1[i];
				result[2*i+1]=a2[i];
			}
			return result;
		}
	
			
	}
	
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		else
		{
			for (int i =0;i<values.length-pos-1;i++)
			{
				values[i+pos]=values[i+pos+1];
			}
			values[values.length-1]=0;
		}
		// YOUR CODE HERE
	}

	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		else
		{
			
			for(int i=0;i<values.length-pos-1;i++)
			{
				values[values.length-(i+1)]=values[values.length-(i+2)];
			}
			values[pos]=newInt;
		}
		// YOUR CODE HERE
	}
}
