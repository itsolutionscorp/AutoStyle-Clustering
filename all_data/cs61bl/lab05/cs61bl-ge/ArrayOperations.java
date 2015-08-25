public class ArrayOperations {

	// Delete the value at the given position in the argument array,
	// shifting all the subsequent elements down, and storing a 0
	// as the last element of the array.
	public static void delete (int[] values, int pos) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		// YOUR CODE HERE
		
			
			for (int i =pos;i < values.length;i++){if (i == values.length-1)
			
			{values[i] = 0;}
			else{values[i]= values[i+1];} 
				
			}
			
	  
	}
	
	// Insert newInt at the given position in the argument array,
	// shifting all the subsequent elements up to make room for it.
	// The last element in the argument array is lost.
	public static void insert (int[] values, int pos, int newInt) {
		if (pos < 0 || pos >= values.length) {
			return;
		}
		for (int i=values.length -1; i>=pos;i--)
		{if(i == pos)
		{values[i]=newInt;}
		else{values[i]=values[i-1];
			}
		
			
		}
		
	}
	public static int[] zip(int[] list1, int[] list2){
		int n = list1.length*2;
		
		int [] result ;
		result = new int[list1.length*2];
		for (int k=0; k<n; k++){if(k%2==0) {result[k] =list1[k/2];}
		      else {result[k] = list2 [(k-1)/2];}
		    	  
		      }
		return result;
		}
		}
	
