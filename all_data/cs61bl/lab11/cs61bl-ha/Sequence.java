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
    /* ToString version 1
     * 
    public String toString() {
        if (isEmpty()) {
            return "[  ]";
        }
        String result = "[  " + myHead.myItem.toString() + "  ";
        for (DListNode current = myHead.myNext;
                 current != myHead;
                 current = current.myNext) {
            result = result + current.myItem.toString() + "    ";
        }
        return result + "]";
    }
    */
    
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
    	
    	if (deletePos < 0) {
			throw new IllegalArgumentException ("Argument to get must be at least 0.");
		}
		if (deletePos >= size()) {
			throw new IllegalArgumentException ("Argument to get is too large.");
		}
		
		if(size() == 1 && deletePos == 0) {
			myHead = null;
			mySize--;
			return;
		}
		
		if (deletePos == size() - 1) {
			myHead.myPrev.myPrev.myNext = myHead;
			myHead.myPrev = myHead.myPrev.myPrev;
			mySize--;
			return;
		}
		int count = 0;
		for (DListNode p = myHead; p != null; p = p.myNext) {
			if (count == deletePos) {
				p.myPrev.myNext = p.myNext;
				p.myNext.myPrev = p.myPrev;
				if(deletePos == 0) {
					myHead = myHead.myNext;
				}
				mySize--;
				break;
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
    	int counter;
    	DListNode link;

        // Initialize an iterator object.
        public SequenceIterator() {
            counter = 0;
        	link = myHead;
        }
        
        // Return true when there are more ring elements to iterate over;
        // return false otherwise.
        public boolean hasNext() {
            // TODO Your code here
        	return counter < size();
        }
        
        // Return the next list element. 
        // Precondition: hasNext
        public Object next() {
        	if (hasNext()) {
        		Object temp = link.myItem;
        		counter++;
        		link = link.myNext;
        		return temp;
        	}
            return null;
        }

        // Removes the most recent item returned by next
        // Preconditon: next has been called at least once
        public void remove() {
        	if (counter > 0) {
        		delete(counter-1);        		
        	}
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
        System.out.println("");
        
        
        Sequence seq2 = new Sequence();
        seq2.addToSequence("1");
        seq2.addToSequence("2");
        seq2.addToSequence("3");
        seq2.addToSequence("4");
        seq2.addToSequence("5");
        seq2.addToSequence("6");
        
        Iterator iter = seq2.iterator();
        while(iter.hasNext()) {
        	System.out.println(iter.next());
        }
        System.out.println("");
        
        Iterator iter1 = seq2.iterator();
        if (iter1.hasNext()) {
        	System.out.println(iter1.next());
        }
        if (iter1.hasNext()) {
        	System.out.println(iter1.next());
        	System.out.println("");
        }
        iter1.remove(); // remove 2
        
        Iterator iter2 = seq2.iterator();
        while(iter2.hasNext()) {
        	System.out.println(iter2.next());
        }
    }
}
