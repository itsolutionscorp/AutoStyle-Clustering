abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Object get(int x);
    abstract public String toString();
    abstract public boolean equals(Object a);
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

    public int size(){
    	if (myNext == null) {
    		return 0;
    	}
    	else{
    		return 1 + myNext.size();
    	} 
    }
    
    public Object get(int x) {
    	int count = -1;
    	for(NonemptyListNode temp = this; temp != null; temp = (NonemptyListNode)temp.next()){ 	
    		 count++;
    		 if(count == x){
    			 return temp.item();
    		 }
    	}
		throw new IllegalArgumentException("position out of range.");
    }

    public String toString(){
    	String s = "( ";
    	for(NonemptyListNode temp = this; temp != null; temp =(NonemptyListNode) temp.next()){
    		s = s + temp.item() + " ";
    	}
    	s = s + " )";	
    	return s;
    }

    public boolean equals(Object a){
    	
    	if(a instanceof NonemptyListNode){ 
    		NonemptyListNode b = (NonemptyListNode)a;
			for(NonemptyListNode temp = this; temp != null; temp = (NonemptyListNode)temp.next()){
				if(!temp.item().equals(((NonemptyListNode) b).item())){
					return false;
				}

				b =(NonemptyListNode) b.next();
				}
			if(b == null){
				return true;
			}
		 }
			
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
    
    public int size(){
    	return 0;
    }
    
    public Object get(int x) {
    	throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public String toString(){
    	return "()";
    }
    
    public boolean equals(Object a) {
    	if(a instanceof EmptyListNode){
    		return true;
    	}else{
    		return false;
    	}
    }
 }

