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
    
    public Object get(int i) throws IllegalArgumentException {
    	if (i < 0) {
    		throw new IllegalArgumentException("Value to get is negative");
    	} else if (i == 0) {
    		return item();
    	} else if (next() == null) {
    		throw new IllegalArgumentException("Value to get is longer than list length");
    	} else {
    		return next().get(i-1);
    	}
    }

}

