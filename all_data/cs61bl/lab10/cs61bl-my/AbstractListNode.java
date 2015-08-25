import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public Comparable get(int num);

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public String toString();
    abstract public boolean equals(Comparable list);
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(item());
    	}

    public Comparable smallestHelper(Comparable smallestSoFar) {
    	if (next().isEmpty()) {
    		return smallestSoFar;
    	}	
    	  return min(smallestSoFar, next().smallest());
    	}

    public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list) ;
    abstract public AbstractListNode reverse();
    public AbstractListNode appendInPlace(AbstractListNode list2) {
    	if (isEmpty()){
    		return list2;
    	} else {
    		AbstractListNode current = this;
    		while (!(current.next().isEmpty())) {
    			current = current.next();
    		}
    		NonemptyListNode holder = (NonemptyListNode) current;
    		holder.setNext(list2);
    		return this;
    	}
    }
    
    public AbstractListNode merge(AbstractListNode list1, AbstractListNode list2) {
    	if (list1.isEmpty() && list2.isEmpty()) {
    		return list1;
    	} else if (list2.isEmpty()) {
    		return list1;
    	} else if (list1.isEmpty()) {
    		return list2;
    	} else if (min(list1.item(), list2.item()) == list1.item()) {
    		NonemptyListNode result = (NonemptyListNode) list1;
    		result.setNext(merge(list1.next(), list2));
    		return result;
    	} else {
    		NonemptyListNode result = (NonemptyListNode) list2;
    		result.setNext(merge(list1, list2.next()));
    		return result;
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
        this(item, new EmptyListNode());
    }

    public void setItem(Comparable target) {
    	myItem = target;
    }
    
    public void setNext(AbstractListNode target) {
    	myNext = target;
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
    
    @Override
    public Comparable get(int num) {
		if (num < 0) {
    		throw new IllegalArgumentException("Index Out of Range!");
    	} 
    	if (num == 0) {
    		return item();
    	} else {
    		return next().get(num-1);
    	}
    }
    
    @Override
    public int size(){
    	if (myItem == null) {
    		return 0;
    	} else {
    		return (1 + next().size());
    	}
    }
    
    @Override
    public String toString() {
    	String result = "( ";
    	for (int i=0; i<size();i++) {
    		result += get(i).toString() + " ";
    	}
    	return result + ")";
    }
    
    @Override
    public boolean equals (Comparable list){
    	try {
    		AbstractListNode list2 = (NonemptyListNode) list;
    		if (size() == list2.size()) {
    			if (item() == list2.item() && size() == 1 ) {
    				return true;
    			} else if (item() == list2.item()) {
    				return next().equals(list2.next());
    			} else {
    				return false;
    			}
    		} else {
    			return false;
    		}
    	} catch (ClassCastException e) {
    		return false;
    	}

    }
    
    @Override
    public NonemptyListNode add(Comparable c) {
    	if (size() == 1) {
    		return new NonemptyListNode(item(), new NonemptyListNode(c, new EmptyListNode()));
    	}
    	return new NonemptyListNode(item(), next().add(c));
    }
    
    @Override
    public AbstractListNode append(AbstractListNode list) {
    	return new NonemptyListNode(item(), next().append(list));
    }	
    
    @Override
    public AbstractListNode reverse(){
    	NonemptyListNode result = new NonemptyListNode(item(), new EmptyListNode());
    	AbstractListNode current = this.next();
    	while (current.isEmpty()) {
    		result = new NonemptyListNode(current.item(), result);
    		current = current.next();
    	}
    	return result;
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

    @Override
    public int size() {
    	return 0;
    }
    	
    @Override
    public Comparable get(int num) throws IllegalArgumentException {
    	throw new IllegalArgumentException("Index Out of Range!");
    }
    
    @Override
    public String toString() {
    	return "( )";
    }
    
    @Override
    public boolean equals(Comparable list) {
    	if (toString().equals(list.toString())){
    		return true;
    	} else 
    		return false;
    }
    
    @Override
    public NonemptyListNode add(Comparable c) {
    	return new NonemptyListNode(c, new EmptyListNode());
    }
    
    @Override
    public AbstractListNode append(AbstractListNode list) {
    	if (list.isEmpty()) {
    		return new EmptyListNode();
    	} else {
    		return new NonemptyListNode(list.item(), new EmptyListNode()).append(list.next());
    	}
    }
    
    @Override
    public AbstractListNode reverse(){
    	return new EmptyListNode();
    }
    
    
}


