abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString(boolean first);
    abstract public boolean equals(Object other);

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
    	return myNext.size() + 1;
    }
    
    public Object get(int pos) {
    	if (pos == 0) {
    		return myItem;
    	} else {
    		return myNext.get(pos-1);
    	}
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
    	return 1;
    }
    
    public Object get(int pos) {
    	throw new IllegalArgumentException("Position out of bounds.");
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
    	

}
