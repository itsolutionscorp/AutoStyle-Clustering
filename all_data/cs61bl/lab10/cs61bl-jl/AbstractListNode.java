import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
abstract public class AbstractListNode implements Comparable {
	
    abstract public Comparable item();
	abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public String toString();
    abstract public Comparable get(int index);
    abstract public boolean equals(Object b); 
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract void setItem(Comparable item);
    abstract void setNext(AbstractListNode next);
    abstract AbstractListNode appendInPlace(AbstractListNode list2);
    
    

    public Comparable smallest() {
	  if (isEmpty()) {
	    throw new NoSuchElementException("Can't find smallest in empty list.");
	  }
	  return smallestHelper(item());
	}

	public Comparable smallestHelper(Comparable smallestSoFar) {
	  AbstractListNode curr = this;
	  while (!curr.isEmpty()){
		  smallestSoFar = min(smallestSoFar, curr.item());
		  curr = curr.next(); 
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
	
	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b) {
	    if(a.isEmpty()) {
	    	return b; 
	    }
	    if(b.isEmpty()) {
	    	return a; 
	    }
	    if (a.get(0).compareTo(b.get(0)) < 1) {
	    	a.setNext(merge(a.next(), b));
	    	return a; 
	    } 
		b.setNext(merge(b.next(), a));
        return b;
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
    
    public void setItem(Comparable item) { 
    	myItem = item; 
    }
    public void setNext(AbstractListNode next) { 
    	myNext = next; 
    }
    
    public boolean isEmpty() {
        return false;
    }

	@Override
	public int size() {
		if (myNext.isEmpty()){
    		return 1;
    	}
    	return 1 + myNext.size();
    }

	@Override
	public String toString() {
		String s = "( " + myItem;
		AbstractListNode temp = myNext;
		while (!temp.isEmpty()){
			s += " " + temp.item();
			temp = temp.next();
		}
		return s + " )";
	}
	
	@Override
	public Comparable get(int index){
    	if (myNext.isEmpty() && index != 0){
    		throw new IllegalArgumentException("out of bound");
    	}
    	else if (index == 0){
    		return myItem;
    	}
    	return myNext.get(index - 1);
    }

	@Override
	public boolean equals(Object b) {
		if (this.size() != ((AbstractListNode) b).size()){
			return false;
		} else{
			for (int i = 0; i <= this.size() - 1; i++){
				if (!this.get(i).equals(((AbstractListNode) b).get(i))){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int compareTo(Object o) {
		return myItem.compareTo((Comparable) o);
	}
	
    public AbstractListNode add (Comparable c){
    	NonemptyListNode a = new NonemptyListNode(myItem, myNext.add(c));
    	return a;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2) { 
    	if(list2.isEmpty()) {
    		return this; 
    	}
    	AbstractListNode current = this; 
    	while(!current.next().isEmpty()) {
    		current = current.next();
    	}
    	current.setNext(list2); 
    	return this; 
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if(list.isEmpty()) {
    		return this; 
    	}
    	AbstractListNode b = new NonemptyListNode(myItem, myNext.append(list));
		return b;
    }
    
    public static AbstractListNode reverseHelper(AbstractListNode list, AbstractListNode reverse) {
    if (list.isEmpty()) {
       return reverse;

     } else {
             return reverseHelper (list.next() , new NonemptyListNode (list.item(), reverse) );
             }
     }
    public AbstractListNode reverse () {
    	AbstractListNode reverse = new EmptyListNode();
    	return reverseHelper (this, reverse);
    }
}

//EMPTYLISTNODE 
//

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
    
    public void setItem(Comparable item) { 
    	 throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    public void setNext(AbstractListNode next) { 
    	 throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
	@Override
	public int size() {
		return 0;
	}

	@Override
	public String toString() {
		return "( )";
	}
	@Override
	public Comparable get(int index){
    	throw new IllegalArgumentException("out of bound");
    }

	@Override
	public boolean equals(Object b) {
		return ((AbstractListNode) b).isEmpty();
	}

	@Override
	public int compareTo(Object o) {
		throw new UnsupportedOperationException("No Items to compare to!");
	}
	
	 public AbstractListNode add (Comparable c){
		 return new NonemptyListNode(c);
	 }
	 
	public AbstractListNode appendInPlace(AbstractListNode list2) { 
		return list2; 
	}
	
	 public AbstractListNode append(AbstractListNode list) {
    	return list;    	
	}

	@Override
	public AbstractListNode reverse() {
		return this;
	}
}
