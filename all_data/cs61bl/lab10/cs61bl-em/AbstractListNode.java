import java.util.NoSuchElementException;

abstract public class AbstractListNode {

	abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public Object get(int i);
	abstract public int size();
	abstract public boolean equals(AbstractListNode l);
	abstract public AbstractListNode append(AbstractListNode list);
	abstract public AbstractListNode add (Comparable c);
	abstract public AbstractListNode reverse();
	abstract public void setItem(Comparable c);
	abstract public void setNext(AbstractListNode list);
	public static AbstractListNode merge (AbstractListNode a, AbstractListNode b) {
    	if (a.isEmpty()) {
    		return b;
    	}
    	else if (b.isEmpty()) {
    		return a;
    	}
    	else if (min(a.item(), b.item()).equals(a.item())) {
    		a.setNext(merge(a.next(), b));
    		return a;
    	}
    	else {
    		b.setNext(merge(a, b.next()));
    		return b;
    	}    	
    }
		
	public Comparable smallest() throws NoSuchElementException {
		  if (isEmpty()) {
		    throw new NoSuchElementException("Can't find smallest in empty list.");
		  }
		  return smallestHelper(item());
		}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		  if (next().isEmpty()) {
			  return smallestSoFar;
		  }
		  return next().smallestHelper(min (smallestSoFar, next().item()));
		}

	public static Comparable min(Comparable c1, Comparable c2) {
		  if (c1.compareTo(c2) < 0) {
		    return c1;
		  } else {
		    return c2;
		  }
		}

	abstract public AbstractListNode appendInPlace(AbstractListNode list2);
	
    // Every other list-processing method goes here.

}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;

    public String toString() {
    	String s = "( ";
    	for (int i = 0; i < size(); i++) {
    		s = s + (get(i)) + " ";
    	}
    	s = s + ")";
    	return s;
    }
    
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

    public Comparable get (int i) {
    	if (i == 0) {
    		return myItem;
    	}
    	else {
    		return (Comparable) myNext.get (i - 1);
    	}
    }
    
    public int size () {
    	return 1 + next().size();
    	
    }
    
    public boolean equals (AbstractListNode l) {
    	return (size() == l.size() && item().equals(l.item()) && (next() == null && l.next() == null) || (next().equals(l.next())));
    }
    
    //destructive version:
    /*public AbstractListNode add (Comparable c) {
    	if (next().isEmpty()) {
    		AbstractListNode l = new NonemptyListNode (c);
    		myNext = l;
    		return this;
    	}
    	else {
    		return new NonemptyListNode(item(), next().add(c));
    	}
    	
    }*/
    
    public AbstractListNode add (Comparable c) {
    	if (next().isEmpty()) {
    		AbstractListNode l = new NonemptyListNode (c);
    		return new NonemptyListNode(item(), l);
    	}
    	else {
    		return new NonemptyListNode(item(), next().add(c));
    	}
    	
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if (list.isEmpty()) {
    		return this;
    	}
    	else if (next().isEmpty()) {
    		return new NonemptyListNode(item(), list);
    	}
    	else {
    		return new NonemptyListNode(item(), next().append(list));
    	}
    }
    
    public AbstractListNode reverse() {
    	if (next().isEmpty()) {
    		return this;
    	}
    	else {
    		return next().reverse().add(item());
    	}
    }
    
    public void setItem (Comparable c) {
    	myItem = c;
    }
    
    public void setNext (AbstractListNode list) {
    	myNext = list;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if (next().isEmpty()) {
    		setNext(list2);
    		return this;
    	}
    	else {
    		setNext(next().appendInPlace(list2));
    		return this;
    	}  		
    }
}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public String toString() {
		return "( )";
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

    public Comparable get (int i) {
    		return null;
    }
    
    public int size () {
    	return 0;
    }
    
    public boolean equals (AbstractListNode l) {
    	return true;
    }
    
    public AbstractListNode add (Comparable c) {
    	return new NonemptyListNode (c);
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	AbstractListNode l = list;
    	return l;
    }
    
    public AbstractListNode reverse() {
    	return this;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	return list2;
    }
    
    public static AbstractListNode merge (AbstractListNode a, AbstractListNode b) {
    	return null;    	
    }

	@Override
	public void setItem(Comparable c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNext(AbstractListNode list) {
		// TODO Auto-generated method stub
		
	}
}
