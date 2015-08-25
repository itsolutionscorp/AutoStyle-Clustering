 import java.util.ArrayList;
import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    
    abstract public String nodeStringing();
    abstract public int size();
    abstract public Comparable get(int k);
    public String toString() {
    	return "(" + nodeStringing() + ")";
    }
    abstract public boolean equals(AbstractListNode p);
    // Every other list-processing method goes here.
    
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
    	if (a.isEmpty()) {
    		return b;
    	} 
    	else if (b.isEmpty()) {
    		return a;
    	} else {
    		if (b.item().compareTo(a.item()) >= 0) {
    			((NonemptyListNode) a).setNext(merge(a.next(), b));
				return a;
    		} else {
    			((NonemptyListNode) b).setNext(merge(a, b.next()));
    			return b;
    		}
    	}
    }
    
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    
    abstract public AbstractListNode add (Comparable c);
    abstract public AbstractListNode append (AbstractListNode list);
    abstract public AbstractListNode reverse ();
    
    public Comparable smallest() {
  	  if (isEmpty()) {
  	    throw new NoSuchElementException("Can't find smallest in empty list.");
  	  } else {
  		  return this.smallestHelper(this.item());
  	  }
  	}
    
    public Comparable smallestHelper(Comparable smallestsofar) {
    	if (this.isEmpty()) {
    		return smallestsofar;
    	} else {
    		return min(smallestsofar, this.next().smallestHelper(this.item()));
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
    

    public NonemptyListNode (Comparable item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }
    
    public AbstractListNode add (Comparable c){
      	return new NonemptyListNode(item(), next().add(c));
    }
    
    public AbstractListNode append(AbstractListNode list) {
        return new NonemptyListNode(item(), next().append(list));
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	setNext(next().appendInPlace(list2));
    	return this;
    }
    
    public void setItem(Comparable c) {
    	myItem = c;
    }
    
    public void setNext(AbstractListNode a) {
    	myNext = a;
    }
    
    public AbstractListNode reverse() {
    	return next().reverse().append(new NonemptyListNode(myItem));
    }
   
    public NonemptyListNode (Comparable item) {
        this (item, new EmptyListNode());
    }
    
    public Comparable get(int k) {
    	if (k > 0) {
    	return myNext.get(k-1);
    	} 
    	else if (k == 0){
    		return myItem;
    	} else {
    		throw new IllegalArgumentException();
    	}
    }
    
    public boolean equals(AbstractListNode p) {
    	return myItem.equals(p.item()) && myNext.equals(p.next());
    }
    
    public String nodeStringing() {
    	 return " " + myItem + myNext.nodeStringing();
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
}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public AbstractListNode reverse() {
    	return new EmptyListNode();
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if (list.isEmpty()) {
    		return new EmptyListNode();
    	} else {
        return new NonemptyListNode(list.item()).append(list.next());
    	}
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	return list2;
    }
    
    public AbstractListNode add (Comparable c) {
    	return new NonemptyListNode(c);
    }
    
    public Comparable get(int k) {
    	if (k == 0) {
    		return (Comparable) this;
    	} else {
    	throw new IllegalArgumentException();
    	}
    }
    
    public boolean equals(AbstractListNode p) {
    	return p.isEmpty();
    }
    public String nodeStringing() {
    	return " ";
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

}
