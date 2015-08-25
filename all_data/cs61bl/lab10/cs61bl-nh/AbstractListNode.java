import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public void setNext(AbstractListNode nextNode);
    abstract public Comparable get(int pos);
    abstract public String toString();
    
    public AbstractListNode add (Comparable c){
    	if (this.isEmpty()) {
    		AbstractListNode newNode = new NonemptyListNode(c, new EmptyListNode());
    		return newNode;
    	} else {
    		AbstractListNode newNode = new NonemptyListNode(this.item(), this.next().add(c));
    		return newNode;
    	}
    }
    public AbstractListNode append(AbstractListNode list) {
    	
    	AbstractListNode updated = this.copy();
    	AbstractListNode toAdd = list.copy();
    	AbstractListNode first, last;
    	
    	first = updated;

    	if (list.isEmpty()) {
    		return updated;
    	}
    	while (!updated.next().isEmpty()) {
    		updated = updated.next();
    	}
    	last = updated;
    	last.setNext(list);

        return first;
    }
    public AbstractListNode reverse() {
    	AbstractListNode updated = this.copy();
    	AbstractListNode first, last, empty, prev;
    	
    	if (this.isEmpty()) {
    		return updated;
    	}
    	
    	first = updated;
    	while (!updated.next().isEmpty()){
    		updated = updated.next();
    	}
    	empty = updated.next(); // extract out the first and last list node and resetting updated to the first node
    	last = updated;
    	updated = first;
    	while (!first.next().isEmpty()) {
    		while (!updated.next().next().isEmpty()) {
    			updated = updated.next();
    		}
    		prev = updated;
    		updated = updated.next();
    		updated.setNext(prev);
    		prev.setNext(empty);
    		updated = first;
    	}
    	return last;
    }
    
    public AbstractListNode copy() {
    	if (this.isEmpty()) {
    		return new EmptyListNode();
    	} else {
    		AbstractListNode newNode = new NonemptyListNode(this.item(), this.next().copy());
    		return newNode;
    	}
    }
    
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        AbstractListNode emptyA, currentNode, firstNode;
    	
    	if (a.isEmpty()) {
        	return b;
        }
        if (b.isEmpty()) {
        	return a;
        }
        firstNode = smallerNode(a,b);
        if (firstNode == a) {
        	a=a.next();
        } else {
        	b=b.next();
        }
        currentNode = firstNode;
        while (!a.isEmpty() && !b.isEmpty()) {
        	if (smallerNode(a,b)==a) {
        		currentNode.setNext(a);
        		currentNode = a;
        		a = a.next();
        	} else {
        		currentNode.setNext(b);
        		currentNode = b;
        		b = b.next();
        	}
        	if (a.isEmpty()) {
        		currentNode.setNext(b);
        	} else {
        		currentNode.setNext(a);
        	}        	
        }
        return firstNode;
    }
    
    public Comparable smallest() {
    	Comparable smallest;
    	AbstractListNode nextItem = this;
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  } else {
    		  smallest = this.item();
    	  }
    	  while (!nextItem.isEmpty()) {
    		  smallest = smallestHelper(smallest);
    		  nextItem = nextItem.next();
    	  }
    	  return smallest;
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    	  return min(smallestSoFar, next().item());
    	}

    	public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    	public static AbstractListNode smallerNode(AbstractListNode c1, AbstractListNode c2) {
      	  if (c1.item().compareTo(c2.item()) < 0) {
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

    public NonemptyListNode (Comparable item) {
        this (item, new EmptyListNode());
    }

    public Comparable item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }
    public void setNext(AbstractListNode nextNode) {
    	myNext = nextNode;
    }
    
    public boolean isEmpty() {
        return false;
    }

	public int size() {
		int count=1;
		NonemptyListNode temp = new NonemptyListNode(myItem, myNext);
		temp = this;

		while (!temp.next().isEmpty()) {
			count++;
			temp = (NonemptyListNode)temp.next();
		}
		return count;
	}
    public Comparable get(int pos) {
    	AbstractListNode A = this;

    	if (pos<0)
    		throw new IllegalArgumentException("Position is invalid");
    	while (pos>=0) {
    		if (pos==0) {
    			return A.item();
    		}
    		A = A.next();
    		
    		if (A.isEmpty()) {
    			throw new IllegalArgumentException("Position is invalid");
    		}
    		pos--;
    	}
    	return null;
    }

	public String toString() {
		AbstractListNode A = this;
		String endString = "( ";
		while (!A.isEmpty()) {
			endString = endString +  A.item() + " ";
			A = A.next();
		}
		endString = endString + ")";
		return endString;
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
    public void setNext(AbstractListNode nextNode) {
 
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
		return "()";
	}

}
