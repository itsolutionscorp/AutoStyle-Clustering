import java.util.NoSuchElementException;

abstract public class AbstractListNode<Comparable> {
	
    abstract public Comparable item();
    abstract public AbstractListNode<Comparable> next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int index);
    abstract public String toString();
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(this.item()) ;
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    	  if(this.next()==null){
    		  return smallestSoFar;
    	  } else {
    		  Comparable nextItem = this.next().item();
	    	  if(nextItem.compareTo(smallestSoFar) < 0){
	    		  smallestSoFar = nextItem;
	    	  }
	    	  smallestHelper(smallestSoFar);
    	  }
    	}

    	public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    // Every other list-processing method goes here.

}

class NonemptyListNode<Comparable> extends AbstractListNode<Comparable> {

    private Comparable myItem;
    private AbstractListNode<Comparable> myNext;

    public NonemptyListNode (Comparable item, AbstractListNode<Comparable> next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode<Comparable>();
        } else {
            myNext = next;
        }
    }

    public NonemptyListNode (Comparable item) {
        this (item, new EmptyListNode<Comparable>());
    }

    public Comparable item() {
        return myItem;
    }

    public AbstractListNode<Comparable> next() {
        return myNext;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public int size(){
    	return myNext.size() + 1;
    }
    
    public Object get(int index) {
    	if(index >= size()-1){
    		throw new IllegalArgumentException();
    	}
    	int i = 0;
    	AbstractListNode<Comparable> nextNode = this;
    	while (i < index){
    		nextNode = nextNode.next();
    		i++;
    	}
    	return nextNode.item();
    }
    
    public String toString(){
    	String stringList = "( ";
    	for(int i = 0; i < size() - 1; i++){
    		stringList = stringList + get(i).toString() + " ";
    	}
    	stringList = stringList + ")";
    	return stringList;
    }
    
    public boolean equals(AbstractListNode<Comparable> first){
        if (this.size() == 1 && first.size() == 1){
        	return true;
        }
        else if (this.size() != first.size()){
        	return false;
        }
        else if (this.item() != first.item()){
    		return false;
    	}
    	else{
    		return this.myNext.equals(first.next());
    	}
    }
    
    
}

class EmptyListNode<Comparable> extends AbstractListNode<Comparable> {
    
    public EmptyListNode() {
        
    }
    
    public Comparable item() {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public AbstractListNode<Comparable> next() {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public int size(){
    	return 1;
    }

    public Object get(int index) {
    	throw new IllegalArgumentException();
    }
    
    public String toString(){
    	String list = "()";
    	return list;
    }

}
