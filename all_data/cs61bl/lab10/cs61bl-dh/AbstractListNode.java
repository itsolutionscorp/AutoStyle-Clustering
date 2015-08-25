import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public Comparable get(int pos);
    abstract public int size();
    abstract public String toString();
    abstract public boolean equals(AbstractListNode list);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    abstract public void setItem(Comparable arg);
    abstract public void setNext(AbstractListNode arg);
    
    public Comparable smallest() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can't find smallest in empty list.");
        }
        return next().smallestHelper(item());
    }

    public Comparable smallestHelper(Comparable smallestSoFar) {
        if (isEmpty()) {
            return smallestSoFar;
        }
            
        return next().smallestHelper(min(item(), smallestSoFar));
    }

    	public static Comparable min(Comparable c1, Comparable c2) {
    		if (c1.compareTo(c2) < 0) {
    			return c1;
    		} else {
    			return c2;
    		}
    	}
    	
    	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    		if (a.isEmpty()) {
    			return b;
    		}
    		if (b.isEmpty()) {
    			return a;
    		}
    		if (b.item() == min(a.item(), b.item())) {
    			b.setNext(merge(a, b.next()));
    			return b;
    		} else {
    			a.setNext(merge(a.next(), b));
    			return a;
    		}
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
    
    public Comparable get(int pos) {
    	if (pos == 0) {
    		return this.item();
    	}
    	
    	return this.next().get(pos-1);  	
    }
    
    public int size() {  	
    	return 1 + this.next().size();
    }
    
    public String toString() {
    	String result = new String();
    	AbstractListNode p = this;
    	
    	for (int i=0; i < size()-1; i++) {
    		result += p.item().toString();
    		result += " ";
    		p = p.next();
    	}
    	result = result + p.item().toString();
    	result = "( " + result + " )";

    	return result;
    }
    
    public boolean equals(AbstractListNode list) {
    	
    	if (size() != list.size())
    		return false;
    	
    	if (!item().equals(list.item()))
    		return false;
    	
    	return next().equals(list.next());
    }

	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(item(), next().add(c));
	}
	
	public AbstractListNode append(AbstractListNode list) {
		return new NonemptyListNode(item(), next().append(list));
	}
	
	public AbstractListNode reverse() {
		AbstractListNode rest = next().reverse();
		AbstractListNode list = rest.add(item());
		return list;
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		if (next().isEmpty()) {
			myNext = list2;
			return this;
		}
		myNext = next().appendInPlace(list2);
		return this;
	}
	
	public void setItem(Comparable arg) {
		myItem = arg;
	}
	
	public void setNext(AbstractListNode arg) {
		myNext = arg;
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
    	return new String("( )");
    }
    
    public boolean equals(AbstractListNode list) {   	
    	return size() == list.size();
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
	
	public void setItem(Comparable arg) {
		
	}
	
	public void setNext(AbstractListNode arg) {
		
	}
}
