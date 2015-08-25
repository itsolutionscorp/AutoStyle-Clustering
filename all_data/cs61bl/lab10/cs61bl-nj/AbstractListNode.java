import java.util.NoSuchElementException;
abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int a);
    abstract public String toString();
    abstract public boolean equals(Object a);
    public static Comparable min(Comparable c1, Comparable c2) {
  	  if (c1.compareTo(c2) < 0) {
  	    return c1;
  	  } else {
  	    return c2;
  	  }
  	}
    public Comparable smallest() throws NoSuchElementException{
  	  if (isEmpty()) {
  	    throw new NoSuchElementException("Can't find smallest in empty list.");
  	  }
  	  return smallestHelper(item());
  	}
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	if (next().isEmpty()) {
    		return smallestSoFar;
    	} return min(smallestSoFar, next().smallest());
    }
    abstract public AbstractListNode add (Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    abstract public void setNext(AbstractListNode next);
    abstract public void setItem(Comparable a);
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        if (a.isEmpty()) {
        	return b;
        } else if (b.isEmpty()) {
        	return a;
        } else {
        	AbstractListNode result;
        	if (a.item()==min(a.item(),b.item())) {
    			result = a;
    			a.setNext(merge(a.next(),b));
    		} else {
    			result = b;
    			b.setNext(merge(a,b.next()));
    		}
        	return result;
        }
    }
    // Every other list-processing method goes here.

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
    
    public int size() {
    	return 1 + myNext.size();
    }
    
    public Comparable get(int index) throws IllegalArgumentException{
    	if (index==0) {
    		return this.myItem;
    	} else {
    		return myNext.get(index-1);
    	}
    }
    
    public String toString() {
    	AbstractListNode temp = this;
    	String result = "( ";
    	while (!temp.isEmpty()) {
    		result = new String(result+temp.item()+" ");
    		temp = temp.next();
    	}
    	result = new String(result+")");
    	return result;
    }
    
    public boolean equals(Object a) {
    	if (a instanceof NonemptyListNode) {
    		NonemptyListNode real = (NonemptyListNode) a;
    		if (real.size()!=size()) {
    			return false;
    		} else {
    			if (real.item().equals(myItem)) {
    				return myNext.equals(real.next());
    			} else return false;
    		}
    	} else if (a instanceof EmptyListNode) {
    		return false;
    	} else {
    		return false;
    	}
    }
    
    public AbstractListNode add (Comparable c){
        AbstractListNode temp = this;
        NonemptyListNode result = new NonemptyListNode(myItem);
        NonemptyListNode temp2 = result;
        while (!temp.next().isEmpty()) {
        	temp = temp.next();
        	temp2.myNext = new NonemptyListNode(temp.item());
        	temp2 = (NonemptyListNode)temp2.myNext;
        }
        temp2.myNext = new NonemptyListNode(c);
        return result;
    }
    public AbstractListNode append(AbstractListNode list) {
        AbstractListNode temp = this;
        NonemptyListNode result = new NonemptyListNode(myItem);
        NonemptyListNode temp2 = result;
        while (!temp.next().isEmpty()) {
        	temp = temp.next();
        	temp2.myNext = new NonemptyListNode(temp.item());
        	temp2 = (NonemptyListNode)temp2.myNext;
        }
        while (!list.isEmpty()) {
        	temp2.myNext = new NonemptyListNode(list.item());
        	temp2 = (NonemptyListNode)temp2.myNext;
        	list = list.next();
        }
        return result;
    }
    
    public AbstractListNode reverse() {
        AbstractListNode temp = myNext;
        AbstractListNode result = new NonemptyListNode(myItem);
        while (!temp.isEmpty()) {
        	result = new NonemptyListNode(temp.item(), result);
        	temp = temp.next();
        }
        return result;
    }
    public void setNext(AbstractListNode next) {
    	this.myNext = next;
    }
    public void setItem(Comparable a) {
    	this.myItem = a;
    }
    public AbstractListNode appendInPlace(AbstractListNode list2){
        AbstractListNode temp = this;
        while (!temp.next().isEmpty()) {
        	temp = temp.next();
        }
        temp.setNext(list2);
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
    
    public boolean isEmpty() {
        return true;
    }
    
    public int size() {
    	return 0;
    }
    
    public Comparable get(int index) throws IllegalArgumentException{
    	throw new IllegalArgumentException("The requested index is out of range.");
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(Object a) {
    	return a instanceof EmptyListNode;
    }
    public AbstractListNode add (Comparable c){
        return new NonemptyListNode(c,this);
    }
    public AbstractListNode append(AbstractListNode list) {
    	if (list.next().isEmpty()) {
    		return new NonemptyListNode(list.item(),new EmptyListNode());
    	}
    	return new NonemptyListNode(list.item(), append(list.next()));
    }
    public AbstractListNode reverse() {
        return new EmptyListNode();
    }
    public AbstractListNode appendInPlace(AbstractListNode list2){
        return list2;
    }
    public void setNext(AbstractListNode next) {
    	
    }
    public void setItem(Comparable a) {
    	
    }
}
