import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    public int counter;
    public String ourString;
    public Comparable current;
    public AbstractListNode myNext;

    // Every other list-processing method goes here.
    
    public int size() {
    	if (isEmpty()) {
    		return 0;
    	}
    	else if (next() == null){
    		return 1;
    	}
    	else  {
    		return 1 + next().size();
    	}
    }
    
   public Object get(int position) {
    if (next() == null && position != 0) {
    	throw new IllegalArgumentException("Position out of range");
    		}
    	else if (position == 0) {
    		return item();
    	}
    	else {
    		next().get(position-1);
    	}
    	return item();
    } 
    	
    
   public String toString() {
   	if (next().isEmpty()) {
   		ourString = item() + " )";
   	}
   	else {
   		ourString = item() + next().toString().substring(1);
   	}
   	return "( " + ourString;
   }
    
    public boolean equals(Comparable o) {
    	if (this.item().equals(o)) {
    		return true; 
    	}
    	return false; 
    }
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(this.item());
    	}

    public Comparable smallestHelper(Comparable smallestSoFar) {
    	Comparable min = null; 
    	AbstractListNode current = this; 	  
    		while (!current.isEmpty()) {
    			if (min(smallestSoFar,current.next().item()).equals(smallestSoFar)) {
    				min = smallestSoFar; }
    				current = this.next(); 
    			}
    		return min;
    	}

    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
	
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(item(),next().add(c)); 
	}
	public AbstractListNode append(AbstractListNode list) {
	if (list.isEmpty()){
		return this;
	}
	else {
		return new NonemptyListNode(item(),next().append(list));
	}
}
	public AbstractListNode reverse() {
     	AbstractListNode reverse = new EmptyListNode();
     	for (int i = 1; i <= size(); i++) {
     		reverse = reverse.add((Comparable) get(size() - i));	
     	}
     	return reverse;
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
    	return new NonemptyListNode(myItem,next().add(c));
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if (list.isEmpty()){
    		return this;
    	}
    	else {
    		return new NonemptyListNode(myItem,next().append(list));
    	}
    }
    
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	if (myNext.isEmpty()) {
    		setNext(list2);
    	}
    	return this; 
    }
    
    public void setItem(Comparable c) {
    	myItem = c;
	}
    
    public void setNext(AbstractListNode node) {
    	myNext = node; 	
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
    
    public String toString() { 
   	 if (next().isEmpty()) {
       	ourString = item() + ")"; 
       	}
   	return "(" + ourString;
   }
    
    public Comparable smallest() {
    	return null; 
    }
    
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	return null; 
    }
    public AbstractListNode reverse() {
		return null;
	}
    
    public AbstractListNode add(Comparable c) {
        return new NonemptyListNode(c,null); 
       }
    
    public AbstractListNode append(AbstractListNode list) {
    	return list; 
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
       return list2; 
    }

}


