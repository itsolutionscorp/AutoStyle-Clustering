import java.util.Iterator;

public class Sequence<T> implements Iterable<T> {

    // instance variables
    protected T [] myValues;   // sequence elements
    int myCount;                // number of array cells used by sequence
    int index;

    // constructor
    // capacity: actual size of the array or the (temporary) maximum
    // number of elements it can hold
    public Sequence(int capacity) {
        myValues = (T[]) new Object[capacity];
        myCount = 0;
        for (int i = 0; i < capacity; i++){
            myValues[i] = null;
        }
    }

    // Insert toInsert into the sequence at position insertPos,
    // shifting the later elements in the sequence over to make room
    // for the new element.
    // Assumptions: The array isn't full, i.e. myCount < myValues.length
    // Also, insertPos is between 0 and myCount, inclusive.
    public void insert(T toInsert, int insertPos) {
        if (myCount == myValues.length ) {
            System.err.println("the set is full!");
            System.exit(1);
        }

        if(insertPos > myCount || insertPos < 0){
            System.err.println("invalid position!");
            System.exit(1);
        }

        if(insertPos == myCount){
            add(toInsert);
            return;
        }
        for (int k = myCount; k > insertPos; k--) {
            myValues[k] = myValues[k-1];
        }
        myValues[insertPos] = toInsert;
        myCount++;
    }

    public T remove (int pos) {
        if (pos < 0 || pos >= myCount) {
            System.err.println("input is invalid!");
            System.exit(1);
        }
        myCount --;
        T result = myValues[pos];
        for (int i = pos; i< myCount; i++){
            myValues[i] = myValues[i+1];
        }
        myValues[myCount] = null;
        return result;
    }

    public boolean isEmpty(){
        if (myCount == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return myCount;
    }

    public T elementAt(int pos){
        if (pos < 0 || pos >= myCount){
            System.err.println("input is invalid");
            System.exit(1);
        }
        return myValues[pos];
    }

    // Add the argument to the sequence by placing it in the first
    // unused spot in the array and incrementing the count.
    // Assume that the sequence isn't full.
    public void add (T toBeAdded) {
        if (myCount == myValues.length){
            System.err.println("the set is full!");
            System.exit(1);
        }
        myValues[myCount] = toBeAdded;
        myCount ++;
    }

    public boolean contains(T k){
        int i = 0;
        while (i < myCount) {
            if (myValues[i].equals(k)){
                return true;
            }
            i ++;
        }
        return false;
    }
    public String toString() {
        if (myCount == 0){
            return "";
        }
        String result =""  + myValues[0];
        if (myCount == 1){
            return result;
        }
        for (int i = 1; i < myCount; i++){
            result = result + " " + myValues[i];
        }
        return result;
    }

    public Iterator<T> iterator() {
        return new SeqIterator<T>();
    }

    private class SeqIterator<E> implements Iterator<E> {

        public SeqIterator () {
            index = -1;
        }

        public boolean hasNext() {
            return index + 1 < myCount;

        }


        public E next() {
            index ++;
            return (E) myValues[index];
        }


        public void remove() {
            return;
        }

    }


}


