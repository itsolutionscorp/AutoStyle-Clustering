public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for (int i = 0; i < values.length; i++)
		{
			if (i < pos)
			{
				continue;
			}
			else if (i==values.length -1)
			{
				values[i] = 0;
			}
			else
			{
				values[i] = values[i+1];
			}
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
		int temp = 0;
		int temp2 = 0;
		for (int i = 0; i < values.length; i++)
		{
			if (i < pos)
			{
				continue;
			}
			else if (i == pos)
			{
				temp = values[i];
				values[i] = newInt;
			}
			else
			{
				temp2 = values[i];
				values[i] = temp;
				temp = temp2;
			}
		}
	}
	
	public static int[] zip (int[] array1, int[] array2)
	{
		int[] result = new int[(array1.length)*2];
		int count = 0;
		for(int i = 0; i<result.length; i+=2)
		{
			result[i] = array1[count];
			result[i+1] = array2[count];
			count++;
		}
		return result;
	}
}
