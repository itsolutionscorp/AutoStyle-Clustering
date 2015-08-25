import java.util.NoSuchElementException;
abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public String toString();
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode reverse();
    abstract public void setItem(Comparable c);
    abstract public void setNext(AbstractListNode a);
    // Every other list-processing method goes here.
    
    public Comparable get(int pos){
    	AbstractListNode x = this;
    	for (int i = 0; i < pos; i++){
    		if (!x.isEmpty() && !x.next().isEmpty()){
    			x = ((AbstractListNode) x).next();
    		}
    		else{
    			throw new IllegalArgumentException("Position out of range.");
    		}
    	}
    	return ((AbstractListNode) x).item();
    }
    
    public boolean equals(Object obj){
    	AbstractListNode x = this;
    	Boolean same = false;
    	if (this.size() == ((AbstractListNode) obj).size()){
    		for (int i = 0; i < this.size(); i++){
    			if (x.get(i) == ((AbstractListNode) obj).get(i)){
    				same = true;
    			}
    			else{
    				return false;
    			}
    		}
    	}
		return same;
    }
    
    public Comparable smallest() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("Can't find smallest in empty list.");
    	}
    	return this.next().smallestHelper(this.item());
   	}
    
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	if (this.isEmpty()){
    		return smallestSoFar;
    	}
    	else{
    		return this.next().smallestHelper(min(this.item(), smallestSoFar));
    	}
  	}
    
    public static Comparable min(Comparable c1, Comparable c2) {
    	if (c1.compareTo(c2) < 0) {
    		return c1;
    	} 
    	else {
    		return c2;
    	}
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	AbstractListNode x = this;
    	for(int i = 0; i < list2.size(); i++){
    		x = x.add(list2.get(i));
    	}
    	return x;
    }
    

    public static AbstractListNode merge (AbstractListNode a, AbstractListNode b ) {
    	if (a.isEmpty()){
    		return b;
    	}
    	if (b.isEmpty()){
    		return a;
    	}
    	if (min(a.item(), b.item()) == a.item()){
    		a.setNext(merge(a.next(), b));
    		return a;
    	}
    	else{
    		b.setNext(merge(b.next(), a));
    		return b;
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
    
    public int size(){
    	AbstractListNode x = this;
    	if(!x.isEmpty()){
    		x = ((NonemptyListNode) x).next();
    		return 1 + x.size();
    	}
    	return 0;
    }
    
    public String toString(){
    	String str = "( ";
    	AbstractListNode x = this;
    	for (int i = 0; i < this.size(); i++){
    		str += x.get(i) + " ";
    	}
    	return str + ")";
    }
    
    public AbstractListNode add (Comparable c){
    	AbstractListNode x = this;
    	NonemptyListNode temp = new NonemptyListNode(c);
    	NonemptyListNode copy = temp;
    	for (int i = 0; i < this.size(); i++){
    		temp.myItem = x.get(i);
    		temp.myNext = new NonemptyListNode(c);
    		temp = (NonemptyListNode) temp.myNext;
    	}
    	return copy;
    }
    
    public AbstractListNode append(AbstractListNode list){
    	AbstractListNode x = this;
    	for(int i = 0; i < list.size(); i++){
    		x = x.add(list.get(i));
    	}
    	return x;
    }
    
    public AbstractListNode reverse(){
    	AbstractListNode copy = new EmptyListNode();
    	for (int i = this.size()-1; i >= 0; i--){
    		copy = copy.add(this.get(i));
    	}
    	return copy;
    }
    
    public void setItem(Comparable c){
    	myItem = c;
    }
    
    public void setNext(AbstractListNode a){
    	myNext = a;
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
    
    public String toString(){
    	return "( )";
    }
    
    public AbstractListNode add (Comparable c){
    	return new NonemptyListNode(c);
    }
    
    public AbstractListNode append(AbstractListNode list){
    	return list;
    }
    
    public AbstractListNode reverse(){
    	return this;
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	return list2;
    }
    
    public void setItem(Comparable c){
    	
    }
    
    public void setNext(AbstractListNode a){
    	
    }
}
