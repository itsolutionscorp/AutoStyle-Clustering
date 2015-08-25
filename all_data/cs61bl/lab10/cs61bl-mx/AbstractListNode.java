import java.util.NoSuchElementException;
abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public boolean equals(Object o);
    abstract public String toString();
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public void setItem(Comparable c);
    abstract public void setNext(AbstractListNode lst);
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(this.item()) ;
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    		Comparable smallest = smallestSoFar;
    		AbstractListNode curr = this;
    		while(curr.next().isEmpty() != true){
    			smallest = min(curr.next().item(), smallest);
    			curr = curr.next();
    		}
    		return smallest;
    	}

    	public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    	
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	if (this.isEmpty()){
    		return list2;
    	}
    	if (list2.isEmpty()){
    		return this;
    	}
    	else {
    		AbstractListNode curr = this;
    		for(int i = 0; i < this.size()-1; i++) {
    			curr = curr.next();
    		}
    		curr.setNext(list2);
    		return this;
    	}
    	
    }
    
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
    	if(a.isEmpty()){
    		return b;
		
    	}else if(b.isEmpty()){
    		return a;
    	}
    	else if(a.item().compareTo(b.item())<=0){
    		a.setNext(merge(a.next(), b));
    		return a;
    		
    	}else if(a.item().compareTo(b.item()) > 0){
    		b.setNext(merge(b.next(), a));
    		return b;
    	} else{	
    		return null;
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
    	AbstractListNode current = this;
    	int count = 1;
    	while (!(current.next() instanceof EmptyListNode)){
    		count++;
    		current = current.next();
    	}return count;
    }
    
    public Comparable get(int pos){
      	 AbstractListNode current = this;
      	 for(int i = 0; i < pos; i++){
      		 current = current.next();
      	 } return current.item();
      }

    public boolean equals (Object o){
    	boolean same = true;
    	if(o instanceof AbstractListNode){
    		if (this.size() != ((AbstractListNode) o).size()){
    			return false;
    	    }
    		else {
    			for(int i = 0; i < this.size(); i++) {
    				if (this.get(i) != ((AbstractListNode) o).get(i)) {
    					same = false;
    				}
    			}
    		}
    	} 
		return same;
    }
    
    public String toString(){
    	String s = "( ";
    	for(int i = 0;  i < this.size(); i++){
    		s += this.get(i) + " ";
    	} 
    	s += ")";
    	return s;
    }
    
    public AbstractListNode add (Comparable c){
        NonemptyListNode newList = new NonemptyListNode(c);
        return addHelper(this, newList);
    }
        
    	private static AbstractListNode addHelper(AbstractListNode L1, AbstractListNode L2 ){
    		if(L1.isEmpty()) {
    			return L2;
    		} else {
    			return new NonemptyListNode(L1.item(), addHelper(L1.next(), L2));
    		}    	
    	}
    
    	
    //This code was provided in the lab
    public AbstractListNode append(AbstractListNode list) {
    	if (list.isEmpty()){
    		return this;
    	}
        return appendHelper(this, list);
    }

    	private static AbstractListNode appendHelper(AbstractListNode L1, AbstractListNode L2) {
    		if (L1.isEmpty()) {
    			return L2;
    		} else {
    			return new NonemptyListNode(L1.item(), appendHelper(L1.next(), L2));
    		}
    	}
    	
    public AbstractListNode reverse() {
    	NonemptyListNode newList = new NonemptyListNode(this.item());
    	return reverseHelper(this.next(), newList);
    }
    
    	private static AbstractListNode reverseHelper(AbstractListNode L1, AbstractListNode L2){
    		if (L1.isEmpty()){
    			return L2;
    		}
    		else {
    			return reverseHelper(L1.next(), new NonemptyListNode(L1.item(), L2));
    		}
    	}
    	
    public void setItem(Comparable c) {
    	myItem = c;
    }
    
    public void setNext(AbstractListNode lst){
    	myNext = lst;
    }
}



class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public int size(){
    	return 0;
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
    
    public Comparable get(int pos){
    	return null;
    }
    
    public boolean equals (Object o){
    	if(((AbstractListNode) o).size() == 0) {
    		return true;
    	}
    	return false;
    }
    
    public String toString(){
    	return "( )";
    }
    
    public AbstractListNode add (Comparable c){
    	NonemptyListNode newList = new NonemptyListNode(c);
    	return newList;
    }
    
  public AbstractListNode append(AbstractListNode list){
        if (list.isEmpty()) {
        	return this;
        } else {
        	return list;
        }
    }
  
  public AbstractListNode reverse() {
	  return this;
  }
  
  public void setItem(Comparable c) {
	  throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
  }
  
  public void setNext(AbstractListNode lst){
      throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
  }

}

