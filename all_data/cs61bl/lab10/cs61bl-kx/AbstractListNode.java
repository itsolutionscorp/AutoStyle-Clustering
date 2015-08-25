import java.util.NoSuchElementException;
abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public boolean equals(Object o);
    abstract AbstractListNode add (Comparable c);
    abstract AbstractListNode reverse();
    abstract Comparable smallest();
    abstract AbstractListNode append(AbstractListNode list);
    abstract AbstractListNode appendInPlace(AbstractListNode list2);
    abstract void setItem(Comparable c);
    abstract void setNext(AbstractListNode next);
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
	    // Fill this out
    	if(a.isEmpty() && b.isEmpty()){
    		return a;
    	}
    	else if(a.isEmpty() && !b.isEmpty()){
    		return b;
    	}
    	else if(b.isEmpty() && !a.isEmpty()){
    		return a;
    	}
    	Comparable smallest = NonemptyListNode.min(a.item(),b.item());
    	if(smallest.equals(a.item())){
    		a.setNext(merge(a.next(), b));
            return a;
    	}
    	else{
            b.setNext(merge(a, b.next()));
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
    	return 1 + next().size();
    }
    
    public Comparable get(int pos){
    	if(pos<0 || (next().isEmpty() && pos!=1)){
    		throw new IllegalArgumentException("position out of range");
    	}
    	else if(pos==0){
    		return item();
    	}
    	else if(!next().isEmpty()){
    		return next().get(pos-1);
    	}
    	return null;
    }
    
    public String toString(){
    	//return " " + item() + " " + next().toString();
    	AbstractListNode node = this;
    	String s = "( ";
    	while(true){
    		if(node.size()==0){
    			break;
    		}
    		s = s + node.item() + " ";
    		node = node.next();
    	}
    	return s + ")";
    }
    
    public boolean equals(Object o){
    	if(o instanceof NonemptyListNode){
    		NonemptyListNode node = (NonemptyListNode)o;
    		if(item().equals(node.item())){
    			return next().equals(node.next());
    		}
    		else{
    			return false;
    		}
    	}
    	else if(o instanceof EmptyListNode){
    		return false;
    	}
    	return false;
    	
    }
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(item());
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    	  AbstractListNode n = this;
    	  while(!n.next().isEmpty()){
    		  smallestSoFar = min(n.next().item(), smallestSoFar);
    		  n = n.next();
    	  }
    	  return smallestSoFar;
    	}

    	public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    	
    	 
    	 
    	 public AbstractListNode add (Comparable c){
    		 return addHelper(this, c);
    	 }
    	 
    	 private static AbstractListNode addHelper(AbstractListNode n , Comparable c){
    		 if(n.isEmpty()){
    			 return new NonemptyListNode(c);
    		 }
    		 else{
    			 return new NonemptyListNode(n.item(), addHelper(n.next(), c));
    		 }
    	 }
    	 
    	 public AbstractListNode append(AbstractListNode list){
    		 return appendHelper(this, list);
    		 
    	 }
    	 
    	 private static AbstractListNode appendHelper(AbstractListNode n1, AbstractListNode n2){
    		 if(n1.isEmpty()){
    			 return n2;
    		 }
    		 else{
    			 return new NonemptyListNode(n1.item(), appendHelper(n1.next(), n2));
    		 }
    	 }
    	 

    	 public AbstractListNode reverse(){
    		 AbstractListNode n = this;
    		 AbstractListNode rev = new EmptyListNode();
    		 while(!n.isEmpty()){
    			 rev = new NonemptyListNode(n.item(), rev);
    			 n = n.next();
    		 }
    		 return rev;
    	 }
    	 
    	 public void setItem(Comparable c){
    		 myItem = c;
    	 }
    	 
    	 public void setNext(AbstractListNode next){
    		 myNext = next;
    	 }
    	 public AbstractListNode appendInPlace(AbstractListNode list2){
    		    AbstractListNode n = this;
    		    while(!n.next().isEmpty()){
    		    	n = n.next();
    		    }
    		    n.setNext(list2);
    		    return this;
    		}
    	 
    	 
} //end class

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
    
    public Comparable get(int pos){
    	return null;
    }
    
    public String toString(){
    	return "( )";
    }
    
    public boolean equals(Object o){
    	if(o instanceof EmptyListNode){
    		return true;
    	}
    	return false;
    }
    
    public Comparable smallest(){
    	return null;
    }
    
    public AbstractListNode add (Comparable c){
    	return new NonemptyListNode(c);
    }
    
    public AbstractListNode reverse(){
    	return this;
    }
    
    public AbstractListNode append(AbstractListNode list){
    	return list;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
        return list2;
    }
    
    public void setItem(Comparable c){
    	return;
    }
    
    public void setNext(AbstractListNode next){
    	return;
    }
    
}
