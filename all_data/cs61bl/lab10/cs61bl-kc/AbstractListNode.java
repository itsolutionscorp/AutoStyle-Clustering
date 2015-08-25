import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract int size();
    abstract public Comparable get(int index);
    abstract public String toString();
    abstract public String toString(String current);
    abstract boolean equals(AbstractListNode a);

    // Every other list-processing method goes here.
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return this.next().smallestHelper(this.item());
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
   		AbstractListNode n = this;
   		if (isEmpty()) {
   			return smallestSoFar;
   		}
   		return
    		(this.next().smallestHelper(min(this.item(), smallestSoFar))); 
    	}
    	
    	public static Comparable min(Comparable c1, Comparable c2) {
    		  if (c1.compareTo(c2) < 0) {
    		    return c1;
    		  } else {
    		    return c2;
    		  }
    		}
    	
    	abstract public AbstractListNode add (Comparable c);
    		
    	
    	abstract public AbstractListNode append(AbstractListNode list);
    	
    	abstract public AbstractListNode reverse();
    	
    	abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    	
    	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    	    // Fill this out
    		AbstractListNode temp = new EmptyListNode();
    		AbstractListNode head = temp, c = head;
    		while ((a != null) && (b != null))
    			if ((min(a.smallest(), b.smallest())) == a) {
    			c.next() = a;
    			c = a;
    			a = a.next();
    			}
    			else {
    				c.myNext = b;
    				c = b;
    				b = b.next();
    			}
    		c.myNext = (a == null) ? b : a;
    		return head.next();
    	}
    	
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
    	return 1 + myNext.size();
    }
    
    public Comparable get(int index){
        if(index + 1 > this.size())
            throw new IllegalArgumentException ("Out of Range");
        else if (index == 0) {
            return myItem;
        }
        else {
            index = index - 1;
            AbstractListNode next = this.myNext;
            return next.get(index);
        }
    }
    
    public String toString() {
		return this.toString("");
	}
    
	public String toString(String current) {
		return myNext.toString(current + " " + myItem.toString());
	}
	
	public boolean equals(AbstractListNode a) {
		if (this.myItem.equals(a.item()))
			return myNext.equals(a.next());
		else
			return false;
	}
	
	public AbstractListNode add (Comparable c) {
		NonemptyListNode newList = new NonemptyListNode(item(), next().add(c));
		return newList;	
		
	}
	
	
	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode app = new NonemptyListNode(item(), next().append(list));
	    return app;
	}
	
	public AbstractListNode reverse() {	
		AbstractListNode sofar = new EmptyListNode();
			return reverseHelper (this, sofar);
	}
	
	private static AbstractListNode reverseHelper (AbstractListNode list, AbstractListNode sofar) {
		if (list.isEmpty()) {
			return sofar;
		}
		else {
			return reverseHelper (list.next(), new NonemptyListNode (list.item(), sofar));
		}
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
        return appendHelper(this, list2);
	}
	
	private static AbstractListNode appendHelper(AbstractListNode list1, AbstractListNode list2) {
		if (list1.isEmpty()) {
			return list2;
		}
		else {
			AbstractListNode temp = list1.next();
			return appendHelper (temp, list2);
		}
	}
	
//	return new NonemptyListNode(list1.item(), appendHelper(list1.next(), list2));

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
    
    public Comparable get(int index) {
		throw new IllegalArgumentException("Out of Range");
	}
    
    public String toString() {
		return this.toString("");
	}
    
    public String toString(String current) {
		return "(" + current + " )";
	}
    
	public boolean equals(AbstractListNode a) {
		if (this.isEmpty() == a.isEmpty())
			return true;
		else
			return false;
	}
	
	public AbstractListNode add (Comparable c) {
		NonemptyListNode newList = new NonemptyListNode(c);
		return newList;	
		
	}
	
	public AbstractListNode append(AbstractListNode list) {
		return list;
	}
	
	public AbstractListNode reverse() {
		return null;
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2){
		return list2;
	}

}
