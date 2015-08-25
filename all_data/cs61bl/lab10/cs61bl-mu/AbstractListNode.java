import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int a);
    abstract public String toString();
    abstract public boolean equals(Comparable w);
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  AbstractListNode node = this;
    	  Comparable smallest = node.item();
    	  node = node.next();
    	  while(node.isEmpty() == false){
    	  smallest =  min(smallest , node.item());
    	  node = node.next();
    	  }
    	  return smallest ;
    	}
    
    public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    
    public AbstractListNode add (Comparable c){
    	if (isEmpty()) {
    	   return new NonemptyListNode(c, null);
    	  }
    	return new NonemptyListNode(this.item(), this.next().add(c));
    }

    
    
    public AbstractListNode append(AbstractListNode list) {
    	if(this.isEmpty()){
    		
    	if (list!= null) {
    		if(list.isEmpty()){
    			return new EmptyListNode();
    		}else{
    			AbstractListNode lists = list.next();
    			return new NonemptyListNode(list.item(), lists.append(lists));
    		}
     	  }else{
     		 return new EmptyListNode();
     	  }
    	}
    	else{
    		return new NonemptyListNode(this.item(), this.next().append(list));
    	}
    	
    	
    }
    // Every other list-processing method goes here.

    
    
    public AbstractListNode reverse() {
    	if (isEmpty()) {
     	   return new EmptyListNode();
     	  }
    	

	      AbstractListNode node = new EmptyListNode();
	      AbstractListNode curnode = this;

	      while(!curnode.next().isEmpty()){
	      node = new NonemptyListNode(curnode.item(), node);
	      curnode = curnode.next();
	      }
	      return new NonemptyListNode(curnode.item(), node.reverse().reverse());
    }
    
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
        if(this.isEmpty() == true){
        	return list2;
        }
        AbstractListNode node = this;
        while(node.next().isEmpty() == false){
        	node = node.next();
        }
        
        ((NonemptyListNode) node).setNext(list2);
        return this;
    }
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
//    	AbstractListNode temp = a;
//    	AbstractListNode head = a;
//    	temp = a.next();
//    	while(temp.item().compareTo( b.item()) <= 0){
//        	temp = temp.next();
//        	((NonemptyListNode) a).setNext(b);
//        	
//        	if(temp.item().compareTo( b.item()) > 0){
//        	b = b.next();
//        	((NonemptyListNode) a).setNext(temp);
//        	}
//        }
//    	return head;
    	
    	if (a.isEmpty()) return b;
    	  if (b.isEmpty()) return a;

    	  if (a.item().compareTo(b.item()) <0) {
    		  ((NonemptyListNode) a).setNext( merge(a.next(), b));
    	    return a;
    	  } else {
    		  ((NonemptyListNode) b).setNext (merge(b.next(),a));
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
    public void setNext(AbstractListNode list2){
    	myNext = list2;
    }
    public void setItem(Comparable item){
    	myItem = item;
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
    	AbstractListNode node= this;
    	int i = 0;
    	while(node.isEmpty() == false){
    		node = node.next();
    		i++;
    	}
    	return i;
    }
    
    
    public Comparable get(int a){
    	AbstractListNode node = this;
    	int i = 0;
    	if(a <0){
    		throw new IllegalArgumentException("Wrong argument");
    	}
    	while(a > i){
    		if(node.next()!=null){
    		node = (NonemptyListNode)node.next();
    		}else{
    			throw new IllegalArgumentException("Argument out of bounds");
    		}
    		i++;
    	}
    	return node.item();
    }
    
    public String toString(){
    	AbstractListNode node = this;
    	String a = "(";
    	Object it;
    	while(node.isEmpty() == false){
    		
    		it = node.item();
    		node = node.next();
    		a = a+" "+it;
    	}
    	a = a+" )";
    	return a;
    }
    
   public boolean equals(Object w){
	   if(w instanceof AbstractListNode){
		   AbstractListNode nodes = (AbstractListNode) w;
		   AbstractListNode node = this;
		   if( node.size() == nodes.size()){
			   while(node.next().isEmpty() == false){
				   if(node.item() != nodes.item()){
					   return false;
				   }
				   node = node.next();
				   nodes = nodes.next();
			   }
			   return true;
		   }
		   return false;
	   }
	   return false;
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
    public Comparable get(int a){
    	return null;
    }
    
    public String toString(){
    	String a = "( )";
    	
    	return a;
    }
    
    public boolean equals(Object w){
    	if(w instanceof AbstractListNode){
 		   AbstractListNode nodes = (AbstractListNode) w;
 		   if(nodes.isEmpty() == true){
 			   return true;
 		   }
 		   
    }
 return false;   	
}
    
    
    
 
}

