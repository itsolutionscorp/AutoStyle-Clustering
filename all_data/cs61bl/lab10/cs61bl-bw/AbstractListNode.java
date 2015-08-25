abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    public String toStringThing = "";
    abstract public boolean equals(Object lst);


}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;
    public int length = 0;

    public NonemptyListNode (Object item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    public NonemptyListNode (Object item) {
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
    	length = this.next().size() + 1;
    	return length;
    }
    public Comparable get(int pos) {
    	if (pos > size() - 1) {
    		throw new IllegalArgumentException("out of bounds Element");
    	}
    	if (pos < 0) {
    		throw new IllegalArgumentException("cannot get negative element");
    	}
    	if (pos == 0) {
    		return this.item();
    	}
    	AbstractListNode x = this;
    	while (pos > 0) {
    		x = x.next();
    		pos--;
    	}
    	return x.item();
    	
    }
    public String toString() {
    	this.toStringThing += "( " + myItem + " " + next().toString().substring(2);
    	return toStringThing;
    }
    public boolean equals(Object lst) {
    	int equal = 0;
    	if (this.size() == ((AbstractListNode) lst).size()) {
    		for (int i = 0; i < this.size() - 1; i++) {
    			if (this.get(i) != ((AbstractListNode) lst).get(i)) {
    				return false;
    			}
    		} 
    		return true;	
    	} else {
    		return false;
    	}
    }

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
    public Comparable get(int pos) {
    	throw new IllegalArgumentException ("Cannot Get from an EmptyListNode");
    }
    public String toString() {
    	this.toStringThing += "  )";
    	return toStringThing;
    }
    public boolean equals(Object lst) {
    	if (((AbstractListNode) lst).size() == 0) {
    		return true;
    	} else {
    		return false;	
    	}
    	
    }
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return;
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    	  return;
    	}

    	public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    	  public AbstractListNode add (Comparable c){
    	        return;
    	    }
    	  public AbstractListNode append(AbstractListNode list) {
    		   return;
    		}
    	  public AbstractListNode reverse() {
    		    return;
    		}
    	  public AbstractListNode appendInPlace(AbstractListNode list2){
    		    return;
    		}
    	  public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
    		    // Fill this out
    		}
    
    
}
