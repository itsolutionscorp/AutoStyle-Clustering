import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString(boolean first);
    abstract public boolean equals(Object other);
    abstract public Comparable smallest();
    abstract public Comparable smallestHelper(Comparable smallestSoFar);
    abstract public AbstractListNode add(Comparable c);
    abstract public void setNext(AbstractListNode next);
    abstract public void setItem(Comparable item);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode getLastNode();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);

    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        
        AbstractListNode pointer;
        if (a.item().compareTo(b.item()) == -1) {
            pointer = (AbstractListNode) a.item();
            a = a.next();
        }else{
            pointer = (AbstractListNode) b.item();
            b = b.next();
        }
        
        AbstractListNode headPointer = pointer;
        while (!a.isEmpty() && !b.isEmpty()) {
            if (a.item().compareTo(b.item()) == -1) {
                pointer.setNext((AbstractListNode) a.item());
                a = a.next();
            } else {
                pointer.setNext((AbstractListNode) b.item());
                b = b.next();
            }
            pointer = pointer.next();
        }
        if (a.isEmpty()) {
            pointer.setNext(b);
        } else{
            pointer.setNext(a);
        }
        return headPointer;
    }

    

    // Every other list-processing method goes here.

}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;
    
    public AbstractListNode add(Comparable c) {
    	AbstractListNode copy = this;
    	if (copy.isEmpty()) {
    		AbstractListNode endCopy= new NonemptyListNode(c, new EmptyListNode());
    		copy.setNext(endCopy);
    		return copy;
    	} else {
    		copy = new NonemptyListNode(copy.item(), copy.next().add(c));
    		return copy;
    	}
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	AbstractListNode copy = this;
    	if (copy.isEmpty()) {
    		AbstractListNode secondHalf = new NonemptyListNode(list.item(), list.next());
    		copy.setNext(secondHalf);
    		return copy;
    	} else {
    		copy = new NonemptyListNode(copy.item(), copy.next().append(list));
    		return copy;
    	}
    }
    
    public AbstractListNode reverse() {

    	AbstractListNode reversed = new EmptyListNode();
    	for (int i = this.size()-2; i >= 0; i--)
    		reversed = reversed.add(this.get(i));
    	return reversed;

    }

    public AbstractListNode appendInPlace (AbstractListNode list2) {
    	this.getLastNode().setNext(list2);
    	return this;
    }
    
    public void setNext(AbstractListNode next) {
    	myNext = next;
    }
    
    public void setItem(Comparable item) {
    	myItem = item;
    }
    	 
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(myItem);
    	}

    public Comparable smallestHelper(Comparable smallestSoFar) {
    	if (myNext.isEmpty()) {
    		return smallestSoFar;
    	} else if (min(myNext.item(), smallestSoFar) != smallestSoFar) {
    		  return myNext.smallestHelper(myNext.item());
    	} else {
    		  return myNext.smallestHelper(smallestSoFar);
    	}
    }
    
    public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
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
    	return myNext.size() + 1;
    }
    
    public Comparable get(int pos) {
    	if (pos == 0) {
    		return myItem;
    	} else {
    		return myNext.get(pos-1);
    	}
    }
    
    public AbstractListNode getLastNode(){
    	if (myNext.isEmpty())
    		return this;
    	else
    		return myNext.getLastNode();
    }
    
    public String toString(){
    	return this.toString(true);
    }
     //First is set to true in the first call to toString.
    public String toString(boolean first) {
    	if (first) {
    		return "( " + myItem + myNext.toString(false);
    	} else {
    		return " " + myItem + myNext.toString(false); 
    	}
    }
    
    public boolean equals(Object other) {
        AbstractListNode test;
        try{
         	test = (AbstractListNode)other;
         }
         catch (ClassCastException cce){
         	throw new IllegalArgumentException("Argument must be a linked list");
         }
        if (this.size() != test.size())
        	return false;
        if (!test.item().equals(myItem)) {
        	return false;
        } else {
        	return this.myNext.equals(test.next());
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
    
    public Comparable get(int pos) {
    	throw new IllegalArgumentException("Position out of bounds.");
    }
    
    public AbstractListNode getLastNode(){
    	return new EmptyListNode();
    }
    
    public String toString() {
    	return "( )";
    }
    
    public String toString(boolean first) {
    	return " )";
    }
    
    public boolean equals(Object other) {
    	if (this.size() != ((AbstractListNode) other).size()) {
    		return false;
    	} else if (((AbstractListNode) other).isEmpty()) {
    		return true;
    	} else {
    		return false;
    	}
    }

	public Comparable smallest() {
		return null;
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
		return null;
	}
    	
	public AbstractListNode add(Comparable c) {
    	NonemptyListNode copy = new NonemptyListNode(c, new EmptyListNode());
    	return copy;
    }

	public void setNext(AbstractListNode next) {
	}
	
	public void setItem(Comparable item) {

	}

	public AbstractListNode append(AbstractListNode list) {
		return list;
	}

	public AbstractListNode reverse() {
		return new EmptyListNode();
	}

	public AbstractListNode appendInPlace (AbstractListNode list2) {
		return list2;
	}
	}
