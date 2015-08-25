public class ListNode {

    private Object myItem;
    private ListNode myNext;

    public ListNode(Object item, ListNode next) {
        myItem = item;
        myNext = next;
    }
    public ListNode(Object item) {
        this(item, null);
    }

    public Object item() {
        return myItem;
    }
    public ListNode next() {
        return myNext;
    }
    
    public Object get(int k) {
    	if (k == 0) {
    		return myItem;
    	} 
    	else if (myNext == null || k < 0) {
    		throw new IllegalArgumentException();
    	} else {
    		return myNext.get(k-1);
    	}
    }
     
    //self-referential/liar's paradox: if you have this == null, then you would not have had the method which calls on this == null if it is in fact null 
    //if it is null, that method does not exist, and if it not, then it is never null
    //null only has method null.equals/== where it returns true for with all null objects only
    //same with a method that sets this = null; 

}

