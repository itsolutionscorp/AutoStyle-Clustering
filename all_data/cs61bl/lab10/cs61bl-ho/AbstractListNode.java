import java.util.NoSuchElementException;

abstract public class AbstractListNode{
	abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
	abstract public Comparable get (int pos);
    abstract public String toString();
    abstract public String subToString();
    abstract public boolean equal (Comparable a);
    public Comparable smallest() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("Can't find smallest in empty list.");
    	}
    	return smallestHelper(item()) ;
    }
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	if (next().isEmpty()) {
            return smallestSoFar;
    	}
        return min(smallestSoFar, smallestHelper(next().item()));
    }
    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
    		return c1;
    	} else {
    		return c2;
    	}
    }
    abstract public AbstractListNode add (Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    abstract public void setItem(Comparable i);
    abstract public void setNext(AbstractListNode m);
    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
    	if (!a.isEmpty() && !b.isEmpty()){
    		AbstractListNode c, d;
    		if (a.item().compareTo(b.item()) < 0) {
    			c = a;
    			d = c;
    			a = a.next();
    		} else {
    			c = b;
    			d = c;
    			b = b.next();
    		}
    		while (!a.isEmpty() && !b.isEmpty()){
    	    	if (b.item().compareTo(a.item()) < 0) {
    	    		((NonemptyListNode)d).setNext(b);
    	    		d = d.next();
    	    		b = b.next();
    	    	} else {
    	    		((NonemptyListNode)d).setNext(a);
    	    		d = d.next();
    	    		a = a.next();
    	    	}
    	    }
    		if (a.isEmpty()){
    			((NonemptyListNode)d).setNext(b);
    		} else {
    			((NonemptyListNode)d).setNext(a);
    		}
    		return c;
    	} else if (a.isEmpty() && !b.isEmpty()){
    		return b;
    	} else {
    		return a;
    	} 
    }
    
    
    /*More functions to be written
      boolean contains1MoreThan(AbstractListNode otherList)
      void removeMin()
      void removeMax()
      void remove(int index)
      void insertInto(AbstractListNode otherList, int insertLocation)
      void duplicateMax()
      int calculateMean() // maybe only use any of the Objects that are Integers.
    */
    
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
    public void setItem(Comparable i){
    	myItem=i;
    }
    public void setNext(AbstractListNode m){
    	myNext=m;
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
    public int size(){
    	return 1+myNext.size();
    }
    public Comparable get (int pos){
    	if (pos==0){
    		return myItem;
    	}
    	return myNext.get(pos-1);
    }
    public String toString(){
    	return ("( " + subToString() + ")");
    }
    public String subToString(){
    	return (item() + " " + myNext.subToString());
    }
    public boolean equal (Comparable a){
    	if (((AbstractListNode) a).isEmpty()){
    		return false;
    	}
    	if (item().equals(((AbstractListNode) a).item())){
    		return myNext.equal((Comparable) ((AbstractListNode) a).next());
    	}
    	return false;
    }
    /* Instead of using recursion, we can also use a for loop.
       We can firstly create a pointer pointing to the first Node in the linked list,
       after adding the new Node, just return the pointer.
    
       Here is the recursive instantiation of add method
    
	    public AbstractListNode add(Comparable c) {
	  		NonemptyListNode temp = new NonemptyListNode(myItem);
	  		addHelper(temp, 1, c);
	  		return temp;
	    }
	    public AbstractListNode addHelper(NonemptyListNode c, int i, Comparable d) {
	    	if (i<size()){
	  			c.myNext = new NonemptyListNode(get(i));
	  			return addHelper((NonemptyListNode)c.next(), i+1, d);
	  	    }
	  	 	c.myNext = new NonemptyListNode(d);
	  	    return null;
	}*/
    
    public AbstractListNode add(Comparable c) {
    	NonemptyListNode temp = new NonemptyListNode(myItem);
    	NonemptyListNode pointToTemp = temp;
    	for (int i=1; i<size(); i++){
    		temp.myNext=new NonemptyListNode(get(i));
    		temp=(NonemptyListNode) temp.next();
    	}
    	temp.myNext=new NonemptyListNode(c);
    	return pointToTemp;
    }
    
	public AbstractListNode append(AbstractListNode list) {
		NonemptyListNode temp = new NonemptyListNode(myItem);
    	NonemptyListNode pointToTemp = temp;
    	for (int i=1; i<size(); i++){
    		temp.myNext=new NonemptyListNode(get(i));
    		temp=(NonemptyListNode) temp.next();
    	}
    	for (int i=0; i<list.size(); i++){
    		temp.myNext=new NonemptyListNode(list.get(i));
    		temp=(NonemptyListNode) temp.next();
    	}
    	return pointToTemp;
	}
	public AbstractListNode reverse() {
		NonemptyListNode temp = new NonemptyListNode(get(size()-1));
		NonemptyListNode pointToTemp = temp;
		for (int i=size()-2; i>=0; i--){
    		temp.myNext=new NonemptyListNode(get(i));
    		temp=(NonemptyListNode) temp.next();
    	}
    	return pointToTemp;
	}
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		if (list2.isEmpty()){
			return this;
		}
		NonemptyListNode temp = this;
		while (!temp.next().isEmpty()){
			temp=(NonemptyListNode)temp.next();
		}
		temp.myNext=(NonemptyListNode)list2;
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
    public int size(){
    	return 0;
    }
    public Comparable get (int pos){
    	throw new IllegalArgumentException("The position is out of range.");
    }
    public String toString(){
    	return ("( " + subToString() + ")");    	
    }
    public String subToString(){
    	return "";
    }
    public boolean equal (Comparable a){
    	if (((AbstractListNode) a).isEmpty()){
    		return true;
    	}
    	return false;
    }
	public AbstractListNode add(Comparable c) {
		return new NonemptyListNode(c);
	}
	public AbstractListNode append(AbstractListNode list) {
		if (list.isEmpty()){
			return new EmptyListNode();
		}
		NonemptyListNode temp = new NonemptyListNode(list.get(0));
    	NonemptyListNode pointToTemp = temp;
    	temp.append(list.next());
    	return pointToTemp;
	}
	public AbstractListNode reverse() {
		return new EmptyListNode();
	}
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		return list2;
	}
	public void setItem(Comparable i) {
		throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
		
	}
	public void setNext(AbstractListNode m) {
		throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
	}
}