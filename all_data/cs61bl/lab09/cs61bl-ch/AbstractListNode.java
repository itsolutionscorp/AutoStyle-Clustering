abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public String toString();
    abstract public AbstractListNode get(int i);
    abstract public int size();
    
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


    public String toString(){
    	String s = "( ";
    	AbstractListNode e = this;
    	int c = 0;
    	s += e.item().toString();
    	
    	while(e.next()!=null && c<e.size()){
    		s += " ";
    		c += 1;
    		e = e.next();
    		s += e.item();
    	}
       	s += " )";
       	return s;
        
    }
    
    public AbstractListNode get(int i){
    	int counter = 0;
    	
    	if(this.size()<=i || i<0){
    		throw new IllegalArgumentException("Go and ...");
    	}
    	
    	AbstractListNode e = this;
    	if(i==0){
    		return e; 
    	}else{
    		while(e.next().size() !=0){
    			counter += 1;
    			e = e.next();
    			if(counter==i){
    				return e;
    			}
    		}
    	}
    	return e;
    }

    public int size() {
		int count = 0;
		while (true) {
			if (this.next() != null) {
				if (count == 0) {
					count = this.next().size() + 1;
				} else {
					return count;
				}
			} else {
				return count;
			}

		}
	}
    	
    public static void main(String[] s){
    	
    	
    	
    }

    public boolean equals(AbstractListNode e){    	
    	return this.toString().equals( e.toString() ) ;
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

    public String toString(){
    	return "()";
    }
    
    public EmptyListNode get(int i){
    	return this;
    }
    
    public int size(){
    	return 0;
    }
    
    public boolean equals(EmptyListNode e){    	
    	return this.toString().equals( e.toString() ) ;
    }
    
    
    
}




