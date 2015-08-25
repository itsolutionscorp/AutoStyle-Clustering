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
    
    public Object get(int pos) {
    	while (pos > 1) {
    		if (myNext == null) {
    			throw new IllegalArgumentException("Position out of bounds.");
    		}
    		myNext = myNext.myNext;
    		pos--;
    	}
    	return myNext.myItem;
    }

}

