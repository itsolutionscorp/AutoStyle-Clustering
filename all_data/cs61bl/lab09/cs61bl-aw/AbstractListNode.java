abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size(); 
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public boolean equals( AbstractListNode secondList);

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
    	AbstractListNode cycleThrough = this;
    	int sizeOfList = 0;
    	int end = 0;
    	while (end == 0 ) {
        	if (cycleThrough.isEmpty()) {end = 1; }
        	else {
        		sizeOfList++;
        		cycleThrough =   cycleThrough.next();
        	}
    	}
    	return sizeOfList; 
    }
    
    
    
    public Object get(int pos) {
    	AbstractListNode cycleThrough = this;//TODO: How do I make the variable Cycle through refer to itself?
    	for (int i = 0; i < pos ; i++) {
    		cycleThrough = cycleThrough.next();
    	}
    	
    	
    	return cycleThrough.item();
}
    
     public String toString() {
    	AbstractListNode cycleThrough = this;
     	String allTheStringElementsInTheList= "(";
     	int end = 0;
     	while (end == 0 ) {

         	if (cycleThrough.isEmpty()) {end = 1; }
         	
         	else {
        		
         		allTheStringElementsInTheList += cycleThrough.item() + " ";
         		cycleThrough =  cycleThrough.next();
         	}
     }
		allTheStringElementsInTheList += ")";
		System.out.println(allTheStringElementsInTheList);

 		return allTheStringElementsInTheList; 

     }
      public boolean equals( AbstractListNode secondList) {
      	AbstractListNode firstCycle = this;
    	AbstractListNode secondCycle = secondList;
    	while(this != null) {
    		  if (!(this == secondCycle)) {
    			  return false; 
    		  }
    		  firstCycle = firstCycle.next();
    		  secondCycle = secondCycle.next();
    	  }
    	return true; 
      }

}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    public boolean equals(AbstractListNode secondList) {
    	if ((this == null) && (secondList == null)) {
    		return true; 
    	} else {
    		return false; 
    	}
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
    public String toString() {
   	 return "";
    }
    public Object get(int pos) {
		return null;
	}

}

