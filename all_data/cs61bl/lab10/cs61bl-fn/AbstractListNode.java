import java.util.NoSuchElementException;
abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    abstract public void setItem(Object item);
    abstract public void setNext(AbstractListNode next);
    public int size () {
    	if ( !isEmpty() ) {
    		return 1 + next().size();
    	}
    	return 0;
    }
    public Comparable get(int pos) {
    	if ( pos < 0 && pos + 1 >= size() ) {
    		throw new IllegalArgumentException("invalid position");
    	} else if ( pos == 0) {
    		return item();
    	} else return next().get(pos-1);
    }
    public String toString() {
    	AbstractListNode test = this;
    	String toReturn = "(";
    	while (!test.isEmpty()) {
    		toReturn = toReturn+" " + test.item();
    		test = test.next();
    	}
    	toReturn = toReturn +" )";
    	return toReturn;
    }
    public boolean equals(AbstractListNode toCompare) {
    	if (this.size() != toCompare.size()) {
    		return false;
    	} else if (this.isEmpty() && toCompare.isEmpty()) {
    		return true;
    	} else if ( !this.item().equals(toCompare.item())) {
    		return false;
    	} else return this.next().equals(toCompare.next());
    }
    public Comparable smallest() {
        if (isEmpty()) {
            throw new NoSuchElementException ("can't find smallest in empty list");
        }
        return smallestHelper(next(), item());
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
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        if (a.isEmpty() && b.isEmpty()) {
        	return a;
        } else if (a.isEmpty()) {
        	return b;
        } else if (b.isEmpty()) {
        	return a;
        }
        
        if ((int) a.item() <= (int) b.item()) {
        	a.setNext(merge(a.next(),b));
        } else {
        	AbstractListNode toSort = b.next();
        	b.setNext(a);
        	a = b;
        	a.setNext(merge(a.next(),toSort));
        }
        return a;
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
    public AbstractListNode add(Comparable c) {
    		return new NonemptyListNode(item(), next().add(c));
    }
    public AbstractListNode append(AbstractListNode list) {
    	if (list.isEmpty()) {
    		return this;
    	}
    	return this.add(list.item()).append(list.next());
    }
    public AbstractListNode reverse() {
    	return next().reverse().add(item());
    }
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if (!next().isEmpty()) {
    		next().appendInPlace(list2);
    	} else myNext = list2;
    	return this;
    }
    public void setItem(Object item) {
    	myItem = (Comparable) item;
    }
    public void setNext(AbstractListNode next) {
    	myNext = next;
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
    public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c);
}
    public AbstractListNode append(AbstractListNode list) {
    	if (list.isEmpty()) {
    		return new EmptyListNode();
    	}
    	return new NonemptyListNode(list.item()).append(list.next()); 
    }
    public AbstractListNode reverse() {
    	return new EmptyListNode();
    }
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	return list2;
    }
    public void setItem(Object item) {
    	throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    public void setNext(AbstractListNode next) {
    	throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
}
