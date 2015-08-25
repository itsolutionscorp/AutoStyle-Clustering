abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    public int size() {
    	if (next().isEmpty()) {
    		return 1;
    	} else {
    		return 1 + next().size();
    	}
    }
    
    public Object get(int index) {
    	if (index==0){
    		return item();
    	} else {
    		return next().get(index-1);
    	}
    }
    
    public String stringHelper() {
    	if (!isEmpty() && !next().isEmpty()) {
    		return item().toString()  + " " + next().stringHelper();
    	} else if (!isEmpty() && next().isEmpty()) {
    		return item().toString();
    	} else {
    		return "";
    	}
    }
   public String toString() {
	   return "( " + stringHelper() + " )";
   }
   
   public boolean equalsHelper(AbstractListNode o, AbstractListNode p) {
	   if (!o.next().isEmpty()){
		   if (!(o.item().equals(p.item()))) {
			   return false;
		   } else {
			   return equalsHelper(o.next(), p.next());
		   }
	   } else {
		   return (o.item().equals(p.item()));
	   }
	   
   }
   
   public boolean equals(Object q) {
	   if (!(q instanceof AbstractListNode)) {
		   return false;
	   } else {
		  return equalsHelper((AbstractListNode) q, this); 
	   }
   }
   
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

}
