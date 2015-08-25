import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public AbstractListNode add(Comparable c);
    abstract AbstractListNode append(AbstractListNode list);

	
    @Override
    public String toString(){
    	String me = new String("( ");
    	AbstractListNode here = this;
    	try{
    	if (here.size() == 1){
    		me = me + here.item() + " ";
        	return me + ")";
    	}
    	for(int i = 0; i < here.size() + 2; i++){
    		me = me + here.item() + " ";
    		here = here.next();
    	}
    	return me + ")";
    	
    	}
    	catch(IllegalArgumentException e){
    		return "( )";
    	}

	}
    public boolean equals(Object input1){
    	return input1.toString().equals(this.toString());
    }
    public int size(){
    	try{
    	this.next();
    	}
    	catch(IllegalArgumentException e){
    		return 0;
    	}
    	return 1 + this.next().size();
    }
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(next().item());
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    	if (next().next() == null){
    		return min(item(), smallestSoFar);
    		}
    	else{
    		Comparable next = min(item(), smallestSoFar);
    		if(next == this){
    		return this.smallestHelper(next().next().item());
    		}
    		if(next == next()){
    			return next().smallestHelper(next().next().item());
    		}
    	}
    	//just here to make eclipse happy. not real sol'n
    	return next().item();
    	}

    	public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
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
/*
    public AbstractListNode add(Comparable c){
    	AbstractListNode b = new NonemptyListNode(item(), null);
    	NonemptyListNode a = (NonemptyListNode) b;
    	while(this.next() != null){
    		a.myNext =this.next();
    		a = (NonemptyListNode) a.next();
    	}
    	return b;
    } */
    public AbstractListNode append(AbstractListNode list) {
    	AbstractListNode err = new NonemptyListNode(item(), null);
       if(this.isEmpty()){
    	   return list;
       }
       if(list.isEmpty()){
    	   return this;
       }
       return err;
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
    
    public AbstractListNode add(Comparable c){
    	NonemptyListNode thisone = new NonemptyListNode(c, null);
    	System.out.print(thisone);
    	return thisone;
    }

}
