import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Comparable get(int pos);
    @Override
	abstract public String toString();
    abstract public String allItems();
    abstract public boolean equals(AbstractListNode l);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public void setNext(AbstractListNode c);
    
    public Comparable smallest() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("Can't find smallest in empty list.");
    	}
    	  	return smallestHelper(item());
    }

	public Comparable smallestHelper(Comparable smallestSoFar) {
		if (next().smallest() == null) {
			return smallestSoFar;
		}
		return min(smallestSoFar, next().smallest());
	}

	public static Comparable min(Comparable c1, Comparable c2) {
		if (c1.compareTo(c2) < 0) {
			return c1;
		} else {
			return c2;
		}
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		AbstractListNode result = this;
		if (this.isEmpty()) {
			return list2;
		}
		else {
			while (!result.next().isEmpty()) {
				result = result.next();
			}
		}
		result.setNext(list2);
		return result;
	}

	public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
		
		if (a.isEmpty()) {
			return b;
		}
		if (b.isEmpty()) {
			return a;
		}
			
		AbstractListNode result;
		
		if (min(a.item(), b.item()) == a.item()) {
			result = a;
			result.setNext(merge(a.next(), b));
		}
		else {
			result = b;
			result.setNext(merge(a, b.next()));
		}
		
		return result;
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

    @Override
	public Comparable item() {
        return myItem;
    }

    @Override
	public AbstractListNode next() {
        return myNext;
    }
    
    @Override
	public boolean isEmpty() {
        return false;
    }

	@Override
	public int size() {
		return 1 + myNext.size();
	}
	
	@Override
	public Comparable get(int pos) {

		if (pos < 0 || (pos > size())){
    		throw new IllegalArgumentException();
    	} 
    	if (pos == 0) {
			return item();
		}else {
			return myNext.get(pos-1);
		}
    }

	@Override
	public String allItems() {
		return item() + " " + myNext.allItems();
	}
	
	@Override
	public String toString() {
		return "( " + allItems() + ")";
	}

	@Override
	public boolean equals(AbstractListNode l) {
		return this.toString().equals(l.toString());
	}

	@Override
	public AbstractListNode add (Comparable c){
        NonemptyListNode result = new NonemptyListNode(item());
        NonemptyListNode tracker = result;
        AbstractListNode current = this.next();
        
        while (!current.isEmpty()) {
        	tracker.myNext = new NonemptyListNode(current.item());
        	current = current.next();
        	tracker = (NonemptyListNode) tracker.next();
        }
        tracker.myNext = new NonemptyListNode(c);
        return result;
    }

	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode result = this;
		AbstractListNode current = list;
		while (!current.isEmpty()) {
			result = result.add(current.item());
			current = current.next();
		}
		return result;
	}

	@Override
	public AbstractListNode reverse() {
		
		NonemptyListNode last;
		NonemptyListNode tracker = this;
		while (!tracker.next().isEmpty()) {
			tracker = (NonemptyListNode) tracker.next();
		}
		last = (NonemptyListNode) tracker;
		NonemptyListNode result = new NonemptyListNode(last.item());
		NonemptyListNode resultTracker = result;
		if (this.next().isEmpty()) {
			return result;
		}
		while (last != this.next()) {
			tracker = (NonemptyListNode) this.next();
			while (tracker.next() != last) {
				tracker = (NonemptyListNode) tracker.next();
			}
			last = tracker;
			resultTracker.myNext = new NonemptyListNode(last.item());
			resultTracker = (NonemptyListNode) resultTracker.next();
		}
		resultTracker.myNext = new NonemptyListNode(item());
		
		return result;
	}

	@Override
	public void setNext(AbstractListNode c) {
		myNext = c;
	}
}







class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    @Override
	public Comparable item() {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    @Override
	public AbstractListNode next() {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    @Override
	public boolean isEmpty() {
        return true;
    }

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Comparable get(int pos) {
		return item();
	}

	@Override
	public String toString() {
		return "( )";
	}

	@Override
	public String allItems() {
		return "";
	}

	@Override
	public boolean equals(AbstractListNode l) {
		return this.toString().equals(l.toString());
	}
	
	@Override
	public Comparable smallest() {
		return null;
	}

	@Override
	public AbstractListNode add(Comparable c) {     
        return new NonemptyListNode(c);
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		return list;
	}

	@Override
	public AbstractListNode reverse() {
		return this;
	}

	@Override
	public void setNext(AbstractListNode c) {
		throw new IllegalArgumentException("Cannot set next of empty node");
	}
	
}
