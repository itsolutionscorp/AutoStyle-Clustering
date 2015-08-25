import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int index);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode list);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public void setItem(Comparable c);
    abstract public void setNext(AbstractListNode lst);
    
    // Every other list-processing method goes here.

    public Comparable smallest() {
    	if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	}
    	return smallestHelper(item());
    }

    public Comparable smallestHelper(Comparable smallestSoFar) {
    	AbstractListNode next = next();
    	while (! next.isEmpty()) {
    		smallestSoFar = min(smallestSoFar, next().item());
    		next = next.next();
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
    
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
        if (isEmpty()) {
        	return list2;
        } else if (next().isEmpty()) {
        	setNext(list2);
        	return this;
        }
        next().appendInPlace(list2);
        return this;
    }
    
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        // Fill this out
    	AbstractListNode c;
    	if (a.isEmpty() || b.isEmpty()) {
    		return a.appendInPlace(b);
    	} else if ((Integer) a.item() < (Integer) b.item()) {
    		c = a.next();
    		a.setNext(b);
    		b = c;
    		merge(a.next(), b);
    		return a;
    	} else {
    		c = b.next();
    		b.setNext(a);
    		a = c;
    		merge(a, b.next());
    		return b;
    	}
    }
}

class NonemptyListNode extends AbstractListNode implements Comparable<AbstractListNode>{

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
    
    public Comparable get(int index) {
    	if (index == 0) {
    		return myItem;
    	}
    	return (Comparable)myNext.get(index-1);
    }
    
    public String toString() {
    	AbstractListNode next = myNext;
    	Object items = "" + myItem + " ";
    	while(! next.isEmpty()){
    		items = "" + items + next.item() + " ";
    		next = next.next();
    	} 
    	return "( " + items + ")";
    }
    
    public boolean equals(AbstractListNode list) {
    	AbstractListNode next = this;
    	AbstractListNode next1 = list;
    	if (this.size() != list.size()) {
    		return false;
    	}
    	while (! next.isEmpty()) {
    		if (next.item() == next1.item()) {
    			next = next.next();
    			next1 = next1.next();
    		} else {
    			return false;
    		}
    	}
    	return true;
    }
    
    public int compareTo(AbstractListNode c2) {
    	return -(c2.item().compareTo(myItem));
    }
    
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(myItem, myNext.add(c));
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	return new NonemptyListNode(myItem, myNext);
    }
    
    public AbstractListNode reverse() {
    	int len = size();
    	AbstractListNode a = new EmptyListNode();
    	for (int i = 0; i < len; i ++) {
    		Comparable item = get(i);
    		a = new NonemptyListNode(item, a);
    	}
    	return a;
    }
    
    public void setItem(Comparable c) {
    	myItem = c;
    }
    
    public void setNext(AbstractListNode lst) {
    	myNext = lst;
    }
}

class EmptyListNode extends AbstractListNode{
    
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
    
    public Comparable get(int index) {
    	throw new IllegalArgumentException("index out of bounds");
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(AbstractListNode list) {
    	return false;
    }
    
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(c);
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	return list;
    }
    
    public AbstractListNode reverse() {
    	return this;
    }
    
    public void setItem(Comparable c) {
    	throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public void setNext(AbstractListNode lst) {
    	throw new IllegalArgumentException ("There is no 'next' value stored in an EmptyListNode.");
    }
}
