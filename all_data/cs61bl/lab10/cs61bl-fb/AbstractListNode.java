import java.util.NoSuchElementException;
abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public void setNext(AbstractListNode to_be_next);
    abstract public void setItem(Comparable to_be_item);
    abstract public boolean isEmpty();
    abstract public AbstractListNode append(AbstractListNode to_be_appended);
    abstract public AbstractListNode reverse(); 
    abstract public int size();
    abstract public Comparable get(int element_At);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode NodeCompared);
    abstract public AbstractListNode add(Comparable c);
    public static AbstractListNode merge(AbstractListNode a, AbstractListNode b){
        AbstractListNode original = a.appendInPlace(b);
        AbstractListNode scope = original;
        AbstractListNode microscope = null;;
        Comparable placeholder = null;
        for(int i = 0; i<original.size(); i++){
        	
        	if(!scope.smallest().equals(original.get(i))){
        		placeholder = original.get(i);
        		scope.setItem(scope.smallest());
        		
        		for(int j = i+1; j<original.size();j++){
        			microscope = microscope.next();
        			
        			if(microscope.get(0).equals(scope.smallest())){
        				microscope.setItem(placeholder);
        				
        			}
        		}
        		
        	}
        	scope = scope.next();
        	microscope = scope;
        	
        }
        
        
        return original;
        
    }
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(get(0));
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    	   Comparable rv = smallestSoFar;
    		for(int i = 0; i<size()-1; i++){
    		  
    			rv = min(rv, get(i+1));
    	  }
    	  return rv;
    	  
    	}
    	public static Comparable min(Comparable c1, Comparable c2) {
    	  	  if (c1.compareTo(c2) < 0) {
    	  	    return c1;
    	  	  } else {
    	  	    return c2;
    	  	  }
    	  	}
    	
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	
        AbstractListNode placeholder = this;
    	if(placeholder.isEmpty()){
        	return list2;
        }
        else{
        	while(!placeholder.next().isEmpty()){
        		placeholder = placeholder.next();
        	}
        	placeholder.setNext(list2);
            return this;
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
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	
        AbstractListNode placeholder = this;
    	if(placeholder.isEmpty()){
        	return list2;
        }
        else{
        	while(!placeholder.next().isEmpty()){
        		placeholder = placeholder.next();
        	}
        	placeholder.setNext(list2);
            return this;
        }
    }

    public void setNext(AbstractListNode to_be_next){
    	myNext = to_be_next;
    }
    @Override
    public void setItem(Comparable to_be_item){
    	myItem = to_be_item;
    }
    @Override
    public AbstractListNode add(Comparable c){
		if(this.size() == 0){
			return new NonemptyListNode(c,null);
		}else{
			return new NonemptyListNode(this.item(), this.next().add(c));
		}
		
    }
    @Override
    public AbstractListNode append(AbstractListNode to_be_appended){
    	if(this.next().size() == 0){
			return new NonemptyListNode(this.item(),to_be_appended);
		}else{
			return new NonemptyListNode(this.item(), this.next().append(to_be_appended));
		}
    }
    @Override
    public AbstractListNode reverse() {
    	//end of original ListNode, start of new ListNode
    	if(this.next().size()==0){
    		return new NonemptyListNode(this.item(),null);
    		
		}else{
			return this.next().reverse().add(this.item());
		}

    }
    public static Comparable min(Comparable c1, Comparable c2) {
  	  if (c1.compareTo(c2) < 0) {
  	    return c1;
  	  } else {
  	    return c2;
  	  }
  	}
    @Override
    public int size(){
        int count = 1;
    	AbstractListNode placeholder= this;
        while (placeholder.next().size()!=0){
        	placeholder = placeholder.next();
    		count++;
        }
        return count;
    }
    @Override
    public Comparable get(int element_At){
    	
    	AbstractListNode placeholder= this;
    	if(placeholder.size()-1 < element_At){
	    	throw new IllegalArgumentException("Index too large	");
	    }
    	for(int i=0; i<element_At;i++){
    	    
    		placeholder = placeholder.next();
    	    
    	}
        return placeholder.item();
    }
    @Override
    public String toString(){
    	String returnString = "( ";
    	AbstractListNode placeholder= this;
        while (placeholder.size()!=0){
        	returnString = returnString +""+ placeholder.item()+ " ";
        	placeholder = placeholder.next();
    		
        }
        returnString = returnString + ")";
        return returnString;
    }
    @Override
    public boolean equals(AbstractListNode NodeCompared){
    	if (this.size() == NodeCompared.size()){
    		for(int i = 0; i<=this.size()-1;i++ ){
    			if(!this.get(i).equals(NodeCompared.get(i))){
    				return false;
    			}
    		}
    	}
    	return true;
    	
    }

    public NonemptyListNode (Comparable item) {
        this(item, new EmptyListNode());
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

}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    @Override
    public AbstractListNode add(Comparable c){
		AbstractListNode placeholder = new NonemptyListNode(c,null);;
    	return placeholder;
    }
    @Override
    public void setNext(AbstractListNode to_be_next){
    
    }
    public void setItem(Comparable to_be_item){
    	
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
    public static Comparable min(Comparable c1, Comparable c2) {
  	  if (c1.compareTo(c2) < 0) {
  	    return c1;
  	  } else {
  	    return c2;
  	  }
  	}
    @Override
    public int size(){
    	return 0;
    }
    @Override
    public Comparable get(int element_At){
        throw new IllegalArgumentException("It's just empty brah");	
    }
    @Override
    public String toString(){
    	return new String("( )");
    }
    @Override
    public boolean equals(AbstractListNode NodeCompared){
        
        if (NodeCompared.size() == 0){
        	return true;
        }
    return false;
    }
    @Override
    public AbstractListNode append(AbstractListNode to_be_appended){
        return to_be_appended;	
        
    }
    @Override
    public AbstractListNode reverse() {
        throw new IllegalArgumentException("no reverse for empty list");
    }
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	
        AbstractListNode placeholder = this;
    	if(placeholder.isEmpty()){
        	return list2;
        }
        else{
        	while(!placeholder.next().isEmpty()){
        		placeholder = placeholder.next();
        	}
        	placeholder.setNext(list2);
            return this;
        }
    }
}  


