import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode compare);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public void setItem(Comparable c);
    abstract public void setNext(AbstractListNode next_node);
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    	if (a.isEmpty() && b.isEmpty()) {
    		return a;
    	}
    	else if (a.isEmpty() && !b.isEmpty()) {
    		return b;
    	}
    	else if (b.isEmpty() && !a.isEmpty()) {
    		return a;
    	}
    	else if ((int)a.item() < (int)b.item()) {
    		a.setNext(merge(a.next(), b));
    		return a;
    	} else {
    		b.setNext(merge(a, b.next()));
    		return b;
    	}
    }
    
    public Comparable smallest() {
  	  if (isEmpty()) {
  	    throw new NoSuchElementException("Can't find smallest in empty list.");
  	}
  	  return smallestHelper(item());
  }
  
  public Comparable smallestHelper(Comparable smallestSoFar) {
	  if (next().isEmpty()) {
		  return smallestSoFar;
	  } else {
		  return min(smallestSoFar, next().smallest());
		  }
  }
  
  public static Comparable min(Comparable c1, Comparable c2) {
  	  if (c1.compareTo(c2) < 0) {
  	    return c1;
  	  } else {
  	    return c2;
  	  }
  }

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
    	if (myNext.size() == 0) {
    		return 1;
    	} else {
    		return 1 + myNext.size();
    	}
    }
    
    public Comparable get(int pos) {
    	if (pos == 0) {
    		return myItem;
    	}
    	if (pos < 0 || (myNext.get(pos-1) == null && pos > 0)) {
    		throw new IllegalArgumentException("Position is out of range.");
    	} else {
    		return myNext.get(pos-1);
    	}
    }
    
    public String toString() {
    	if (myNext.isEmpty()) {
    		return "( " + myItem + " )";
    	} else {
    		String the_string = "( ";
    		for (int i = 0; i < size(); i++) {
    			the_string += get(i) + " ";
    		}
    		return the_string + ")";
    	}
    }
    
    public boolean equals(AbstractListNode compare) {
    	if (size() != compare.size()) {
    		return false;
    	} else {
    		for (int i = 0; i < size(); i++) {
    			if (!get(i).equals(compare.get(i))) {
    				return false;
    			}
    		}
    		return true;
    	}
    }
    
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(item(), next().add(c));
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	return new NonemptyListNode(item(), next().append(list));
    }
    
    public AbstractListNode reverse() {
    	if (next().isEmpty()) {
    		return new NonemptyListNode(item());
    	}
    	AbstractListNode reversed = new EmptyListNode();
    	for (int i = size() - 1; i >= 0; i--) {
    		reversed = reversed.add(get(i));
    	}
    	return reversed;
    }
    
    public void setItem(Comparable c) {
    	myItem = c;
    }
    
    public void setNext(AbstractListNode next_node) {
    	myNext = next_node;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if (list2.isEmpty()) {
    		return this;
    	}
    	else if (next().isEmpty()) {
    		this.setNext(list2);
    		return this;
    	}
    	else {
    		return next().appendInPlace(list2);
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
    	return null;
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(AbstractListNode compare) {
    	if (compare.isEmpty()) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(c);
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if (list.isEmpty()) {
    		return new EmptyListNode();
    	}
    	return new NonemptyListNode(list.item(), list.next().append(this));
    }
    
    public AbstractListNode reverse() {
    	return new EmptyListNode();
    }
    
    public void setItem(Comparable c) {
    }
    
    public void setNext(AbstractListNode next_node) {
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	return list2;
    }
}




