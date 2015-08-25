abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public String toBePrinted();
    abstract public boolean equals(AbstractListNode lst);


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
    public int size(){
    	return 1 + next().size();
    }
    
    public Object get(int pos) throws IllegalArgumentException{
    	if(pos == 0){
    		return this.item();
    	}
    	else{
    		if (this.next().isEmpty() == true){
    			throw new IllegalArgumentException("Out of Bound");
    		}
    		else{
    			return next().get(pos-1);
    		}
    
    	   
    	 
    	}
    }
    public String toBePrinted(){
    	if(this.next().isEmpty()){
    		return ""+this.item()+"";
    	}
    	else{
    		return this.item() + " "+next().toBePrinted();
    	}
    }
    public String toString(){
    	    return "(" +" " + this.toBePrinted() +  " "+")";      
    }
    public boolean equals(AbstractListNode lst){
      if(this.size() == lst.size()){
    	  int left = this.size();
    	  int pos =0;
    	  while(left != 0){
    		 if(this.get(pos) != lst.get(pos)){
    			 return false;
    		 }
    		 pos += 1;
    		 left -= 1;
    		 
    	  }
    	  return true;
      }
      else{
    	  return false;
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
    public int size(){
    	return 0;
    }
    public Object get(int pos) throws IllegalArgumentException{
    	throw new IllegalArgumentException("Out of Bound");
    	
    }
    public String toBePrinted(){
    	return "";
    }
    public String toString(){
    	return "()";
    }
    public boolean equals( AbstractListNode lst){
    	if(lst.size() !=0){
    		return false; 
    	}
    	else{
    		return true;
    	}

}
}
