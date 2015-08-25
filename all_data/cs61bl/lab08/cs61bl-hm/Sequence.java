import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Sequence<T> implements Iterable<T>{
	private T[] myValues;
	int myCount;
	
	public Sequence(int capacity) {
		myValues = (T[]) new Object[capacity]; 
	}
	
	public void add(T toBeAdded) {
		if(myCount==myValues.length){
			System.err.println("Array is already full");
			System.exit(1);
		}
		myValues[myCount] = toBeAdded;
		myCount++;
	}
	
	public void insert(T toInsert, int insertPos) {
		if (insertPos < 0 || insertPos > myCount){
			System.err.println("Position not valid");
			System.exit(1);
		}
		for (int k = myCount; k > insertPos; k--) {
			myValues[k] = myValues[k - 1];
		}
		myValues[insertPos] = toInsert;
		myCount++;
	}
	
	public boolean isEmpty() {
		return myCount == 0;
	}

	public int size() {
		return myCount;
	}
	
	public T elementAt(int pos){
		if(pos>=0 && pos<myCount){
			return myValues[pos];
		}else{
			System.err.println("Position not valid");
			System.exit(1);
			return null;
		}
	}
	
	public String toString(){
		String returnString ="";
		for(int i=0;i<myCount;i++){
			returnString = returnString + myValues[i].toString();
			if(i!=myCount-1){
				returnString = returnString + " ";
			}
		}
		return returnString;
	}
	
	public void remove(int pos){
		for(int i=pos;i<myCount;i++){
			myValues[i]=myValues[i+1];
		}
		myCount--;
	}
	
	public boolean contains(T k){
		for(int i=0;i<myCount;i++){
			if(myValues[i]==k)
				return true;
		}
		return false;
	}
	
	public Iterator<T> iterator(){
		return new IterateSequence();
	}
	
	private class IterateSequence implements Iterator<T>{
		
		private int position;
		
		public IterateSequence() {
			position = 0;
		}
		
		public boolean hasNext(){
			return position < myCount;
		}
		public T next(){
			T retValue = myValues[position];
			position++;
			return retValue;
		}
	}
}
