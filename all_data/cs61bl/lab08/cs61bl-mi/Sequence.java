import java.util.ArrayList;
import java.util.Iterator;


public class Sequence<T> implements Iterable<T>{

	private ArrayList<T> myValues = new ArrayList<T>();
	int myCount = 0;
	
//	public Sequence(int capacity) {
//		myValues = new ArrayList<T>;
//	}
	
	public SeqIterator iterator() {
		SeqIterator s = new SeqIterator();
		return s;
	}
	private class SeqIterator implements Iterator<T> {
		int positionAt = 0;
		public boolean hasNext() {
			return positionAt < myCount;
		}
		
		public T next() {
			T objToReturn = elementAt(positionAt);
			positionAt++;
			return objToReturn;
		}
	}
	
    public boolean isEmpty() {
    	return myCount == 0;
    }
    
    public int size() {
    	return myCount;
    }
	
    public T elementAt(int pos) {
    	if (pos < myCount) {
    		return myValues.get(pos);
    	}
    	else if (pos > myCount){
    		System.err.println("Value does not exist at that element");
    		System.exit(1);
    		return null; //remove before submit
    	}
    	else {
    		System.err.println("Index is out of range");
    		System.exit(1);
    		return null; //remove before submit
    	}
    }
    
    public void add(T toBeAdded) {
        // YOUR CODE HERE
    	
    	myValues.add(toBeAdded);
    	myCount++;
    	
    }
    
    public void insert(T toInsert, int insertPos) {
        
    	if (insertPos == myCount) {
    		myValues.add(toInsert);
    		myCount++;
    	}
    	else {
    	ArrayList<T> myValues2 = new ArrayList<T>();
    	for (int i = 0; i < insertPos; i++) {
    		myValues2.add(myValues.get(i));
    	}
        T store1 = null;
		T store2 = null;
		for (int i = insertPos; i <myCount + 1; i++) {
			if (i == insertPos) {
				store1 = myValues.get(i);
				store2 = myValues.get(i);
				myValues2.add(toInsert);
			}
			else {
				store2 = myValues.get(i - 1);
				myValues2.add(store1);
				
			}
			store1 = store2;

			}
		myCount++;
		myValues = myValues2;
    }}
    
    public String toString() {
    	String sofar = "";
    	for (int i = 0; i < myCount; i++) {
    		if (i != myCount - 1) {
    			sofar = sofar + myValues.get(i).toString() + " ";
    		}
    		else {
    			sofar = sofar + myValues.get(i).toString();
    		}
    	}
    	return sofar;
    }
    
	public void remove (int pos) {
		if (pos < 0 || pos >= myCount) {
			return;
		}
		// YOUR CODE HERE
		else {
			ArrayList<T> myValues2 = new ArrayList<T>();
			for (int i = 0; i < pos; i++) {
				myValues2.add(myValues.get(i));
			}
			for (int i = pos + 1; i < myCount; i++) {
				myValues2.add(myValues.get(i));
			}
			myValues = myValues2;
			myCount--;
		}
	}
    
//	public static void main(String[] args) {
//		Sequence<String> strings = new Sequence<String>();
//		for (int i = 0; i < 10; i++) {
//			strings.add("hello " + i);
//			//System.out.println(strings.elementAt(i));
//		}
//		//strings.insert("hello " + 10, 10);
//		//System.out.println(strings.elementAt(9));
//		//System.out.println(strings.elementAt(10));
//		//strings.remove(3);
//		//System.out.println(strings.toString());
//		Iterator<String> s = strings.iterator();
//		for (int i = 0; i < 10; i++) {
//			System.out.println(s.hasNext());
//			System.out.println(s.next());
//		}
//		System.out.println(s.hasNext());
//		System.out.println(s.next());
//		
//	}
    
}
