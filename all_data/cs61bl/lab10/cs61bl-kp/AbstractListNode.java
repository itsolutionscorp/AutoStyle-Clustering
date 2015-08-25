import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int x);
    abstract public String toString();
    abstract public boolean listequals(AbstractListNode m);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
	abstract public void setNext(AbstractListNode list2);
	
    // Every other list-processing method goes here.
	public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
		if (a.isEmpty()) {
			return b;
		} else if (b.isEmpty()) {
			return a;
		} 
		if (min(a.item(), b.item()) == a.item()) {
			a.setNext(merge(a.next(), b));
			return a;
		} else {
			b.setNext(merge(a, b.next()));
			return b;
		}
	}
	
    public Comparable smallest() {
    	  if(this.next().isEmpty()) {
    		  return this.item();
    	  } else {
    		  return min(this.item(), this.next().smallest());
    	  }
    	}

  	  	public Comparable smallestHelper(Comparable smallestSoFar) {
  	  	  return min(this.item(), smallestSoFar);
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

    public NonemptyListNode (Comparable item) {
        this (item, new EmptyListNode());
    }

    public Comparable item() {
        return myItem;
    }
    
    public void setItem(Comparable blah) {
    	this.myItem = blah;
    }

    public AbstractListNode next() {
        return myNext;
    }
    
    public void setNext(AbstractListNode blah) {
    	this.myNext = blah;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public int size() {
    	return 1 + this.next().size();
    }
    
    public Comparable get(int x) {
    	if (x == 0){
    		return this.item();
    	}
    	return this.next().get(x-1);
    }
    
    public String toString(){
    	String rtn = "( ";
    	AbstractListNode currNode = this;
    	while (!currNode.isEmpty()) {
    		rtn = rtn + currNode.item().toString() + " ";
    		currNode = currNode.next();
    	}
    	return rtn + ")";
    }
    
    public boolean listequals(AbstractListNode m){
    	if (!(m instanceof AbstractListNode)) {
    		return false;
    	}
    	if (((AbstractListNode) m).isEmpty()) {
    		return false;
    	}
    	if (!(this.item().equals(((AbstractListNode) m).item()))) {
    		return false;
    	}
    	return this.next().listequals(((AbstractListNode) m).next());
    }
    

	  	
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(this.item(), this.next().add(c));
	}
	
	public AbstractListNode append(AbstractListNode list) {
		return new NonemptyListNode(this.item(), this.next().append(list));
	}
	
	public AbstractListNode reverse() {
		return this.next().reverse().add(this.item());
	}
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		if (this.next().isEmpty()){
			AbstractListNode rtn = new NonemptyListNode(this.item());
			rtn.setNext(list2);
			return rtn;
		}
		return new NonemptyListNode(this.item(), this.next().appendInPlace(list2));
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
    
    public void setNext(AbstractListNode list2) {
    	throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public int size() {
    	return 0;
    }
    
    public Comparable get(int x) {
    	throw new IllegalArgumentException ("There is no 'item' to get from an EmptyListNode.");
    }
    
    public String toString(){
    	return "( )";
    }
    
    public boolean listequals(AbstractListNode m){
    	if (!(m instanceof AbstractListNode)) {
    		return false;
    	}
    	return (((AbstractListNode) m).isEmpty());
    }
    public Comparable smallest() {
    	throw new NoSuchElementException("Can't find smallest in empty list.");
    }
    
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(c);
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	return list;
    }
    
    public AbstractListNode reverse() {
    	return new EmptyListNode();
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	return list2;
    }

}
