import java.awt.List;
import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
	public static void main(String[] args){
		AbstractListNode empty1 = new EmptyListNode();
	    AbstractListNode empty2 = new EmptyListNode();
	    //System.out.println(merge(empty1, empty2));
	    
	    NonemptyListNode n = new NonemptyListNode(1, new NonemptyListNode(3, 
				new NonemptyListNode(5)));
	    
	    NonemptyListNode m = new NonemptyListNode(2, new NonemptyListNode(6, 
				new NonemptyListNode(9)));
	    
	    System.out.println(merge(n,m));
	    
	    NonemptyListNode p = new NonemptyListNode(1, new NonemptyListNode(3, 
				new NonemptyListNode(5)));
	    
	    NonemptyListNode q = new NonemptyListNode(2, new NonemptyListNode(6, 
				new NonemptyListNode(9, new NonemptyListNode(11))));
	    
	    System.out.println(merge(q,p));
	}
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.

    public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
        if (a.isEmpty()){
        	return b;
        }
        if (b.isEmpty()){
        	return a;
        }
        if (a.item().compareTo(b.item()) < 0){ //a.item > b.item
        	((NonemptyListNode)a).setNext(merge(a.next(), b));
        	return a;
        } else {
        	((NonemptyListNode)b).setNext(merge(b.next(), a));
        	return b;
        }
    }
    
    public int size(){
    	if (isEmpty()){
    		return 0;
    	} else {
    		return 1 + (this.next()).size();
    	}
    }
    
    public Comparable get(int pos){
    	return null;
    }
    
    public String toString(){
    	String s = new String("(");
    	for (int i = 0; i < this.size(); i++){
    		s+= " "+this.get(i).toString();
    	}
    	s+= " )";
    	return s;
    }
    
    public boolean equals(AbstractListNode j){
    	if (this.size() != ((AbstractListNode)j).size()){
    		return false;
    	}
    	
    	for (int i = 0; i<size();i++){
    		if (!(this.get(i).equals(((AbstractListNode)j).get(i)))){
    			return false;
    		}
    	}
    	return true;
    }
    
    abstract public AbstractListNode add(Comparable c);
    
    abstract public AbstractListNode append(AbstractListNode list);
    
    abstract public AbstractListNode reverse();
    
    public Comparable smallest() {
    	  if (isEmpty()) {
    	    throw new NoSuchElementException("Can't find smallest in empty list.");
    	  }    	  
    	  //this.next().smallestHelper(this.item());
    	  Comparable val = this.item();
    	  for (int i=0; i<this.size(); i++){
    		  val = min(val, this.get(i));
    	  }
    	  return val;
    	}

    	public Comparable smallestHelper(Comparable smallestSoFar) {
    	  return min(this.item(), smallestSoFar);
    	}

    	public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}

    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;

    public void setItem(Comparable c){
    	this.myItem = c;
    }
    
    public void setNext(AbstractListNode lst){
    	this.myNext = lst;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	this.setNext(list2);
    	return this;
    }
    
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
    
    public Comparable get(int pos){
    	if (pos == 0){
    		return this.item();
    	} else {
    		return this.next().get(pos -1);
    	}
    }
    
    public AbstractListNode add(Comparable c){
    	NonemptyListNode a = new NonemptyListNode(this.item(), this.next().add(c));
    	return a;
    }
    
    public AbstractListNode append(AbstractListNode list){
    	NonemptyListNode a = new NonemptyListNode(this.item(), this.next().append(list));
    	return a;
    }
    
    public AbstractListNode reverse(){
    	AbstractListNode a = new EmptyListNode();
    	for (int i= 0; i<this.size(); i++){
    		a = new NonemptyListNode(this.get(i), a);
    	}
    	return a;
    }

}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	return list2;
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
    
    public AbstractListNode add(Comparable c){
    	return new NonemptyListNode(c);
    }
    
    public AbstractListNode append(AbstractListNode list){
    	if (list.isEmpty()){
    		return new EmptyListNode();
    	} else {
    		//AbstractListNode a = new NonemptyListNode(list.item(), new EmptyListNode());
    		//return a;
    	   	//while (! (list.equals(this))){
    	   		
    	   	//}  
    		return new NonemptyListNode(list.item(), list.next().append(list.next()));
    	}
    }
    
    public AbstractListNode reverse(){
    	return null;
    }
}
