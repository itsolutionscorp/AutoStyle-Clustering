import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	abstract public Comparable item();

	abstract public AbstractListNode next();

	abstract public boolean isEmpty();

	abstract public int size();
	
	abstract public AbstractListNode copy();
	
	abstract public AbstractListNode reverse();

	
	public Comparable smallest() {
		  if (isEmpty()) {
		    throw new NoSuchElementException("Can't find smallest in empty list.");
		  }
		  return smallestHelper(this.item()) ;
		}

		public Comparable smallestHelper(Comparable smallestSoFar) {
		  Comparable tiny = smallestSoFar;
				  
				  for( int i = 0; i < this.size(); i++) {
					  
					  tiny = min(tiny, this.get(i).item());
					  
				  }
				  return tiny;
	}
		
	
		public static Comparable min(Comparable c1, Comparable c2) {
			  if (c1.compareTo(c2) < 0) {
			    return c1;
			  } else {
			    return c2;
			  }
			}

	public AbstractListNode get(int pos) {

		if (pos < 0) {
			throw new IllegalArgumentException(
					"Position can't be less than zero");
		}
		AbstractListNode n = this;
		while (pos > 0) {
			if (n.next().isEmpty()) {
				throw new IllegalArgumentException("Position out of bounds.");
			}
			n = n.next();
			pos--;
		}
		return n;
	}
	
	abstract public String toString();
	abstract public boolean equals();
	
	abstract public AbstractListNode add(Comparable C);
	
	abstract public AbstractListNode append(AbstractListNode list);

	// Every other list-processing method goes here.

}


////////////////// Non Empty//////////////

class NonemptyListNode extends AbstractListNode {

	private Comparable myItem;
	private AbstractListNode myNext;

	public NonemptyListNode(Comparable item, AbstractListNode next) {
		myItem = item;
		if (next == null) {
			myNext = new EmptyListNode();
		} else {
			myNext = next;
		}
	}

	public NonemptyListNode(Comparable item) {
		this(item, new EmptyListNode());
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

	public void setItem(Comparable item) {
		myItem=item;
	}

	public void setNext(AbstractListNode node) {
		myNext = node;
	}

	public String toString() {
			String theString = new String();
			theString += "( ";
			for( int i = 0; i < this.size(); i++ ) {
				theString  += this.get(i).item().toString() + " ";
			}
			theString += ")";
			return theString;
		}

	public boolean equals(NonemptyListNode blah) {
		if (this.size() != blah.size()) {
			return false;
			
		}
		else {
			for (int i = 0; i< this.size(); i++ ) {
				
				if(!this.get(i).item().equals(blah.get(i).item())) {
					return false;
				}	
			}
		}
		return true;
	}

	public AbstractListNode add(Comparable C) {
		return new NonemptyListNode(item(),this.next().add(C));
	}
	
	@Override
	public boolean equals() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public AbstractListNode copy() {
		return new NonemptyListNode(this.item(),this.next().copy());
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		
		return new NonemptyListNode(this.item(),this.next().append(list));
	}

	@Override
	public AbstractListNode reverse() {
			AbstractListNode p = new NonemptyListNode(this.item());
			for(int i = 1; i<size(); i++) {
				p = new NonemptyListNode(this.get(i), p);
			}
			return p;
		}
	
	public AbstractListNode appendInPlace(AbstractListNode list2){
    	if(isEmpty()) {
    		return list2;
    	}
    	else {
    		AbstractListNode p = this;
    		while(!p.next().isEmpty){
    			p=p.next();
    		}
    		p.setNext(list2);
    	}
	}

	public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
    	a.appendInPlace(b);
    	for(int i = 0; i<size(); i++) {
    		Comparable smallest = a.get(i).smallest();
    		AbstractListNode smallestNode;
    		for(int j = i; j<size(); j++) {
    			if a.get(j).item().equals(smallest){
    				smallestNode = a.get(j);
    				a.get(j-1).setNext(a.get(j+1));
    			}
    			smallestNode.next=a;
    		}
    	}
    	return a;
    }
}
		
	

}

//////////////////Empty /////////////////////////////////////

class EmptyListNode extends AbstractListNode {

	public EmptyListNode() {

	}

	public Comparable item() {
		throw new IllegalArgumentException(
				"There is no 'item' value stored in an EmptyListNode.");
	}

	public AbstractListNode next() {
		throw new IllegalArgumentException(
				"No elements follow an EmptyListNode.");
	}

	public boolean isEmpty() {
		return true;
	}

	public int size() {
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean equals(AbstractListNode eh) {
		return eh.isEmpty();
	}

	public AbstractListNode add(Comparable C) {
		 NonemptyListNode fresh = new NonemptyListNode(C,new EmptyListNode());
		 return fresh;
	}

	public boolean equals() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public AbstractListNode copy() {
		return new EmptyListNode();
	}

	public AbstractListNode append(AbstractListNode list) {
		return list.copy();
	}

	@Override
	public AbstractListNode reverse() {
		return new EmptyListNode();
	}
}
