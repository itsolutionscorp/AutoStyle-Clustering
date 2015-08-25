import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int i);
    abstract public boolean equals(Object o);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode l);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode l);
    abstract public String toString();
    

    // Every other list-processing method goes here.
    public Comparable smallest() {
        if (isEmpty()) {
            throw new NoSuchElementException ("can't find smallest in empty list");
        }
        return smallestHelper(next(), item());
    }
    
    public static AbstractListNode merge(AbstractListNode l1, AbstractListNode l2) {
    	if (l1.isEmpty() && l2.isEmpty()) {
    		return l1;
    	} else if (l1.isEmpty()) {
    		return l2;
    	} else if (l2.isEmpty()) {
    		return l1;
    	} else if ((Integer) l1.item() < (Integer) l2.item()) {
    		((NonemptyListNode) l1).setNext(merge(l1.next(), l2));
    		return l1;
    	} else {
    		((NonemptyListNode) l2).setNext(merge(l1, l2.next()));
    		return l2;
    	}
    }

    public static Comparable smallestHelper(AbstractListNode list, Comparable smallestSoFar) {
        if (list.isEmpty()) {
            return smallestSoFar;
        } else {
            return smallestHelper(list.next(), min(smallestSoFar, list.item()));
        }
    }
    	
	public static Comparable min(Comparable c1, Comparable c2) {
		  if (c1.compareTo(c2) < 0) {
		    return c1;
		  } else {
		    return c2;
		  }
		}
}
	

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;
    
    public AbstractListNode appendInPlace(AbstractListNode l) {
    	if (l.isEmpty()) {
    		return this;
    	}
    	myNext = myNext.appendInPlace(l);
    	return this;
    }

    public NonemptyListNode (Comparable item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }
    
    public AbstractListNode reverse() {
		AbstractListNode new_list = new EmptyListNode();
		for (int i = size() - 1; i >= 0; i--) {
			new_list = new_list.add(get(i));
		}
		return new_list;
    }
    
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(item(), next().add(c));
    }
    
    public AbstractListNode append(AbstractListNode l) {
    	if (l.isEmpty()) {
    		return new NonemptyListNode(item(), next());
    	} else {
    		return new NonemptyListNode(myItem, next().append(l));
    	}
    }

    public boolean equals(Object o) {
    	if (((AbstractListNode) o).isEmpty()) {
    		return false;
    	} else if (((AbstractListNode) o).item().equals(item())) {
    		return next().equals(((AbstractListNode) o).next());
    	} else {
    		return false;
    	}
    }

    public NonemptyListNode (Comparable item) {
        myItem = item;
        myNext = new EmptyListNode();
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
    
    public void  setItem(Comparable o) {
    	myItem = o;
    }
    
    public void setNext(AbstractListNode l) {
    	myNext = l;
    }
    
    public Comparable get(int i) {
    	if (i == 0) {
    		return myItem;
    	} else {
    		return (Comparable) myNext.get(i - 1);
    	}
    }
    
    public String toString() {
    	return "( " + myItem.toString() + " " + myNext.toString().substring(2);
    }

}

class EmptyListNode extends AbstractListNode {
	
    public EmptyListNode() {
        
    }
    
    
    public AbstractListNode appendInPlace(AbstractListNode l) {
    	return l;
    }
    
    public AbstractListNode reverse() {
    	return new EmptyListNode();
    }
    
    public AbstractListNode append(AbstractListNode l) {
    	if (l.isEmpty()) {
    		return new EmptyListNode();
    	} else {
    		return new NonemptyListNode(l.item(), l.next());
    	}
    }
    
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(c);
    }
    
    public Object get(int i) {
    	throw new IllegalArgumentException("index out of range");
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
    
    public boolean equals(Object o) {
    	if (((AbstractListNode) o).isEmpty()) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public String toString() {
    	return "( )";
    }
}

