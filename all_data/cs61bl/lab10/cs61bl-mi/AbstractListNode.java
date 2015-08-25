import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int i);
    abstract public String toString();
    abstract public boolean equals(Object arg);
    public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  return smallestHelper(item());
    	}

    public Comparable smallestHelper(Comparable smallestSoFar) {
    	AbstractListNode currNode = this;
    	 while (!currNode.isEmpty()) {
    		  smallestSoFar = min(smallestSoFar, currNode.item());
    		  currNode = currNode.next();
    	  }
    	  return smallestSoFar;
    	}
    abstract public AbstractListNode add (Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    abstract public void setNext(AbstractListNode list);
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
    	if (!a.isEmpty() && !b.isEmpty()) {
    		if (a.item().compareTo(b.item()) <= 0) {
    			a.setNext(merge(a.next(), b));
    			return a;
    		}
    		else {
    			b.setNext(merge(a, b.next()));
    			return b;
    		}
    		
    	}
    	else if (!a.isEmpty()) {
    		b = a;
    		return a;
    	}
    	else if (!b.isEmpty()) {
    		a = b;
    		return b;
    	}
    	else {
    		a = b;
    		return a;
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
    
    public Comparable get(int i) {

    	if (i == 0) {
    		return myItem;
    	}
    	else if (i < 0){
    		throw new IllegalArgumentException("The index is out of bounds");
    	}
    	else {
    		return myNext.get(i - 1);
    	}
    }
    
    public String toString() {
//    	return "(" + myItem.toString() + 
    	String currString = "(";
    	for (int i = 0; i < size(); i++) {
    		currString += " " + get(i).toString();
    	}
    	currString += " )";
    	return currString;
    }
    
    public boolean equals(Object arg) {
    	if (arg instanceof NonemptyListNode) {
    		NonemptyListNode NEListNode = (NonemptyListNode) arg;
    		if (size() == NEListNode.size()) {
    			for (int i = 0; i < size(); i ++) {
    				if (get(i).equals(NEListNode.get(i)) == false) {
    					return false;
    				}
    			}
    			return true;
    		}
    	}
    	return false;
    }
    
    public AbstractListNode add(Comparable c) {
    	NonemptyListNode copyList = new NonemptyListNode(this.item());
    	NonemptyListNode currNode = this;
    	NonemptyListNode currCopyNode = copyList;
    	while(!currNode.next().isEmpty()) {
    		currNode = (NonemptyListNode)currNode.next();
    		NonemptyListNode nextNode = new NonemptyListNode(currNode.item());
    		currCopyNode.myNext = nextNode;
    		currCopyNode = nextNode;

    	}
    	currCopyNode.myNext = new NonemptyListNode(c, new EmptyListNode());
    	return copyList;
    	
    }
    
    public AbstractListNode append(AbstractListNode list) {
    		NonemptyListNode toReturn = new NonemptyListNode(this.item());
    		NonemptyListNode copylist = this;
    		while (!copylist.next().isEmpty()) {
    			copylist = (NonemptyListNode) copylist.next();
    			toReturn = (NonemptyListNode) toReturn.add(copylist.item());
    		}
    	if (list.isEmpty()) {
    			return toReturn;
    	}
    	else {
    		NonemptyListNode listcopy = (NonemptyListNode) list;
    		while(!listcopy.next().isEmpty()) {
    			toReturn = (NonemptyListNode) toReturn.add(listcopy.item());
    			
    			listcopy = (NonemptyListNode) listcopy.next();
    		}
    		toReturn = (NonemptyListNode) toReturn.add(listcopy.item());	
    		return toReturn;
    	}
    }
    
    public AbstractListNode reverse() {
    	
    	int counter = 0;
    	AbstractListNode currNode = this;
    	while (!currNode.isEmpty()) {
    		counter++;
    		currNode = currNode.next();
    	}
    	int counter2 = 0;
    	currNode = this;
    	NonemptyListNode[] NELNarr = new NonemptyListNode[counter];
    	for (int i = 0; i < counter; i++) {
    		NELNarr[counter-1-counter2] = (NonemptyListNode) currNode;
    		currNode = currNode.next();
    		counter2++;
    	}
    	AbstractListNode toReturn = new EmptyListNode();
    	for (int i = 0; i < counter; i ++) {
    		toReturn = toReturn.add(NELNarr[i].item());
    	}
    	NonemptyListNode toReturn2 = (NonemptyListNode) toReturn;
    	return toReturn2;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	if (list2.isEmpty()) {
    		return this;
    	}
    	int counter = 0;
    	AbstractListNode currNode = this;
    	while (!currNode.isEmpty()) {
    		counter++;
    		currNode = currNode.next();
    	}
    	currNode = this;
    	if (counter == 1) {
    		this.myNext = list2;
    	}
    	for (int i = 1; i < counter; i++) {
    		currNode = currNode.next();
    	}
    	currNode.setNext(list2);
    	return this;
    	
    }
    
    public void setNext(AbstractListNode list) {
    	this.myNext = list;
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

    public Comparable get(int i) {
    	throw new IllegalArgumentException("The index is out of bounds");
    }
    
    public String toString() {
    	return "( )";
    }
    
    public boolean equals(Object arg) {
    	if (arg instanceof EmptyListNode) {
    			return true;
    	}
    	return false;
    }
    
    public AbstractListNode add(Comparable c) {
    	return new NonemptyListNode(c, new EmptyListNode());
    	
    }
    
    public AbstractListNode append(AbstractListNode list) {
    	if (list.isEmpty()) {
    		return new EmptyListNode();
    	}
    	else {
    		
    		NonemptyListNode copylist = (NonemptyListNode) list;
    		NonemptyListNode currNode = new NonemptyListNode(list.item());
    		while (!copylist.next().isEmpty()) {
    			currNode = (NonemptyListNode) currNode.add(copylist.next().item());
    			copylist = (NonemptyListNode) copylist.next();
    		}
    		return currNode;
    		
    	}
    }
    
    public AbstractListNode reverse() {
    	return new EmptyListNode();
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	return list2;
    }
    
    public void setNext(AbstractListNode list) {
    	throw new IllegalArgumentException("This is empty.");
    }
}
