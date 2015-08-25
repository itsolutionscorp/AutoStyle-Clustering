import java.util.*;

/** 
 * OVERVIEW:
 * This Sequence class implements a circular sequence in which
 * the item after the last is the first. 
 */

public class Sequence implements Iterable {

    // Initialize an empty sequence.
    public Sequence() {
        mySize = 0;
        myHead = null;
    }

    // Return true if this list is empty, false otherwise.                                                               
    public boolean isEmpty() {
        return (mySize == 0);
    }
        
    // Return the number of elements in this list. 
    public int size() {
        return mySize;
    }

        
    // Return a String representation of this list. 
//    public String toString() {
//        if (isEmpty()) {
//            return "[  ]";
//        }
//        String result = "[  " + myHead.myItem.toString() + "  ";
//        for (DListNode current = myHead.myNext;
//                 current != myHead;
//                 current = current.myNext) {
//            result = result + current.myItem.toString() + "  ";
//        }
//        return result + "]";
//    }
    
    // Add the given object to the end of this list.
    public void addToSequence(Object toAdd) {
        if (isEmpty()) {
            myHead = new DListNode (toAdd);
        } else {
            // Insert toAdd between myHead and myHead.myPrev.
            DListNode newNode = new DListNode (toAdd, myHead.myPrev, myHead);
            // Link the new node into the list.
            myHead.myPrev.myNext = newNode;
            myHead.myPrev = newNode;
        }
        mySize++;
    }
    
    // Delete the object at the given position in the list.
    // Precondition: the given position is at least 0 and less than mySize.
    public void delete(int deletePos) {
        if (mySize == 1) {
        		myHead = null;
        		mySize--;
        		return;
        }
    		DListNode left, right, interest;
        DListNode p = myHead;
        int count = 0;
        while(count < deletePos) {
        		p = p.myNext;
        		count++;
        }
        interest = p;
        left = interest.myPrev;
        right = interest.myNext;
        left.myNext = right;
        right.myPrev = left;
        if (deletePos == 0) {
        		myHead = right;
        }
        mySize--;
        
    }
    
    /* TODO You substitute this for the existing version after supplying
         code for the Iterator methods.*/
    public String toString() {
        Iterator iter = iterator();
        String result = "[  ";
        while (iter.hasNext()) {
            result = result + iter.next().toString() + "  ";
        }
        result = result + "]";
        return result;
    } 
    
    
    // Return an initialized iteration of the ring elements.  
    // This list must not be modified while the iteration is in use, except 
    // by using the Iterator.remove method
    public Iterator iterator() {
        return new SequenceIterator();
    }
    
    private class SequenceIterator implements Iterator {
    
        /* This class provides an iteration of the elements
         * of the sequence. */
    		private DListNode reference;
    		private int uniqueSize;
    		private int index;

        // Initialize an iterator object.
        public SequenceIterator() {
        		reference = myHead;
        		index = 0;
        		uniqueSize = helperSize();
        }
        
        // Return true when there are more ring elements to iterate over;
        // return false otherwise.
        public boolean hasNext() {
            return (index < uniqueSize);
        }
        
        // Return the next list element. 
        // Precondition: hasNext
        public Object next() {
            Object item = reference.myItem;
            reference = reference.myNext;
            index ++;
            return item;
        }

        // Removes the most recent item returned by next
        // Preconditon: next has been called at least once
        public void remove(){
        		DListNode interest = reference.myPrev;
        		if (uniqueSize == 1) {
            		myHead = null;
            		//reference = null;
            		index--;
            		mySize--;
            		uniqueSize--;
            		return;
            }
        		DListNode left, right;
        		left = interest.myPrev;
            right = interest.myNext;
            left.myNext = right;
            right.myPrev = left;
            index--;
            mySize--;
            uniqueSize--;
            if (index == 0) {
        			myHead = right;
            }
        }
        
        public int helperSize(){
        		if (reference == null) {
        			return 0;
        		}
        		DListNode p = reference.myNext;
        		int count = 1;
        		while (p != reference) {
        			count ++;
        			p = p.myNext;
        		}
        		return count;
        }
    }
            
    private class DListNode {

        /* DListNode is a class used internally by the Sequence class.
         * Each node in a Sequence is represented as a DListNode (Doubly linked List),
         * with an item and references to the previous and next node in the list.
         * The list is circular, so the last and first nodes contain references
         * to each other. */
        
        public Object myItem;
        public DListNode myPrev;
        public DListNode myNext;

        // Initialize a DListNode with myItem obj and
        // myPrev and myNext both pointing to the new node.
        // (This produces a 1-element circular list.)    */
        public DListNode(Object obj) {
            myItem = obj;
            myPrev = this;
            myNext = this;
        }

        // Initialize a DListNode with myItem obj
        // and the given values for myPrev and myNext.
        public DListNode(Object obj, DListNode prev, DListNode next) {
            myItem = obj;
            myPrev = prev;
            myNext = next;
        }
    }

    // Invariant:
    // (myHead == null && mySize == 0)
    // || (myHead != null 
    //       && the number of nodes in the list pointed to by myHead == mySize
    //       && all the nodes are properly linked)

    private int mySize;         // number of elements in the sequence
    private DListNode myHead;   // pointer to the first element of the sequence
    
    public static void main(String[] args) {
        Sequence seq = new Sequence();
        seq.addToSequence("a");
        System.out.println(seq);
        seq.addToSequence("b");
        System.out.println(seq);
        seq.addToSequence("c");
        System.out.println(seq);
        seq.addToSequence("d");
        System.out.println(seq);
        seq.delete(3);
        System.out.println(seq + "; should be [a b c]");
        seq.delete(0);
        System.out.println(seq + "; should be [b c]");
        seq.delete(1);
        System.out.println(seq + "; should be [b]");
        seq.delete(0);
        System.out.println(seq + "; should be [ ]");
    }

}
