package lab09;

abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int index);

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
    	if(myNext instanceof EmptyListNode){
    		return 1;
    	}else{return 1+myNext.size();}
    	
    }
    public Object get(int index){
    	Object check;
    	
		
			if(index==0){check = this.item();}else{
					check = myNext.get(index-1);
				}
			return check;
		}
    	
    
    public String toString(){
        if(myNext instanceof EmptyListNode){
        	return"("+" "+myItem.toString()+" "+")";
        }else{
        	String sort ="("+" "+myItem.toString()+myNext.toString().substring(1);
        			
        		return sort;}
        	}
    public boolean equals(Object com){
    	
    if(com instanceof NonemptyListNode){if(this.size()==((NonemptyListNode)com).size()){
    		
    			if(item().equals(((NonemptyListNode)com).item())){
    				return this.next().equals(((NonemptyListNode) com).next());
    			}else{return false;}
    		}else{return false;}}else{return false;}
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
    public Object get(int index){
    	throw new 
    	IllegalArgumentException("No elements in this index");
    }
 public String toString(){
	 return "()";
 }
 public boolean equals(Object com){
	 if (com instanceof EmptyListNode){
		 return true;
		 
	 }else{return false;}
 }
}

