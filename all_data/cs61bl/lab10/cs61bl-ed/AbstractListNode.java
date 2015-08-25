import java.util.NoSuchElementException;

abstract public class AbstractListNode {

//	public AbstractListNode myNext;
	abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Comparable get(int pos);
    abstract public String toString();
    abstract public String helperToString();
    abstract public boolean equals(AbstractListNode ls);
    abstract public AbstractListNode add(Comparable c);
    abstract public AbstractListNode append(AbstractListNode a);
    abstract public AbstractListNode reverse();
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
 
    // Every other list-processing method goes here.
 	 public static AbstractListNode merge( AbstractListNode a, AbstractListNode b ) {
	        if (a.isEmpty()){
	        	return b;
	        }
	        else if (b.isEmpty()){
	        	return a;
	        }

	        NonemptyListNode pointerA = (NonemptyListNode) a;
	        NonemptyListNode pointerA2 = (NonemptyListNode) a; 
	        NonemptyListNode pointerB = (NonemptyListNode) b;
	        NonemptyListNode pointerB2 = (NonemptyListNode) b;
	        if(b.item().compareTo(a.item()) > 0){
	        while(!pointerA.next().isEmpty()){
	        	if (pointerB.item().compareTo(pointerA2.item()) < 0){
	        		if(!pointerB2.next().isEmpty()){
	        			pointerB2 = (NonemptyListNode) pointerB2.next();
	        		}		
	        		pointerA.setNext(pointerB);
	        		pointerB.setNext(pointerA2);
	        		pointerA = pointerB;
	        		pointerB = pointerB2;
	        	}else{
	        		pointerA = pointerA2;
	        		if(!pointerA2.next().isEmpty()){

	        			pointerA2 = (NonemptyListNode) pointerA2.next();
	        		}
	        		
	        	}
	        	
	        }
	        if(pointerB.item().compareTo(pointerA2.item()) >= 0 && !pointerB2.next().isEmpty()){
	        	pointerA2.setNext(pointerB2);
	        }
//	        System.out.println(a);
	        return a;
	        }else{
	        while(!pointerB.next().isEmpty()){
	        	if (pointerA.item().compareTo(pointerB2.item()) < 0){
	        		if(!pointerA2.next().isEmpty()){
	        			pointerA2 = (NonemptyListNode) pointerA2.next();
	        		}        		
	        		pointerB.setNext(pointerA);
	        		pointerA.setNext(pointerB2);
	        		pointerB = pointerA;
	        		pointerA = pointerA2;
	        	}else{
	        		pointerB = pointerB2;
	        		if(!pointerB2.next().isEmpty()){
	        			pointerB2 = (NonemptyListNode) pointerB2.next();
	        		} 	        		
	        	}	        	
	        }

	        if(pointerA.item().compareTo(pointerB2.item()) >= 0 && !pointerA2.next().isEmpty()){

	        	pointerB2.setNext(pointerA2);	        	
	        }
//	        System.out.println(b);
	        return b;
	        }


	 }
}

class NonemptyListNode extends AbstractListNode {

	private Comparable myItem;
	private AbstractListNode myNext;
    
 
    public void setItem(Comparable item){
    	myItem = item;
    }
    public void setNext(AbstractListNode next){
    	myNext = next;
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

    public int size(){
    	int mySize = 0;
    	
    	if(myNext.isEmpty() != true){
    		
    			mySize ++;
    			return mySize+myNext.size();
    			
    	}
    	mySize++;
    	
    	return mySize;
    }
    public Comparable get(int pos){
    	if(pos == 0){
    		return item();
    	}else{
    			return myNext.get(pos-1);		
    	}
    	
    }
    public String helperToString(){
    	if (myNext.isEmpty() == true){
    		return  ""+this.item(); 
    		
    	}else{
    		return this.item() +" "+ myNext.helperToString(); 
    	}
    }
    public String toString(){
    	return "( "+this.helperToString()+" )";
    }
    public boolean equals(AbstractListNode ls){

    	if(this.size() != ls.size()){
    		return false;
    	}else{
    		for (int pos = 0; pos < this.size(); pos++){
    	    	if (ls.get(pos) != this.get(pos)){
    	    		return false;
    	    	}
    	    }//end for
    	    return true;
    	}
    } 
    public Comparable smallest() {
        if (isEmpty()) {
            throw new NoSuchElementException ("can't find smallest in empty list");
        }
        return smallestHelper(next(), item());
    }

    public static Comparable smallestHelper(AbstractListNode list, Comparable smallestSoFar) {
        if (list.isEmpty()) {
            return smallestSoFar;
        } else {
            return smallestHelper(list.next(), min(smallestSoFar, list.item()));
        }
    }

    	public static Comparable min(Comparable c1, Comparable c2) {
    	  if (c1.compareTo(c2) < 0) {
    	    return c1;
    	  } else {
    	    return c2;
    	  }
    	}

    	 public AbstractListNode add(Comparable c){
    		 if(this.next().isEmpty()){
    			 AbstractListNode n = new NonemptyListNode(c);
    			 AbstractListNode head = new NonemptyListNode(this.item(),n);
    			 return head;
    		 }
    		 AbstractListNode next = this.next().add(c);
    		 AbstractListNode head = new NonemptyListNode(this.item(),next);
    		 return head;		 
    	    }
    	 public AbstractListNode append(AbstractListNode a){
    		 if(this.next().isEmpty()){
    			 AbstractListNode n = a;
    			 AbstractListNode head = new NonemptyListNode(this.item(),n);
    			 return head;
    		 }
    		 AbstractListNode next = this.next().append(a);
    		 AbstractListNode head = new NonemptyListNode(this.item(),next);
    		 return head;	
    	 }
    	 public AbstractListNode reverse(){
    		 
    		 AbstractListNode rev = new EmptyListNode();
    		 for (int pos = this.size(); pos > 0; pos--){
    			 rev = rev.add(this.get(pos-1));
//    			 System.out.println("this is"+this.get(pos-1));
    		 }
    		 return rev;
    	 }
    	 public AbstractListNode appendInPlace(AbstractListNode list2){
    		 NonemptyListNode pointer = this;
    		 while (!pointer.next().isEmpty()){
    			 pointer = (NonemptyListNode) pointer.next();
    		 }
    		 pointer.myNext = list2;
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
    	//throw new IllegalArgumentException ("this is empty!");
    }
    public Comparable get(int pos){
    	throw new IllegalArgumentException("Out of bound");	
    }
    public String helperToString(){
    	return "";
    }
    public String toString(){
    	return "( )";
    }
    public boolean equals(AbstractListNode ls){
    	if(ls.size() != 0){
    		return false;
    	}else{
    		return true;
    	}
    }
    public AbstractListNode add(Comparable c){

    		NonemptyListNode head = new NonemptyListNode(c);
    		return head;
    }
    public AbstractListNode append(AbstractListNode a){
    	return a;
    }
    public AbstractListNode reverse(){
    	return new EmptyListNode();
    }
    public AbstractListNode appendInPlace(AbstractListNode list2){
    	return list2;
    }
}


