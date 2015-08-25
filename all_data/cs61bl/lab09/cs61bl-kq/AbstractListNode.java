abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    public int counter;
    public String a;

    // Every other list-processing method goes here.
   abstract public int size();
    abstract public String toString();
    		//    	String b;
//    	String c;
//    	if (counter == 0) {
//    		a = "(";
//    	}
//    	if (counter == size()) {
//    		b = ") ";
//    	}
//    	while (counter <= size()) {
//    		counter++;
//    	if (isEmpty()) {
//    		return "()";
//    	}
////    	else if (next() == null) {
////    		return    ")" ; 
////    	
////    	}
//    	else {
//    	
//    	
//    		a =  "" + item() + "" + next().toString();
//    		
//    	
//    	}
//    	}
//    	if (size() == counter) {
//    		counter = 0;
//    		return "(" + a + ")";
//    	}
//    	else {
//    		return a;
//    	}
//}
}


class NonemptyListNode extends AbstractListNode {

    private Object myItem;
    private AbstractListNode myNext;
    private Object String;

    public NonemptyListNode (Object item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    public NonemptyListNode (Object item) {
        this (item, new EmptyListNode());
    }

    public Object item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public int size() {
    	if (isEmpty()) {
    		return 0;
    	}
    	else if (next() == null){
    		return 1;
    	}
    	else  {
    		return 1 + next().size();
    	}
    }
    public String toString() {
    	if (next().isEmpty()) {
    		String = myItem + " )";
    	}
    	else {
    		String = myItem + next().toString().substring(1);
    	}
    	return "( " + String;
    }
    
    	
    
    

}

class EmptyListNode extends AbstractListNode {
	private Object String;
    
    public EmptyListNode() {
        
    }
    
    public Object item() {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public AbstractListNode next() {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public int size() {
    	if (isEmpty()) {
    		return 0;
    	}
    	else if (next() == null){
    		return 1;
    	}
    	else  {
    		return 1 + next().size();
    	}
    }
    public String toString() {
    	return  "()";
    }
}
