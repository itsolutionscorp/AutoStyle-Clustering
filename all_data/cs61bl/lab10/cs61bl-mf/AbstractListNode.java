import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int index);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode other);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    //abstract public static AbstractListNode merge(AbstractListNode a, AbstractListNode b);

    // Every other list-processing method goes here.

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
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  Comparable smallestSoFar = myItem;
    	  return smallestHelper(smallestSoFar);
    }
    	public Comparable smallestHelper(Comparable smallestSoFar) {
    		if (myNext.isEmpty()) {
    			return min(smallestSoFar, myItem);
    		} else {
    			return ((NonemptyListNode) myNext).smallestHelper(min(smallestSoFar, myItem));
    		}
    	}
    
    public AbstractListNode add (Comparable c){
    		AbstractListNode rtn = new NonemptyListNode(c, new EmptyListNode());
            for(int index = (this.size() - 1); index >= 0; index--) {
            	rtn = new NonemptyListNode(this.get(index), rtn);
            }
            return rtn;
       }	
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if (myNext == null || myNext.isEmpty()) {
    		myNext = list2;
    		return this;
    	} else {
    		return myNext.appendInPlace(list2);
    	}
    }
    	
    public AbstractListNode append(AbstractListNode list) {
    	AbstractListNode rtn;
    	if (list == null) {
    		rtn = new EmptyListNode();
    	} else if (list.isEmpty()) {
    		rtn = new EmptyListNode();
    	} else {
	    	rtn = new NonemptyListNode(list.get(list.size() - 1), new EmptyListNode());
	    	for(int index = (list.size() - 2); index >= 0; index--) {
	        	rtn = new NonemptyListNode(list.get(index), rtn);
	        }
    	}
    	
    	//Don't need if/else for "this" object because this method will only be read in the nonempty class
    	for(int index = (this.size() - 1); index >= 0; index--) {
        	rtn = new NonemptyListNode(get(index), rtn);
    	}
    	 return rtn;
    }
    
    public AbstractListNode reverse() {
    	AbstractListNode rtn = new EmptyListNode();
    	for(int index = 0; index < this.size(); index++) {
        	rtn = new NonemptyListNode(get(index), rtn);
    	}
    	 return rtn;
    }
    
    public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
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
    	if (myNext.isEmpty()) {
    		return 1;
    	} else {
    		return (1 + myNext.size());
    	}
    }
    
    public Comparable get(int index) {
    	if (index < 0) {
    		throw new IllegalArgumentException("List index out of bounds.");
    	}
    	if (index == 0) {
    		return myItem;
    	} else {
    		if (myNext.isEmpty()) {
    			throw new IllegalArgumentException("List index out of bounds.");
    		} else {
    		return myNext.get(index - 1);
    		}
    	}
    }
    
    public String toString() {
    	return "( " + this.toStringHelper() + ")";
    }
    
    public String toStringHelper() {
    	if (myNext.isEmpty()) {
    		return "" + myItem.toString() + " ";
    	} else {
    		return "" + myItem.toString() + " " + ((NonemptyListNode) myNext).toStringHelper();
    	}
    }
    
    public boolean equals(AbstractListNode other) {
    	if (other.isEmpty()) {
    		return false;
    	} else if (!myItem.equals(other.item())) {
    		return false;
    	} else {
    		return myNext.equals(other.next());
    	}
    }
    
    public void setNext (AbstractListNode newNext) {
    	myNext = newNext;
    }
    
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b ) {
    	if (a.isEmpty() || a == null) {
    		return b;    		
    	} else if (b.isEmpty() || b == null) {
    		return a;
    	}


    	NonemptyListNode start, end;
    	if (min(a.get(0) , b.get(0)) == a.get(0)) {
    		start = (NonemptyListNode) a;
    		end = (NonemptyListNode) a;
    		a = a.next();
    	} else {
    		start = (NonemptyListNode) b;
    		end = (NonemptyListNode) b;
    		b = b.next();
    	}

    	while (true) {
    		
    		if (a.isEmpty() || a == null) {
    			end.setNext(b);
    			break;
    		} else if (b.isEmpty() || b == null) {
    			end.setNext(a);
    			break;
    		}


    		if (min(a.get(0) , b.get(0)) == a.get(0)) {
    			end.setNext(a);
    			a = a.next();
    			end = (NonemptyListNode) end.next();
    		} else {
    			end.setNext(b);
    			b = b.next();
    			end = (NonemptyListNode) end.next();
    		}
    	}
	return start;
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
    public Comparable get(int index) {
    	return null;
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals (AbstractListNode other) {
    	return other.isEmpty();
    }
    
    public NonemptyListNode add(Comparable c) {
    	return new NonemptyListNode(c, new EmptyListNode());
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	return list2;
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	AbstractListNode rtn;
    	if (list == null) {
    		rtn = new EmptyListNode();
    	} else if (list.isEmpty()) {
    		rtn = new EmptyListNode();
    	} else {
	    	rtn = new NonemptyListNode(list.get(list.size() - 1), new EmptyListNode());
	    	for(int index = (list.size() - 2); index >= 0; index--) {
	        	rtn = new NonemptyListNode(list.get(index), rtn);
	    	}
        } 
    	
    	return rtn;
    	
    	/*AbstractListNode rtn = new NonemptyListNode(list.item(), list.next());
    	for(int index = (list.size() - 1); index >= 0; index--) {
        	rtn = new NonemptyListNode(list.get(index), rtn);
        } return rtn; */
	}
    
    public AbstractListNode reverse() {
    	return new EmptyListNode();
    }
}
