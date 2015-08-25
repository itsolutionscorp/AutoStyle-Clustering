import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int y);
    abstract public String toString();
    abstract public String helperFunction();
    abstract public boolean equals(AbstractListNode lst);
    abstract public void myItem(Comparable item);
    abstract public void myNext(AbstractListNode listnode);
    public Comparable smallest() {
    	if (isEmpty()) {
    	  throw new NoSuchElementException("Can't find smallest in empty list.");
    	}
    	  return smallestHelper(item()) ;
    	}

    public Comparable smallestHelper(Comparable smallestSoFar) {
    	  if (next().isEmpty()) {
    		  return smallestSoFar;
    		  
    	  } else {
    		  return smallestHelper(min(smallestSoFar, next().item()));
    	  }
    	}

    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
    	    return c1;
    	} else {
    	    return c2;
    	}
    }
    public AbstractListNode add(Comparable c){
    	if (isEmpty()) {
    		return new NonemptyListNode(c, new EmptyListNode());
    	} else {
    		return new NonemptyListNode(item(), next().add(c));
    	}
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if (isEmpty()) {
    		return list;
    	} else {
    		return new NonemptyListNode(item(), next().append(list));
    	}
    }
    
    public AbstractListNode reverse() {
    	if (isEmpty()) {
    		return this;
    	}
    	
    	AbstractListNode rest = next().reverse();
    	AbstractListNode lst = rest.add(item());
        return lst;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if(isEmpty()) {
    		return list2;    		
    	} else {
    		myNext(next().appendInPlace(list2));
    		return this;
    	}
    }
    
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        if (a.isEmpty()) {
        	return b;
        } else if (b.isEmpty()) {
        	return a;
        } else if ((int) a.item() <= (int) b.item()) {
        	a.myNext(merge(a.next(), b)); 
        	return a;       	
        } else {
        	b.myNext(merge(a, b.next()));
        	return b;
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
    
    public int size() {
    	if(next().isEmpty()) {
    		return 1;
    	} else {
    		return 1 + next().size();
    	}
    }
    public Comparable get(int pos) {
    	if(next().isEmpty() && pos != 0 ) {
    		throw new IllegalArgumentException("out of bounds");
    	} else if (pos == 0) {
    		return myItem;
    	} else {
    		pos -= 1;
    		return next().get(pos);
    	}
    	
    }
    public String toString() {
    		return "(" + helperFunction();
    }
    
    public String helperFunction() {
    	if (next().isEmpty()) {
    		return " " + item() + " )";
    	} else {
    		return " " + item() + next().helperFunction();
    	}
    }
    
    public boolean equals(AbstractListNode lst) {
    	if (next().isEmpty() && lst.next().isEmpty() && lst.item() == item()) {
    		return true;
    	} else if (!next().isEmpty() && lst.next().isEmpty()) {
    		return false;
    	} else if (next().isEmpty() && !lst.next().isEmpty()) {
    		return false;
    	} else if(item() == lst.item()) {
    		return next().equals(lst.next());
    	} else {
    		return false;
    	}
    }
    
    public void myItem(Comparable item) {
    	myItem = item;
    }
    
    public void myNext(AbstractListNode listnode) {
    	myNext = listnode;
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
    public String toString () {
    	return "( )";
    }
    public String helperFunction () {
    	return "( )";
    }
    public boolean equals(AbstractListNode lst) {
    	if (lst.isEmpty()) {
    		return true;    		
    	} else {
    		return false;
    	}
    }
    
    public void myItem(Comparable item) {
    	
    }
    
    public void myNext(AbstractListNode listnode) {
    	
    }

}
