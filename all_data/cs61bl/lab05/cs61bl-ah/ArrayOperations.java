public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		for(int x=pos; x < values.length; x++){
			if (x != values.length - 1){
				values[x]=values[x+1];
			}
			else {
				values[values.length-1]=0;
			}
		}
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert(int[]values,int pos,int newInt){if(pos<0||pos>=values.length){return;}for(int q=values.length-1;q>pos;q--){values[q]=values[q-1];}values[pos]=newInt;}

	public static int[] zip(int[]x,int[]y){
		int[]result=new int[x.length*2];
		int z = 0;
		for(int q=0;q<x.length;q++){
			result[z]=x[q];
			result[z+1]=y[q];
			z+=2;
			}
		return result;
		} 
}

