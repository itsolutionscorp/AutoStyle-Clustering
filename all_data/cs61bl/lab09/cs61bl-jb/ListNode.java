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
    	if (pos == 0) {
    		return this.item();
    	} else if (this.next() == null || pos < 0) {
    		throw new IllegalArgumentException("Out of bounds.");
    	} else {
    		return this.next().get(pos - 1);
    	}
    }

}

