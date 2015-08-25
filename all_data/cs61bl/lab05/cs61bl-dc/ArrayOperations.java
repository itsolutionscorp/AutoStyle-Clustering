public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		values[pos] = 0;
		for (int i = pos; i < values.length - 1; i++)
		{
			int temp = values[i];
			values[i] = values[i + 1];
			values[i + 1] = temp;
		}
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		int temp = values[pos];
		values[pos] = newInt;
		for (int i = pos + 1; i < values.length; i++)
		{
			int temp2 = values[i];
			values[i] = temp;
			temp = temp2;
		}
	}
	
	public static int[] zip(int[] array1, int[] array2)
	{
		int[] result = new int[array1.length * 2];
		for (int i = 0; i < array1.length; i++)
		{
			result[i * 2] = array1[i];
			result[i * 2 + 1] = array2[i];
		}
		return result;
	}
}
