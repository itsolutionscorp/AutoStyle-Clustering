import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public boolean equals(Object o);
    abstract public Comparable smallest();
    abstract public AbstractListNode add (Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    abstract public void setItem(Comparable c);
    abstract public void setNext(AbstractListNode n);

    // Every other list-processing method goes here.
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
        }
        else if (a.isEmpty() && !b.isEmpty()) {
        	return b;
        }
        else if (!a.isEmpty() && b.isEmpty()) {
        	return a;
        } else {
        	Comparable headItem = min(a.item(), b.item());
        	AbstractListNode ret;
        	if (headItem == a.item()) {
        		ret = a;
        		((NonemptyListNode) ret).setNext(merge(a.next(), b));
        	} else {
        		ret = b;
        		((NonemptyListNode) ret).setNext(merge(a, b.next()));
        	}
        	return ret;
        }
    }

}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;

    public NonemptyListNode (Comparable item, AbstractListNode next) {
        myItem = item;
        myNext = next;     
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
    
    public void setItem(Comparable c) {
    	myItem = c;
    }
    public void setNext(AbstractListNode n) {
    	myNext = n;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public int size() {
    	return 1 + myNext.size();
    }
    
    public Comparable get(int pos) {
    	if (pos > 0 && !(myNext instanceof EmptyListNode)) {
    		return myNext.get(pos - 1);
    	}
    	else if (pos == 0) {
    		return myItem;
    	} else {
    		throw new IllegalArgumentException("Position out of range!");
    	}
    }
    
    public String toString() {
    	NonemptyListNode helper = this;
    	String ret = new String();
    	ret = ret + "( " + myItem + " ";
    	while (!(helper.myNext instanceof EmptyListNode)) {
    		ret += ((NonemptyListNode) helper.myNext).myItem + " ";
    		helper = (NonemptyListNode) helper.myNext;
    	}
    	ret += ")";
    	return ret;
    }
    
    public boolean equals(Object o) {
    	if (this.size() != ((AbstractListNode) o).size()) {
    		return false;
    	}
    	NonemptyListNode helper = this;
    	if (helper.myItem == ((NonemptyListNode) o).myItem) {
    		while (!(((NonemptyListNode) o).myNext instanceof EmptyListNode)) {
    			helper = (NonemptyListNode) helper.myNext;
    			o = ((NonemptyListNode) o).myNext;
    			if (helper.myItem != ((NonemptyListNode) o).myItem) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public Comparable smallest() {
    	  return smallestHelper(myItem);
    	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
	  AbstractListNode temp = this.next();
	  Comparable smallest = smallestSoFar;
	  while (!(temp.isEmpty())) {
		  smallest = min(smallest, temp.item());
		  temp = temp.next();
	  }
	  return smallest;
	}
	
    public AbstractListNode add (Comparable c) {
        AbstractListNode ret = new NonemptyListNode(myItem);
        AbstractListNode retPointer = ret;
        AbstractListNode temp = this.next();
        while (!temp.isEmpty()) {
        	((NonemptyListNode) retPointer).myNext = new NonemptyListNode(temp.item());
        	retPointer = retPointer.next();
        	temp = temp.next();
        }
        ((NonemptyListNode) retPointer).myNext = new NonemptyListNode(c);
        return ret;
    }
    
    public AbstractListNode append(AbstractListNode list) {
        AbstractListNode ret = new NonemptyListNode(myItem);
        AbstractListNode retPointer = ret;
        AbstractListNode temp = this.next();
        while (!temp.isEmpty()) {
        	((NonemptyListNode) retPointer).myNext = new NonemptyListNode(temp.item());
        	retPointer = retPointer.next();
        	temp = temp.next();
        }
        while (!list.isEmpty()) {
        	((NonemptyListNode) retPointer).myNext = new NonemptyListNode(list.item());
	        retPointer = retPointer.next();
	        list = list.next();
        }
        return ret;
    }
    
    public AbstractListNode reverse() {
    	int size = this.size();
        Comparable[] items = new Comparable[size];
        int counter = 0;
        AbstractListNode temp = this;
        while (counter < size) {
        	items[counter] = temp.item();
        	temp = temp.next();
        	counter++;
        }
        counter--;
        AbstractListNode ret = new NonemptyListNode(items[counter]);
        AbstractListNode retPointer = ret;
        counter--;
        while (counter >= 0) {
        	((NonemptyListNode) retPointer).myNext = new NonemptyListNode(items[counter]);
        	retPointer = retPointer.next();
        	counter--;
        }
        return ret;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
        AbstractListNode temp = this;
        while (!temp.next().isEmpty()) {
        	temp = temp.next();
        }
        ((NonemptyListNode) temp).myNext = list2;
        return this;
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
    
    public void setItem(Comparable c) {
    	throw new IllegalArgumentException("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public void setNext(AbstractListNode n) {
    	throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public int size() {
    	return 0;
    }
    
    public Comparable get(int pos) {
    	throw new IllegalArgumentException("Position out of range!");
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(Object o) {
    	if (o instanceof EmptyListNode) {
    		return true;
    	}
    	return false;
    }
    
    public Comparable smallest() {
  	    throw new NoSuchElementException("Can't find smallest in empty list.");
    }
    
    public AbstractListNode add (Comparable c) {
    	AbstractListNode ret = new NonemptyListNode(c);
    	return ret;
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if (!list.isEmpty()) {
    		AbstractListNode ret = new NonemptyListNode(list.item());
            list = list.next();
	        while (!list.isEmpty()) {
	        	ret = ret.add(list.item());
	        	list = list.next();
	        }
	        return ret;
            
    	} else {
    		return new EmptyListNode();
    	}
    }
    
    public AbstractListNode reverse() {
        return new EmptyListNode();
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
        return list2;
    }

}
