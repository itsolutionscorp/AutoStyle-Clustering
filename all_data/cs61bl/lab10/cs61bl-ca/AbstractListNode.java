import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

abstract public class AbstractListNode implements Comparable{
	
    abstract public Comparable item();
	abstract public int compareTo(Object o); 
	abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int idx);
    abstract public String toString(); 
    abstract public boolean equals1(AbstractListNode o);
    abstract public Comparable smallest();
    abstract public Comparable smallestHelper(Comparable smallestSoFar);
	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}
	abstract public AbstractListNode add(Comparable c);
	abstract public AbstractListNode append(AbstractListNode list);
	abstract public AbstractListNode reverse();
	abstract public AbstractListNode appendInPlace(AbstractListNode list2);
	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b ) {
		if (a.isEmpty()) {
			return b;
		} else if (b.isEmpty()) {
			return a;
		} 
		
	}
	   
	
	
	public static void main (String[] args) {
//		AbstractListNode l1 = new EmptyListNode();
//		AbstractListNode l2 = l1.add("a");
//		assertEquals("a ", l2.toString());
//		AbstractListNode l3 = l2.add("b");
//		System.out.println(l3);
//		System.out.println(l3.reverse());
	    AbstractListNode empty1 = new EmptyListNode();
	    System.out.println(empty1.toString());
	    
	}
//
//    // Every other list-processing method goes here.

}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;

    public NonemptyListNode (Comparable item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    public NonemptyListNode (Comparable item) {
        this (item, new EmptyListNode());
    }

    public Comparable item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }
    
    public boolean isEmpty() {
        return false;
    }
    public int size() {
    	if (this.isEmpty()) {
    		return 0;
    	} else {
    		return 1 + this.next().size();
    	}
    }
    public Comparable get(int idx) {
    	int i = 0;
    	AbstractListNode temp = this;
    	while(temp.isEmpty() == false) {
    		if (i == idx) {
    			return temp.item();
    		}
    		temp = temp.next();
    		i += 1;
    	}
    	if (idx > i) {
    		throw new IllegalArgumentException("out of bounds");
    		
    	}
    	return temp.item();
    }
    public String toString() {
    	if (this.next().isEmpty()){
    		 return item() + " ";
    	}
    	else {
    		return item() + " " + this.next().toString(); 
    	}
    }
    
    @Override
    public boolean equals1(AbstractListNode o) {
    	if (size() != ((AbstractListNode) o).size()) {
    		return false;
    	} else {
    		System.out.println((o.item().equals(item())));
    		return ((o.item().equals(item())) && this.next().equals1(o.next()));
		}
	}

	public Comparable smallest() {
		if (isEmpty()) {
			throw new NoSuchElementException(
					"Can't find smallest in empty list.");
		}
		return smallestHelper(myItem);
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		if (myNext.isEmpty()) {
			return smallestSoFar;
		} else {
			smallestSoFar = min(myNext.item(), smallestSoFar);
			return myNext.smallestHelper(smallestSoFar);
		}
    	}
	
	public AbstractListNode add(Comparable c) {
        NonemptyListNode n = new NonemptyListNode(myItem, myNext.add(c));
        return n;
    }
	
	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode n = new NonemptyListNode(myItem, myNext.append(list));
		return n;
	}

	public AbstractListNode reverse() {
		NonemptyListNode temp = this;
		NonemptyListNode rev = new NonemptyListNode(temp.myItem);
		while(temp.next().isEmpty() == false) {
			temp = (NonemptyListNode) temp.next();
			rev = new NonemptyListNode(temp.myItem, rev);
    	}
		return rev;
	}
	public void setItem(Comparable i) {
		myItem = i;
	}
	public void setNext(AbstractListNode a) {
		myNext = a;
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2){
	    if (this.isEmpty()) {
	    	return list2;
	    } else {
	    	AbstractListNode temp = this;
	    	while (temp.next().isEmpty() == false) {
	    		temp = temp.next();
	    	}
	    	((NonemptyListNode) temp).setNext(list2);
	    	return this;
	    }
	}

	@Override
	public int compareTo(Object o) {
		if (((AbstractListNode) o).isEmpty() && this.isEmpty()) {
			return 0;
		} else if (((AbstractListNode) o).isEmpty()) {
			return 1;
		} else if (this.isEmpty()) {
			return -1;
		} else {
			return myItem.compareTo(((AbstractListNode) o).item());
		}
	}

//	@Override
//	public int compareTo(Object o) {
//		// TODO Auto-generated method stub
//		return 0;
//	}


	



}



class EmptyListNode extends AbstractListNode {
	
    
    public EmptyListNode() {
        
    }
    
    public Comparable item() {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public AbstractListNode next() {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    public boolean isEmpty() {
        return true;
    }
    public int size() {
    	return 0;
    }
    public Comparable get(int idx) {
    	if (idx > 0) {
    		throw new IllegalArgumentException("out of bounds");
    		
    	}
    	return null;
    }
    public String toString() {
//    	System.out.println("heya");
    	return "";
    }
    public boolean equals1(AbstractListNode o) {
    	if (((AbstractListNode) o).isEmpty()) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public AbstractListNode add(Comparable c) {
    	NonemptyListNode n = new NonemptyListNode(c);
    	return n;
    }
    public AbstractListNode append(AbstractListNode list) {
		return list;
	}


	@Override
	public Comparable smallest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparable smallestHelper(Comparable smallestSoFar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractListNode reverse() {
		// TODO Auto-generated method stub
		return new EmptyListNode();
	}

	@Override
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		// TODO Auto-generated method stub
		return list2;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return -1;
	}



	




}
