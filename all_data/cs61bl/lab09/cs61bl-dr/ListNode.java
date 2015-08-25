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
    public Object get(int posn){    				
		if (posn == 0)
			return myItem;  
		else if(myNext == null)
			throw new IllegalArgumentException("Index out of range.");			
		else return myNext.get(posn - 1);
	}    	
}



