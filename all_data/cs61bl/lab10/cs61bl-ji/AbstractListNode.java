import java.util.NoSuchElementException;
abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int x);
    abstract public String toString();
    //abstract public boolean equals(Comparable arg);
    // Every other list-processing method goes here.
    
    public Comparable smallest() {
  	  if (isEmpty()) {
  	    throw new NoSuchElementException("Can't find smallest in empty list.");
  	  }
  	  return smallestHelper(this.item());
  	}

  	public Comparable smallestHelper(Comparable smallestSoFar) {
  	  AbstractListNode current = this;
  	  Comparable ans = smallestSoFar;
  	  while (!current.next().isEmpty()){ 
  		  ans = min(ans, current.next().item());
  		  current = current.next();
  	  }
  	  return ans;
  	}

  	public static Comparable min(Comparable c1, Comparable c2) {
  	  if (c1.compareTo(c2) < 0) {
  	    return c1;
  	  } else {
  	    return c2;
  	  }
  	}
  	
  	public AbstractListNode add (Comparable c){
  		if (isEmpty()){
  			return new NonemptyListNode(c);}
  		return addHelper(this, c);
  		
  	}
  	public static AbstractListNode addHelper(AbstractListNode list, Comparable c){
  		if (list.isEmpty()){
  			return new NonemptyListNode(c);
  		}
  		else{
  			return new NonemptyListNode(list.item(), addHelper(list.next(), c));
  		}
  		
  		
  	}
  	
  	public AbstractListNode append(AbstractListNode list) {
  		if (list.isEmpty()){
  			return this;
  		}
  		
  		AbstractListNode newList = this;
  		AbstractListNode current = list;
  		while (!current.isEmpty()){
  			newList = newList.add(current.item());
  			current = current.next();
  		}
  		return newList;
  	}

  	
  	public AbstractListNode reverse() {
  		AbstractListNode current = this;
  		if (!this.isEmpty()) {
  			AbstractListNode newList = new NonemptyListNode(this.item());
  			while (!current.next().isEmpty()) {
  				newList = addToFront(newList, current.next().item());
  				current = current.next();
  			
  			}
  			return newList;
  		}
  		return this;
  		
  	}
  	public AbstractListNode addToFront(AbstractListNode list, Comparable item) {
  		if (list.isEmpty()) {
  			return new EmptyListNode();
  		} else if (item == null) {
  			return list;
  		} else {
  			return new NonemptyListNode(item, list);
  		}
  			
  	}

  	
  	public static int length(NonemptyListNode list) {	
  		return list.size();
  		//return Count;
  	}
  	public AbstractListNode appendInPlace(AbstractListNode list2){
  		if (isEmpty()){
  			return list2;
  		}
  		return new NonemptyListNode(item(), next().appendInPlace(list2));
  	}
  	/*public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
  		
  		if (a.isEmpty()){
  			return b;
  		}
  		else if(b.isEmpty()){
  			return a;
  		}
  		else if (a.item().compareTo(b.item())< 0 ){ // if a is less than b
  			 return new NonemptyListNode(a.item(), merge(a.next(), b));
  			 
  	}else{
  		return new NonemptyListNode(b.item(), merge(a, b.next()));
  		

	  	}
	} */
  	
  	public static AbstractListNode merge( AbstractListNode a, AbstractListNode b){
  		if (a.isEmpty()){
  			return b;
  		}
  		if ( b.isEmpty()){
  			return a;}
  		AbstractListNode head; 
  		if (a.item().compareTo(b.item())<=0){ 
  			head = a;
  		}
  		else {
  			head =  b;
  		}
  		NonemptyListNode currentA = (NonemptyListNode) a; // we know it isnt empty 
  		NonemptyListNode currentB = (NonemptyListNode) b; // we need two to count while we increment through the list
  		
  		while (true){
  			if (currentA.item().compareTo(currentB.item())<=0){
  				// ((NonemptyListNode) a).setItem(a.item()); // 
  				if (currentA.next().isEmpty()){
  					break;
  				}
  				else{
  					a = currentA;
  				currentA = (NonemptyListNode) currentA.next();}		
  			}
  			else if (currentA.item().compareTo(currentB.item())>0){
  				
  				((NonemptyListNode) a).setNext((NonemptyListNode) currentB);
  				if (currentB.next().isEmpty()){
  					break;
  				}
  				currentB = (NonemptyListNode) currentB.next();
  				((NonemptyListNode) b).setNext((NonemptyListNode) currentA);
  				b = (NonemptyListNode) currentB;
  				a = currentA;
  			}}
  		if (currentA.next().isEmpty()){
  			((NonemptyListNode) a).setNext(currentB);
  		}
  		else if (currentB.next().isEmpty()){
  			((NonemptyListNode) b).setNext(currentA);
  		}
  			System.out.println(head);
		return head;	
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
    public void setNext(AbstractListNode a){
    	myNext = a;
    }
    public void setItem(Comparable c){
    	myItem = c;
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
    	//int Count = 1;
    	return 1 + this.next().size();
    	//return Count;
    }
    
    public Comparable get(int x) throws IllegalArgumentException{
    	if (x>this.size()) {
    		throw new IllegalArgumentException("wrong");
    	}
    	
    	AbstractListNode currentNode = this;
    	for (int i =0; i<=x; i++){
    		if (i !=x ){
    			currentNode = currentNode.next();
    		}
    		else {
    			return currentNode.item();
    		}
    	}
        return currentNode.item();
    }
    
    public String toString() {
    	String newString = "( " + this.item() + " ";
    	AbstractListNode current = this.next();
    	for (int i=1; i<size(); i++) {
    		newString += current.item() + " ";
    		current = current.next();
    	}
    	return newString + ")";
    }
    
    public boolean equals(Comparable arg) {
    	if (arg instanceof AbstractListNode) {
    		AbstractListNode node = (AbstractListNode) arg;
    		if (this.size() == node.size()) {
    			AbstractListNode current = this;
    			while (!current.isEmpty()) {
    				if (current.item() != node.item()) {
    					return false;
    				} 
    			
    				current = current.next();
    				node = node.next();
    			}
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    	return true;
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
    
    public Comparable get(int x) {
    	return null;
    	
    }

    public String toString() {
    	return "( )";
    }
    
    public boolean equals(Comparable arg) {
    	if (arg instanceof AbstractListNode) {
    		AbstractListNode node = (AbstractListNode) arg;
    		if (this.size() == node.size() && node.size() ==0) {
    			return true;
    		}
    	}
    	return false;
    }
}
