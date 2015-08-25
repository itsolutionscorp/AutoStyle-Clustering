abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public String toString();
    abstract public boolean equals(Object o);
    public Object get(int i) throws IllegalArgumentException{
    	int k = 0;
    	Object temp1 = this.item();
    	AbstractListNode temp2 = this.next();
    	while (k < i) {
    		if (temp2 == null) {
    			throw new IllegalArgumentException();
    		}
    		temp1 = temp2.item();
    		temp2 = temp2.next();
    		
    		k++;
    	}
    	return temp1;
    }
    // Every other list-processing method goes here.

}

class NonemptyListNode extends AbstractListNode {

    private Object myItem;
    private AbstractListNode myNext;

    public NonemptyListNode (Object item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    public NonemptyListNode (Object item) {
        this (item, new EmptyListNode());
    }

    public Object item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }
    
    public boolean isEmpty() {
        return false;
    }
    public int size() {
    	return 1 + next().size();
    }
  /*  public Object get(int i) {
    	int k = 0;
    	if (i == 0) {
    		return item();
    	}
    	NonemptyListNode temp = (NonemptyListNode) this.next();
    	while (k < i-1) {
    		temp = (NonemptyListNode) temp.next();
    		k++;
    	}
    	return temp.item();
    }*/
    public String toString() {
    	String par = "";
    	Object first = this.item();
    	NonemptyListNode thiss;
    	if (this.next().isEmpty()) {
    		return "( " + first + " )";
    	} else { 
    		thiss = (NonemptyListNode) this.next();
    		while (!(thiss.next().isEmpty())) {
        		par = par + " " + thiss.item();
        		thiss = (NonemptyListNode)thiss.next();
    	}
    	return "( " + first + par + " " + thiss.item() + " )";
    	}
    }
    public boolean equals(Object o) {
    	return (this.item() == ((AbstractListNode) o).item() && this.size() == ((AbstractListNode) o).size() 
    			&& this.next().equals(((AbstractListNode) o).next()));
    }
}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public Object item() {
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
/*    public Object get(int i) {
    	return null;
    }*/
    public String toString() {
    	return "()";
    }
    public boolean equals(Object o) {
    	if (((AbstractListNode) o).isEmpty()) {
    		return true;
    	}
    	return false;
    }
}

