import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Comparable get(int i);
    abstract public String toString();
    abstract public boolean equals(Comparable other);
    abstract public Comparable smallest();
    
    public AbstractListNode addHelperFunction(Comparable c, AbstractListNode e) {
    	if (e.isEmpty()) {
    		return new NonemptyListNode(c);
    	}
    	return new NonemptyListNode(e.item(), addHelperFunction(c, e.next()));
    }

    public AbstractListNode add(Comparable c) {
    	return addHelperFunction(c, this);
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	AbstractListNode listCopy = list;
    	AbstractListNode thisCopy = this;
    	if (list.isEmpty()) {
    		return thisCopy;
    	} else if (this.isEmpty()) {
    		return listCopy;
    	}
    	while (listCopy.isEmpty() == false) {
    		thisCopy = thisCopy.add(listCopy.item());
    		listCopy = listCopy.next();
    	}
    	
    	return thisCopy;
    }
    
    public AbstractListNode reverseHelper(AbstractListNode prev, AbstractListNode current) {
    	NonemptyListNode wow;
    	if (current.isEmpty()) {
    		return new EmptyListNode();
    	}if (current.next().isEmpty()) {
    		return new NonemptyListNode(current.item(), prev);
    	}else 
    		wow = new NonemptyListNode(current.item(), prev);
    	return reverseHelper(wow, current.next());
    	}
    
    public AbstractListNode reverse() {
    	AbstractListNode thisCopy = this;
    	if (this.isEmpty()) {
    		return new EmptyListNode();
    	}if (this.next().isEmpty()){
    		return new NonemptyListNode(this.item());
    	}
    	AbstractListNode newtail = new NonemptyListNode(this.item());
    	return reverseHelper(newtail, this.next());
    	}
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	if (list2.isEmpty()) {
    		return this;
    	}
    	while (list2.isEmpty() == false) {
    		this.add(list2.item());
    		list2.next();
    	}
    	
    	return this;
    }
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        if (a.isEmpty()) return b;
        if (b.isEmpty()) return a;
        if ((Integer) a.item() < (Integer) b.item()) {
        	a = new NonemptyListNode(a.item(), merge(a.next(), b));
        	return a;
        } else {
        	b = new NonemptyListNode(b.item(), merge(b.next(), a));
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
    	int count = 0;
    	AbstractListNode currentNode = this;
    	while (currentNode.isEmpty() == false) {
    		currentNode = currentNode.next();
    		count++;
    	} return count + 1;
    }
    
    public Comparable get(int i) {
    	int n = 0;
    	Comparable currentItem = this.myItem;
    	AbstractListNode currentNode = this;
    	while (i > n && currentNode.next().isEmpty() == false) {
    		if (currentNode.item() == null) {
    			throw new IllegalArgumentException("Position is out of range.");
    		}
    		currentNode = currentNode.next();
    		currentItem = currentNode.item();
    		n++;	
    	}
		return currentItem;
    }
    
    public String toString() {
    	String myString = "";
    	AbstractListNode currentNode = this;
    	while (currentNode.isEmpty() == false) {
    		myString = myString + currentNode.item() + " ";
    		currentNode = currentNode.next();
    	}
		return "( " + myString + ")";
    }
    
    public boolean equals(Comparable arg) {
    	AbstractListNode currentNode;
    	AbstractListNode argCurrentNode;
    	if (arg instanceof NonemptyListNode) {
    		NonemptyListNode node = (NonemptyListNode) arg;
    		if (this.size() == node.size()) {
    			currentNode = this;
    			argCurrentNode = node;
    			while (currentNode.isEmpty() == false) {
    				if (currentNode.item() == argCurrentNode.item()) {
    					currentNode = (AbstractListNode) currentNode.next();
    					argCurrentNode = (AbstractListNode) argCurrentNode.next();
    				} else
    					return false;
    			} return true;
    		} return false;
    	}
		return false;
    }
    
    public Comparable smallest() {
  	  if (isEmpty()) {
  	    throw new NoSuchElementException("Can't find smallest in empty list.");
  	  }
  	  NonemptyListNode currentNode = this;
  	  Comparable smallestSoFar = this.item();
  	  while (currentNode.next() != null) {  
  		  currentNode.smallestHelper(smallestSoFar);
  	  }
  	  return smallestSoFar;
  	}

  	public Comparable smallestHelper(Comparable smallestSoFar) {
  	  smallestSoFar = min(smallestSoFar, this.next().item());
  	  return smallestSoFar;
  	}

  	public static Comparable min(Comparable c1, Comparable c2) {
  	  if (c1.compareTo(c2) < 0) {
  	    return c1;
  	  } else {
  	    return c2;
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
    	return 1;
    }
    
    public Comparable get(int i) {
    	return null;
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(Comparable arg) {
    	if (arg instanceof EmptyListNode) {
    		return true;
    	} else
    		return false;
    }
    public Comparable smallest() {
  	  if (isEmpty()) {
  	    throw new NoSuchElementException("Can't find smallest in empty list.");
  	  }
  	  return null;
  	}
    
}


