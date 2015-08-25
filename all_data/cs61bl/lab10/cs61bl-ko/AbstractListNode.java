import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    
    abstract int size();
    abstract Comparable get(int pos);
    public abstract String toString();
    public abstract boolean equals(Comparable arg);
    
    public abstract Comparable smallest();
    public abstract AbstractListNode add (Comparable c);
    public abstract AbstractListNode append(AbstractListNode list);
    public abstract AbstractListNode reverse();
    public abstract AbstractListNode appendInPlace(AbstractListNode list2);
    public abstract void setItem(Comparable l);
    public abstract void setNext(AbstractListNode l);
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    	if (a.isEmpty()) {
    		return b;
    	} else if (b.isEmpty()) {
    		return a;
    	}
    	AbstractListNode temp = null;
    	if (a.item().compareTo(b.item()) < 0) {
    		temp = a.next();
    		a.setNext(merge(temp, b));
    		return a;
    	}
    	temp = b.next();
    	b.setNext(merge(a, temp));
    	return b;
    }
} 
	
class NonemptyListNode extends AbstractListNode {
		
    private Comparable myItem;
    private AbstractListNode myNext;    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(item());
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    		for (int i = 0; i < size(); i++) {
    			smallestSoFar = min(smallestSoFar, get(i));
    		}
    		return smallestSoFar;
    	  
    	}

    	public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    	
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(this.item(), next().add(c));
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	NonemptyListNode answer = null; 
    	for (int i= list.size() - 1; i >= 0; i--) {
    		answer = new NonemptyListNode(list.get(i), answer);
    	}
    	for (int i= this.size() - 1; i >= 0; i--) {
    		answer = new NonemptyListNode(get(i), answer);
    	}
    	return answer;
    }
    
    public AbstractListNode reverse() {
    	AbstractListNode ans = new EmptyListNode();
    	for (int i = 0; i < size(); i++) {
    		ans = new NonemptyListNode(get(i), ans);
    	}
    	return ans;
    }
    
    public void setItem(Comparable l) {
    	myItem = l;
    }
    public void setNext(AbstractListNode l) {
    	myNext = l;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if (this.isEmpty()) {
    		return list2;
    	}
    	else {
    		setNext(next().appendInPlace(list2));
    		return this;
    	}
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
    
    public int size() {
    	return 1 + next().size();
    }
    
    public Comparable get(int pos) {
    	if (pos == 0) {
    		return item();
    	} else if (next() == null) {
    		throw new IllegalArgumentException ("Position is out of range.");
    	} else {
    		return next().get(pos - 1);
    	}
    }
    
    public String toString() {
    	String left = next().toString().substring(0, 2);
    	String right = next().toString().substring(2);
    	return left + "" + item() + " " + right;
    }
    
    public boolean equals(Comparable arg) {
    	if (arg instanceof AbstractListNode) {
    		AbstractListNode casted = (AbstractListNode) arg;
    		if (casted.size() != this.size()) {
    			return false;
    		} else {
    			if (casted.item().equals(this.item())) {
    				return casted.next().equals(this.next());
    		}
    	} 
    } return false;
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
    
    public boolean equals(Comparable arg) {
    	AbstractListNode casted = (AbstractListNode) arg;
    	if (casted instanceof EmptyListNode) {
    		return true;
    	}
    	return false;
    }

    public Comparable smallest() {
    	throw new NoSuchElementException("Can't find smallest in empty list.");
    }

    public AbstractListNode add (Comparable c) {
    	return new NonemptyListNode(c, new EmptyListNode());
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	return list;
    }
    
    public AbstractListNode reverse() {
    	return this;
    }
    
    public void setItem(Comparable l) {
    	return;
    }
    
    public void setNext(AbstractListNode l) {
    	return;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	return list2;
    }
}
