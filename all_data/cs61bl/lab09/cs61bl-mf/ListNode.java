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
    public Object get(int index) {
    	if (index < 0) {
    		throw new IllegalArgumentException("List index out of bounds.");
    	}
    	if (index == 0) {
    		return myItem;
    	} else {
    		if (myNext == null) {
    			throw new IllegalArgumentException("List index out of bounds.");
    		} else {
    		return myNext.get(index - 1);
    	}
}
}
}