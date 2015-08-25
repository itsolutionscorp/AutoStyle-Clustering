import java.util.Iterator;
import java.lang.Iterable;

public class Sequence<G> implements Iterable<G> {

    // instance variables
    protected G[] myValues;   // sequence elements
    int myCount;
    protected int maxLength;

    // number of array cells used by sequence

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
    	myValues = (G[]) new Object[capacity];
    	maxLength = capacity;
    	myCount = 0;
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add(G toBeAdded) {
    	if(myCount == maxLength){
    		System.err.println("Error: Sequence is full");
    		System.exit(1);
    	}
    	myValues[myCount] = toBeAdded;
    	myCount++;
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(G toInsert, int insertPos) {
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    // other methods go here
    public boolean isEmpty(){
    	return myCount == 0;
    }
    public int size(){
    	return myCount;
    }
    public G elementAt(int pos){
    	if (pos >= myCount){
    		System.err.println("Error: No Value at Specified Position");
    		System.exit(1);
    	}
    	return myValues[pos];
    }
    public String toString(){
    	String returnString = new String();
    	for(G element: myValues){
    		returnString += element.toString() + " ";
    	}
    	return returnString;
    }
    public G remove(int value){
    	G returnG = myValues[value];
    	for(int x=value;x<maxLength-1;x++){
    		myValues[x] = myValues[x+1];
    	}
    	myCount--;
    	return returnG;
    }

    public boolean contains(G k) {
    	for (int elm = 0; elm < myCount; elm++) {
    		if (myValues[elm] == k) {return true;}
    	}
    	return false;
    }

    public Iterator<G> iterator() {
        return new IterSequence();
    }


    private class IterSequence implements Iterator<G> {

        private int index;

        private IterSequence() {
            index = 0;
        }

        public boolean hasNext() {
            return index < myCount;
        }

        public G next() {
            G valToReturn = myValues[index];
            index++;
            return valToReturn;
        }

    }
    
}
