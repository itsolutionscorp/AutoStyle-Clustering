import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Comparable get(int x);
    abstract public String toString();
    abstract public boolean equals(Comparable a);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    abstract public AbstractListNode setNext(AbstractListNode next);

    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  if(size() == 1) {
    		  return get(0);
    	  } else {
    		  
    		 Comparable s = min(get(0), get(1));
    		 for(int i = 2; i < size(); i++){
    			 s = min(s, get(i));
    		 }
    		 return s;
    	  }   
    	}

	/*public Comparable smallestHelper(Comparable smallestSoFar) {
		  
	}*/

	public static Comparable min(Comparable c1, Comparable c2) {
	  if (c1.compareTo(c2) < 0) {
	    return c1;
	  } else {
	    return c2;
	  }
	}
	
	public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
	    if(a.isEmpty()){
	    	return b;
	    } else if(b.isEmpty()){
	    	return a;
	    } else {	
	    	if(min(a.item(),b.item()) == a.item()){
	    		a.setNext(merge(a.next(),b));
	    		return a;
	    	} else {
	    		b.setNext(merge(a,b.next()));
	    		return b;
	    	}

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
    
    public AbstractListNode setNext(AbstractListNode next){
    	myNext = next;
    	return myNext;
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
    	if (myNext == null) {
    		return 0;
    	}
    	else{
    		return 1 + myNext.size();
    	} 
    }
    
    public Comparable get(int x) {
    	int count = -1;
    	for(AbstractListNode temp = this; !temp.isEmpty(); temp = (AbstractListNode)temp.next()){ 	
    		 count++;
    		 if(count == x){
    			 return temp.item();
    		 }
    	}
		throw new IllegalArgumentException("position out of range.");
    }

    public String toString(){
    	String s = "( ";
    	for(AbstractListNode temp = this; !temp.isEmpty(); temp = temp.next()){
    		s = s + temp.item() + " ";
    	}
    	s = s + ")";	
    	return s;
    }

    public boolean equals(Comparable a){
    	
    	if(a instanceof NonemptyListNode){ 
    		NonemptyListNode b = (NonemptyListNode)a;
			for(NonemptyListNode temp = this; temp != null; temp = (NonemptyListNode) temp.next()){
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
    
    public AbstractListNode add(Comparable c){
    	NonemptyListNode current = this;
    	NonemptyListNode head = new NonemptyListNode(current.item());
    	NonemptyListNode temp = head;
    	while (!current.next().isEmpty()){
    		current = (NonemptyListNode) current.next();
    		temp.myNext= new NonemptyListNode(current.item());
    		temp = (NonemptyListNode)temp.myNext;
    	}
    	temp.myNext = new NonemptyListNode(c);
    	return head; 
    }	
        
    
    public AbstractListNode append(AbstractListNode list) {
    	NonemptyListNode current = this;
    	NonemptyListNode head = new NonemptyListNode(current.item());
    	NonemptyListNode temp = head;
    	while (!current.next().isEmpty()){
    		current = (NonemptyListNode) current.next();
    		temp.myNext= new NonemptyListNode(current.item());
    		temp = (NonemptyListNode)temp.myNext;
    	}
    	
    	if(!list.isEmpty()){
	    	NonemptyListNode current1 = (NonemptyListNode)list;
	    	temp.myNext = new NonemptyListNode(current1.item());
	    	temp = (NonemptyListNode) temp.myNext;
	        while(!current1.next().isEmpty()){        		        	
	        	current1 = (NonemptyListNode) current1.next();
	        	temp.myNext = new NonemptyListNode(current1.item());
	        	temp = (NonemptyListNode) temp.myNext;
	        }
	        return head;
    	} else {
    		return head;
    	}
    }
    
    public AbstractListNode reverse(){    	
    	NonemptyListNode current = this;
    	NonemptyListNode tail = new NonemptyListNode(current.item());
    	NonemptyListNode previous = tail;
    	while(!current.next().isEmpty()){
    		current = (NonemptyListNode) current.myNext;
    		previous = new NonemptyListNode(current.item());
    		previous.myNext = tail; 
    		tail = previous;
    	}
    	return previous;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	NonemptyListNode temp = this;
    	while(!temp.next().isEmpty()){
    		temp = (NonemptyListNode)temp.next();
    	}
    	temp.myNext = list2;
    	return this;
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
    
    public AbstractListNode setNext(AbstractListNode next){
    	throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public int size(){
    	return 0;
    }
    
    public Comparable get(int x) {
    	throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public String toString(){
    	return "( )";
    }
    
    public boolean equals(Comparable a) {
    	if(a instanceof EmptyListNode){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public AbstractListNode add(Comparable c){
    	NonemptyListNode newlist = new NonemptyListNode (c, null);   
    	return newlist;
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if(list.isEmpty()){
    		return new EmptyListNode();
    	} else {
    		return new NonemptyListNode(list.item(), append(list.next()));
    	}
    }
    
    public AbstractListNode reverse(){
    	return new EmptyListNode();
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	if(list2.isEmpty()){
    		return this;
    	} else {
    		return list2;
    	}
    }
    
}


