import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public AbstractListNode add(Comparable C);
    
    abstract public AbstractListNode setNext(AbstractListNode list);
    abstract public void setItem(Comparable item);
    

    public AbstractListNode reverse() {
    	if (this.isEmpty()) {
    		return this;
    	} else {
    	AbstractListNode rest = next().reverse();
    	AbstractListNode list2 = rest.add(item());
    	return list2;
    	}
    	
    	}
    
    public AbstractListNode helperAppend(AbstractListNode list1, AbstractListNode list2) {
    	if (!list1.isEmpty()) {
    		AbstractListNode newList = new NonemptyListNode(list1.item(), helperAppend(list1.next(), list2));
    		return newList;
    	} else if (!list2.isEmpty()) {
    		AbstractListNode newList = new NonemptyListNode(list2.item(), helperAppend(list1, list2.next()));
    		return newList;
    	} else {
    		return new EmptyListNode();
    	}
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if (this.isEmpty()) {
    		return list;
    	}
    	helperAppend(this, list).toString();
    	return helperAppend(this, list);
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list) {
    	if (this.isEmpty()) {
    		return list;
    	} else {
    		if (!this.next().isEmpty()) {
    			return this.next().appendInPlace(list);
    		} else {
    			return this.setNext(list);
    		}
    	}
    }
    
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b){
    	
    	if (a.isEmpty()) {
    		return b;
    	} else if (b.isEmpty()) {
    		return a;
    	}
    	if ((Integer)a.item() >= (Integer)b.item()) {
    		return b.setNext(merge(a, b.next()));
    	} else  {
    		return a.setNext(merge(a.next(), b));
    	} 
    }

    // Every other list-processing method goes here.

    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(item()) ;
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    		try {
    			return min(smallestSoFar, next().smallest());
    		}
    		catch (NoSuchElementException e) {
    			return smallestSoFar;
    		}
    	}

    	public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    
    public int size() {
    	if (isEmpty()) {
    		return 0;
    	} else {
    		return 1 + next().size();
    	}
    }
    
    public Comparable get(int position) {
    	if (isEmpty()) {
    		throw new IllegalArgumentException();
    	} else if (position == 0) {
    		return item();
    	} else {
        	if (next().isEmpty()) {
        		throw new IllegalArgumentException();
        	} else {
    		return next().get(position - 1);
        	}
    	}
    }
    
    public String toString() {
    	String returnString = "(";
    	for (int i = 0; i < size(); i++){
    		returnString += " " + get(i);
    	}
    	return returnString + " )";

    }
    
    public boolean equals(AbstractListNode lst) {
    	return this.toString().equals(lst.toString());
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
    		AbstractListNode newList = new NonemptyListNode(item(), next().add(c));
    	return newList;
    }
    
    public AbstractListNode setNext(AbstractListNode list){
    	this.myNext = list;
    	return this;
    }
    public void setItem(Comparable item){
    	this.myItem = item;
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
    	AbstractListNode endList = new NonemptyListNode(c, new EmptyListNode());
    	return endList;
    }

}
