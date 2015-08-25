import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();

    // Every other list-processing method goes here.
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
	abstract public Comparable content();
	abstract public boolean equals(Object obj);
	abstract public AbstractListNode add(Comparable c);
	abstract public AbstractListNode append(AbstractListNode list);
	abstract public AbstractListNode reverse(); 
	abstract public AbstractListNode appendInPlace(AbstractListNode list2);
	public static AbstractListNode merge(AbstractListNode a, AbstractListNode b){
		if(a.isEmpty()){
			return b;
		}else if(b.isEmpty()){
			return a;
		}else if(a.item().compareTo(b.item()) < 0){
			((NonemptyListNode) a).setNext(merge(a.next(), b));
			return a;
		}else{
			((NonemptyListNode) b).setNext(merge(a, b.next()));
			return b;
		}
	}
	public Comparable smallest(){
		if(this.isEmpty()){
			throw new NoSuchElementException("Can't find smallest in empty list");
		}
		return smallestHelper(this.next(), this.item());
	}
	public static Comparable smallestHelper(AbstractListNode list, Comparable smallestSoFar){
		if(list.isEmpty()){
			return smallestSoFar;
		}else{
			return smallestHelper(list.next(), min(list.item(), smallestSoFar));
		}
	}
	
	 public static Comparable min(Comparable c1, Comparable c2){
	    	if(c1.compareTo(c2) < 0){
	    		return c1;
	    	}else{
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
    	if(this.isEmpty()){
    		return 0;
    	}else{
    		return 1 + this.next().size();
    	}
    }
    
    public Comparable get(int pos){
    	if(pos < 0){
    		throw new IllegalArgumentException("Index out of bounds");
    	}else if(pos == 0){
    		return this.item();
    	}else{
    		if(this.next().isEmpty()){
    			throw new IllegalArgumentException("Index out of bounds");
    		}else{
    			return this.next().get(pos - 1);
    		}
    	}
    }
    
    public String content(){
    	if(this.next().isEmpty()){
    		return this.item() + "";
    	}else{
    		return this.item() + " " + this.next().content();
    	}
    }
    
    public String toString(){
    	return "( " + this.content() + " )";
    }

    public boolean equals(Object obj){
    	if(this.size() != ((AbstractListNode) obj).size()){
    		return false;
    	}else if(this.isEmpty() && ((AbstractListNode) obj).isEmpty()){
    		return true;
    	}else if(this.item() != ((AbstractListNode) obj).item()){
    		return false;
    	}else{
    		return this.next().equals(((AbstractListNode) obj).next());
    	}
    }
    
    public AbstractListNode add(Comparable c){
    	return new NonemptyListNode(this.item(), this.next().add(c));
    }
    
    public AbstractListNode append(AbstractListNode list){
    	if(list.isEmpty()){
    		return this;
    	}else{
    		return this.add(list.item()).append(list.next());
    	}
    }
    
    public AbstractListNode reverse(){
    	if(this.isEmpty()){
    		return this.reverse();
    	}else{
    		NonemptyListNode temp = new NonemptyListNode(this.item());
    		return this.next().reverse().append(temp);
    	}
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	if(this.next().isEmpty()){
    		this.myNext = list2;
    		return this;
    	}else{
    		return this.next().appendInPlace(list2);
    	}
    }
    
    public void setItem(Comparable item){
    	this.myItem = item;
    }
    
    public void setNext(AbstractListNode list){
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
    
    public int size(){
    	return 0;
    }
    
    public Comparable get(int pos){
    	throw new IllegalArgumentException("No elements are stored in EmptyListNode.");
    }
    
    public String content(){
    	return "";
    }
    
    public String toString(){
    	return "( )";
    }
    
    public boolean equals(Object obj){
    	if(((AbstractListNode) obj).isEmpty()){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    public AbstractListNode add(Comparable c){
    	return new NonemptyListNode(c);
    }
    
    public AbstractListNode append(AbstractListNode list){
    	if(list.isEmpty()){
    		return new EmptyListNode();
    	}else{
    		return new NonemptyListNode(list.item(), list.next());
    	}
    }
    
    public AbstractListNode reverse(){
    	return new EmptyListNode();
    }
    
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	return list2;
    }
}
