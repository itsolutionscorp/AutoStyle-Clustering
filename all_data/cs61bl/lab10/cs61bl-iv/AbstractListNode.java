import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public boolean equals(Comparable o);
   	abstract public AbstractListNode add (Comparable c);
   	abstract public AbstractListNode append(AbstractListNode list);
   	abstract public AbstractListNode reverse();
   	abstract public void setNext(AbstractListNode list);
   	abstract public void setItem(Comparable item);

    // Every other list-processing method goes here.
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(this.item()) ;
    	}

    public Comparable smallestHelper(Comparable smallestSoFar) {
   		for (int i = 0; i < this.size(); i++) {
   			smallestSoFar = min(this.next().item(), smallestSoFar);
   		}
   		return smallestSoFar;    	
   		}

    public static Comparable min(Comparable c1, Comparable c2) {
   	  if (c1.compareTo(c2) < 0) {
   	    return c1; 
   	  } 
   	  else {
   	    return c2;    	  
   	  }
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	if (this.isEmpty()) {
    		return list2;
    	}
    	else if (!next().isEmpty()) {
    		return next().append(list2);
    	}
    	else {
    		this.setNext(list2);
    		return this;
    	}
    }
    
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
    	if (a.isEmpty()) {
    		return b;
    	}
    	else if (b.isEmpty()) {
    		return a;
    	}
    	else if ((Integer) a.item() > (Integer) b.item()) {
    		AbstractListNode temp = b.next();
    		b.setNext(a);
    		return merge(a,temp);
    	}
    	else { //((Integer) a.item() < (Integer) b.item())
    		AbstractListNode temp = a.next();
    		a.setNext(b);
    		return merge(temp,b);
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
    	return (1 + this.next().size());
    }
    
    public Comparable get(int pos) {
    	if (pos == 0) {
    		return this.item();
    	} else if (pos < 0) {
    		throw new IllegalArgumentException("Out of bounds.");
    	} else {
    		return this.next().get(pos - 1);
    	}
    }
    
    public String toString() {
    	String result = "( ";
    	for (int i = 0; i < this.size(); i++) {
    		if (!this.isEmpty()) {
    			result = result + this.get(i) + " ";
    		}
    	}
    	result = result + ")";

    	return result;
    }
    
    public boolean equals(Comparable o) {
    	if (this.size() == ((AbstractListNode) o).size()) {
    		for (int i = 0; i < this.size(); i++) {
    			if (this.get(i) != ((AbstractListNode) o).get(i)) {
    				return false;
    			}
    		}
    		return true;
    	}
    	return false;
    }
    
   	public AbstractListNode add(Comparable c){
   		return (new NonemptyListNode(this.item(), this.next().add(c)));
    }
    
   	public AbstractListNode append(AbstractListNode list) {
   		if (list.isEmpty() && !next().isEmpty()) {
   			return new NonemptyListNode(item(), next().append(list));
   		}
   		else if (list.isEmpty() && next().isEmpty()) {
   			return new NonemptyListNode(item(), new EmptyListNode());
   		} 
   		else if (!list.isEmpty() && !next().isEmpty()) {
   			return new NonemptyListNode(item(), next().append(list));
   		}
   		else {
   			return new NonemptyListNode(item(), list.append(new EmptyListNode()));
   		}
   	}
   	
   	public AbstractListNode reverse() {
   		AbstractListNode result = new EmptyListNode();
   		for (int i = 0; i < size(); i++) {
   			result = new NonemptyListNode(get(i), result);
   		}
   		return result;
   	}
    
   	public void setNext(AbstractListNode list) {
   		this.myNext = list;
   	}

   	public void setItem(Comparable item) {
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
    
    public int size() {
    	return 0;
    }
    
    public Comparable get(int pos) {
    	if (pos == 0) {
    		return null;
    	} else {
    		throw new IllegalArgumentException ("Out of bounds");
    	}
    }
    
    public String toString() {
    	return("( )");
    }
    
    public boolean equals(Comparable o) {
    	if (((AbstractListNode) o).isEmpty()) {
    		return true;
    	}
    	return false;
    }
    
   	public AbstractListNode add(Comparable c){
        return new NonemptyListNode(c,this);
    }
   	
   	public AbstractListNode append(AbstractListNode list) {
   		if (list.isEmpty()) {
   			return new EmptyListNode();
   		}
   		else if (!list.next().isEmpty()) {
   			return list.append(new EmptyListNode());
   		}
   		else {
   			return (new NonemptyListNode(list.item(),new EmptyListNode()));
   		}
   	}
   	
   	public AbstractListNode reverse() {
   		return new EmptyListNode();
   	}
   	
   	public void setNext(AbstractListNode list) {
   		return;
   	}
   	
   	public void setItem(Comparable item) {
   		return;
   	}
}
