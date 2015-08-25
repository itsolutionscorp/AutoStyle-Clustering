import java.util.NoSuchElementException;


abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int index);
    abstract public void setItem(Comparable item);
    abstract public void setNext(AbstractListNode item);
    
    


    // Every other list-processing method goes here.
    public Comparable smallest() {
  	  if (isEmpty()) {
  	    throw new NoSuchElementException("Can't find smallest in empty list.");
  	  }
  	  return this.next().smallestHelper(this.item()) ;
  	}

  	public Comparable smallestHelper(Comparable smallestSoFar) {
  		if(this.isEmpty()){return smallestSoFar;}else{
  	   return  this.next().smallestHelper(min(this.item(),smallestSoFar));}
  	}

  	public static Comparable min(Comparable c1, Comparable c2) {
  	  if (c1.compareTo(c2) < 0) {
  	    return c1;
  	  } else {
  	    return c2;
  	  }
  	}
     public AbstractListNode add(Comparable c){
    	if(this.size()==0){return new NonemptyListNode(c);}
    	else{
    		return new NonemptyListNode(this.item(),this.next().add(c));
    	}
     } 
     public AbstractListNode append(AbstractListNode list) {
    	if(list.size()==0){
    		return this;
    	}
    	if(this.size() ==0){
    		return list;
    	}
    	return new NonemptyListNode(this.item(),this.next().append(list));
    	}
     public AbstractListNode reverse(){
    	 if(this.size()==0){
    		 return new EmptyListNode();
    	 }
    	 if(this.size()==1){
    		 return new NonemptyListNode(this.item());
    	 }
    	 AbstractListNode copy=this;
    	 Comparable first;
    	
    		first= copy.item();
    		copy=copy.next().reverse();
    		return copy.add(first);
    	}
     public AbstractListNode appendInPlace(AbstractListNode list2){
    	    if(this.size()==0){ 
    	    	return list2;}
    	    if(this.size()==1){
    	    	(this).setNext(list2);}
    	    else{
    	    	this.next().appendInPlace(list2);}
    	    
    	    return this;
    	    
    	    	
    	    
    	
     }
     public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
    	   if(a==null||b==null){
    		   throw new IllegalStateException();
    	   }
    	   AbstractListNode head;
    	   if(a.isEmpty()){
    		   return b;
    		   
    	   }
    	   if(b.isEmpty()){
    		   return a;
    	   }
    	   if(a.item().compareTo(b.item())<0){
    		a.setNext(merge(a.next(),b));
    		return a ;
    		   }else{
    			   if(a.item().compareTo(b.item())>0){
    				   b.setNext(merge(b.next(),a));
    				   return b;
    			   }
    			   AbstractListNode tail = merge(b.next(),a.next());
    			
    			   b.setNext(tail);
    			   return b;
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
    	String sort;
    	if(this.isEmpty()){
    		return "( )";
    	}
        if(myNext instanceof EmptyListNode){
        	return"("+" "+myItem.toString()+" "+")";
        }else{
        	sort ="("+" "+myItem.toString()+myNext.toString().substring(1);
        			
        		}
        return sort;
        	}
    public boolean equals(Object com){
    	
    if(com instanceof NonemptyListNode){if(this.size()==((NonemptyListNode)com).size()){
    		
    			if(item().equals(((NonemptyListNode)com).item())){
    				return this.next().equals(((NonemptyListNode) com).next());
    			}else{return false;}
    		}else{return false;}}else{return false;}
    	}
   
    public void setItem(Comparable item){
		myItem=item;
	}
	public void setNext(AbstractListNode item){
		myNext = item;
		
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
    public int size(){
    	return 0;
    	
    }
    public Object get(int index){
    	throw new 
    	IllegalArgumentException("No elements in this index");
    }
 public String toString(){
	 return "( )";
 }
 public boolean equals(Object com){
	 if (com instanceof EmptyListNode){
		 return true;
		 
	 }else{return false;}
 }
 public void setItem(Comparable item){
	
	 
 }
public void setNext(AbstractListNode item){
	 
 }
}
 


