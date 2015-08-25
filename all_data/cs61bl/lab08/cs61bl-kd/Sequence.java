import java.util.Iterator;

public class Sequence <T> implements Iterable<T>{
	
	private T[] myValues; // sequence elements
    int myCount; 
    int index;
    
    public Sequence (int capacity) {
    	myValues = (T[]) new Object[capacity];
    	myCount = 0;
    }
    
    private class SeqIterator<T> implements Iterator<T>{
		// TODO Auto-generated method stub
    	
    	public void initIterator() {
    		index = 0;
    	}
    	
        public T next() {
            T valToReturn = (T) myValues[index];
            index++;
            return valToReturn;
        }

        public boolean hasNext() {
            return index < myCount;
        }
        
        public SeqIterator<T> Iterator() {
        	return new SeqIterator();
        }
	}
    
    
    public boolean isEmpty() {
    	if (myCount == 0) 
    	{
    		return true;
    	} 
    	else 
    	{
    		return false;
    	}
    }
    
    public int size() {
    	return myCount;
    }
    
    public T elementAt(int pos) {
    	if (pos < 0 || pos >= myValues.length) 
    	{
    		System.err.println("Index does not exist");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    
    public void add (T toBeAdded) {
		 if (myCount == myValues.length) 
		 {
			 System.err.println("No more open spots in the array");
			 //System.exit(1);
			 return;
		 }
		 myValues[myCount] = toBeAdded;
		 myCount += 1;
	 }
	
	 public String toString () {
		 String myString = "";
		 for (int i = 0; i < myCount; i += 1)
		 {
			 myString = myString + myValues[i] + " ";
		 }
		 return myString;
	 }

   // Insert toInsert into the sequence at position insertPos,
   // shifting the later elements in the sequence over to make room
   // for the new element.
   // Assumptions: The array isn't full, i.e. myCount < myValues.length
   // Also, insertPos is between 0 and myCount, inclusive.
   public void insert(T toInsert, int insertPos) {
   	if (myValues.length == myCount) {
   		T[] temp_myValues = (T[]) new Object[myCount*2];
   		for (int i = 0; i < myCount; i++) {
   			temp_myValues[i] = (T) myValues[i];
   		}
   		myValues = temp_myValues;
   	}
       for (int k = myCount; k > insertPos; k--) {
           myValues[k] = myValues[k-1];
       }
       myValues[insertPos] = toInsert;
       myCount++;
   }
   
   
   public T remove (int pos) {
		if (pos < 0 || pos >= myCount) {
			System.err.println("out of range");
			System.exit(1);
		}
		T returnValue = myValues[pos];
		for (int i = 0; i < myCount; i++)
		{
			if (i < pos)
			{
				continue;
			}
			else if (i==myCount -1)
			{
				myValues[i] = null;
			}
			else
			{
				myValues[i] = myValues[i+1];
			}
		}
	    myCount--;
		return returnValue;
	}

   // other methods go here
   public boolean contains (T k) {
   	for (int i = 0; i < myCount; i++) {
   		if (k == myValues[i]) 
   		{
   			return true;
   		}
   		else
   		{
   			continue;
   		}
   	}
   	return false;
   }
   
   

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sequence<Integer> sample = new Sequence<Integer>(5);
		sample.add(1);
		sample.add(2);
		sample.add(3);
		sample.add(4);
		sample.add(5);
		System.out.println(sample.toString());
		
		Sequence<String> sample1 = new Sequence<String>(3);
		sample1.add("dog");
		sample1.add("cat");
		sample1.add("bird");
		System.out.println(sample1.toString());
	}

}
