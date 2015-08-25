import java.util.NoSuchElementException;


abstract public class AbstractListNode {
	
    abstract public Comparable item();
	abstract public AbstractListNode next();
    abstract public boolean isEmpty();


    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Comparable get(int i);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode b);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
    	AbstractListNode aPointer = a;
    	AbstractListNode bPointer = b;
    	NonemptyListNode sentinel = new NonemptyListNode(null);
    	NonemptyListNode sPointer = sentinel;
    	
    	while(!aPointer.isEmpty() && !bPointer.isEmpty()){
    		if(aPointer.item().compareTo(bPointer.item()) < 0){
    			sPointer.setMyNext(aPointer);
    			aPointer = aPointer.next();
    		}else{
    			sPointer.setMyNext(bPointer);
    			bPointer = bPointer.next();
    		}
    		
    		sPointer = (NonemptyListNode) sPointer.next();
    	}
    	if (!aPointer.isEmpty()){
    		sPointer.setMyNext(aPointer);
    	}
    	if (!bPointer.isEmpty()){
    		sPointer.setMyNext(bPointer);
    	}
    	
    	return sentinel.next();
	}
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }
    	  AbstractListNode currNode = this;
    	  Comparable smallest = currNode.item();
    	  while(!currNode.isEmpty()){
    		  smallest = currNode.smallestHelper(smallest);
    		  currNode = currNode.next();
    	  }
    	  return smallest;
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    	  if (smallestSoFar == null){
    			throw new IllegalArgumentException("Compared to null");
    		}
    	  return min(this.item(), smallestSoFar);
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
    
    public void setMyItem(Comparable item){
    	myItem = item;
    }
    
    public void setMyNext(AbstractListNode next){
    	myNext = next;
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
		if (myNext.isEmpty()){
			return 1;
		}
		return 1 + myNext.size();
	}

	@Override
	public Comparable get(int i) {
    	if(i == 0){
    		return myItem;
    	}else{
    		if(myNext == null){
    			throw new IllegalArgumentException();
    		}
    		return myNext.get(i - 1);
    	}
    
	}

	@Override
	public String toString() {
		AbstractListNode curr = this;
		String myStr = "";
		while (!curr.isEmpty()){
			myStr += " " + curr.get(0).toString();
			curr = curr.next();
		}
		return "(" + myStr + " )";
	}

	@Override
	public boolean equals(AbstractListNode b) {
		if (this.myItem != b.get(0)){
			return false;
		}else if (this.myItem == b.get(0) && this.myNext.isEmpty()){
			return true;
		}else{
			return true && this.next().equals(b.next());
		}
	}


	@Override
	public AbstractListNode add(Comparable c) {
		if (c == null){
			throw new IllegalArgumentException("Item to be added is null!");
		}
		if (this.isEmpty()){
			return this.add(c);
		}else{
			return new NonemptyListNode(this.item(), this.next().add(c));
		}
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		AbstractListNode result = list;
		if (this.isEmpty()){
			return this.append(list);
		}else{
			return new NonemptyListNode(this.item(), this.next().append(list));
		}
	}

	@Override
	public AbstractListNode reverse() {
		AbstractListNode curr = this;
		AbstractListNode result = new EmptyListNode();
		while(!curr.isEmpty()){
			result = new NonemptyListNode(curr.item(), result);
			curr = curr.next();
		}
		return result;
	}

	@Override
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		if (this.next().isEmpty()){
			this.setMyNext(list2);
		}else{
			this.myNext.appendInPlace(list2);
		}
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

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Comparable get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "( )";
	}


	@Override
	public boolean equals(AbstractListNode b) {
		return b.isEmpty();
	}
	
	@Override
	public AbstractListNode add(Comparable c){
		if (c == null){
			throw new IllegalArgumentException("Item to be added is null!");
		}
		NonemptyListNode newNode = new NonemptyListNode(c, new EmptyListNode());
		return newNode;
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		return list;
	}

	@Override
	public AbstractListNode reverse() {
		return new EmptyListNode();
	}

	@Override
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		return list2;
	}



}
