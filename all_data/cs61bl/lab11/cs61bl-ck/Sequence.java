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
//
//        for (DListNode current = myHead.myNext;
//                 current != myHead;
//                 current = current.myNext) {
//            result = result + current.myItem.toString() + "    ";
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
		// TODO Your code here
		// int count = 0;
		// DListNode temp = myHead;
		// while (count < mySize) {
		// if (count == deletePos) {
		// DListNode pre = temp.myPrev;
		// DListNode next = temp.myNext;
		// next.myPrev = pre;
		// pre.myNext = next;
		// }
		// temp = temp.myNext;
		// count += 1;
		// // System.out.println(count);
		// // System.out.println(mySize);
		// }
		int count = 0;
		for (DListNode d = myHead; d != null; d = d.myNext) {
			if (count == deletePos) {
				if (count == 0) {
					if (d.myNext == d.myPrev) {
						myHead = null;
						mySize -= 1;
						return;
					}
					myHead = d.myNext;
					d.myPrev.myNext = d.myNext;
					mySize -= 1;
					return;
				}
				d.myPrev.myNext = d.myNext;
				d.myNext.myPrev = d.myPrev;
				mySize -= 1;
				return;
			}
			count++;
		}
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
    	DListNode temp;
    	boolean returnedfirstitem;
        // Initialize an iterator object.
        public SequenceIterator() {
            // TODO Your code here
        	temp = myHead;
        	returnedfirstitem = false;
        }
        
        // Return true when there are more ring elements to iterate over;
        // return false otherwise.
        public boolean hasNext() {
            // TODO Your code here
        	if (myHead == null) {
        		return false;
        	}
            if (returnedfirstitem && temp == myHead) {
            	return false;
            }
            return true;
        }
        
        // Return the next list element. 
        // Precondition: hasNext
        public Object next() {
            // TODO Your code here
        	returnedfirstitem = true;
        	DListNode valToReturn = temp;
        	temp = temp.myNext;
            return valToReturn.myItem;
        }

        // Removes the most recent item returned by next
        // Preconditon: next has been called at least once
        public void remove(){
            // TODO Your code here
        	DListNode pre = temp.myPrev;
        	temp.myPrev = temp.myPrev.myPrev;
        	pre = null;
        	temp.myPrev.myNext = temp;
        	mySize -= 1;
        }
        
        // TODO Your code here
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
//        Sequence seq = new Sequence();
//        seq.addToSequence("a");
//        System.out.println(seq);
//        seq.addToSequence("b");
//        System.out.println(seq);
//        seq.addToSequence("c");
//        System.out.println(seq);
//        seq.addToSequence("d");
//        System.out.println(seq);
//        seq.delete(3);
//        System.out.println(seq + "; should be [a b c]");
//        seq.delete(0);
//        System.out.println(seq + "; should be [b c]");
//        seq.delete(1);
//        System.out.println(seq + "; should be [b]");
//        seq.delete(0);
//        System.out.println(seq + "; should be [ ]");
//        
//        Iterator iter = seq.iterator();
//        System.out.println(iter.hasNext());
//        System.out.println(iter.next());
//        System.out.println(iter.next());
//        iter.remove();
//        System.out.println(seq.toString());
    }

}
