import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public boolean equals(AbstractListNode n);
    abstract public AbstractListNode add (Comparable c);
    abstract public AbstractListNode append (AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public void setItem(Comparable item);
    abstract public void setNext(AbstractListNode next);
    
    public String toString() {
    	String stringOfValues = "( ";
    	int counter = 0;
    	AbstractListNode copyNode = this;
    	while (counter < this.size()) {
    		stringOfValues += copyNode.item() + " ";
    		copyNode = copyNode.next();
    		counter++;
    	}
    	stringOfValues += ")";
    	return stringOfValues;
    }
    
    public Comparable smallest() {
    	if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	}
    	  return this.smallestHelper(this.next(), this.item()) ;
    	}

    public Comparable smallestHelper(AbstractListNode currentNode, Comparable smallestSoFar) {
    	if (currentNode.isEmpty()) {
    		return smallestSoFar;
    	}
    	return smallestHelper(currentNode.next(), min(smallestSoFar, currentNode.item()));
    }

    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
          return c1;
    	} else {
    	  return c2;
    	}
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
        if (this.isEmpty()) {
        	return list2;
        } else {
        	AbstractListNode pointer = this;
        	while (!pointer.next().isEmpty()) {
        		pointer = pointer.next();
        	}
        	pointer.setNext(list2);
        	return this;
        }
        
    }
    
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
    	if (a.isEmpty()){
  			return b;
  		}else if (b.isEmpty()){
  			return a;
  		}else{
  			AbstractListNode pointer1 = a;
  			AbstractListNode pointer2 = b;
  			AbstractListNode aNext = a.next();
  			AbstractListNode bNext = b.next();
  			while (!aNext.isEmpty() && !bNext.isEmpty()){
  				if (min(pointer1.item(),pointer2.item()) == pointer1.item() || pointer1.item() == pointer2.item()){
  					aNext = (AbstractListNode) pointer2.item();
  					pointer2.setItem(pointer1.next().item());
  					pointer1 = pointer1.next();
  					aNext = pointer1.next();
  				}else{
  					pointer1.setItem(pointer2.item());
  					pointer1 = pointer1.next();
  				}
  			}
  			return a.appendInPlace(b);
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
    
    @Override
    public int size() {
    	return 1 + this.next().size();
    }
    
    @Override
    public Comparable get (int pos) {
    	if (pos == 0) {
    		return this.myItem;
    	} else {
    		return this.myNext.get(pos-1);
    	}
    }
    
    @Override
    public boolean equals (AbstractListNode n) {
    	AbstractListNode copyNode1 = this;
    	int counter = 0; 
    	while (counter < this.size()) {
    		if (copyNode1.item() != n.item()) {
    			return false;
    		}
    		copyNode1 = copyNode1.next();
    		n = n.next();
    		counter ++;
    	}
    	return copyNode1.equals(n);
    }
    
    public AbstractListNode add (Comparable c) {
    	return new NonemptyListNode(this.item(), this.next().add(c));
    }
    
    public AbstractListNode append(AbstractListNode list) {
        return new NonemptyListNode(this.item(), this.next().append(list));
    }
    
    public AbstractListNode reverse() {
    	AbstractListNode nowNode = this;
    	NonemptyListNode newList = new NonemptyListNode(nowNode.item());
    	while (!nowNode.next().isEmpty()) {
    		newList = new NonemptyListNode(nowNode.next().item(), newList);
    		nowNode = nowNode.next();
    	}
    	return newList;
    }
    
    public void setItem(Comparable item) {
    	this.myItem = item;
    }
    
    public void setNext(AbstractListNode next) {
    	this.myNext = next;
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
    public Comparable get (int pos) {
    	throw new IllegalArgumentException("Linked List isn't that long");
    }
    
    @Override
    public boolean equals (AbstractListNode n) {
    	if (n.isEmpty()) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public AbstractListNode add (Comparable c) {
    	return new NonemptyListNode(c);
    }
    
    public AbstractListNode append(AbstractListNode list) {
        return list;
    }
    
    public AbstractListNode reverse() {
    	return this;
    }
    
    public void setItem(Comparable item) {
    	
    }
    
    public void setNext(AbstractListNode next) {
    
    }

}
