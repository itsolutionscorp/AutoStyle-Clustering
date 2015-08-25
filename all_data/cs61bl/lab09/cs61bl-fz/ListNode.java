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

    public Object get (int pos) {
    	ListNode test = this;
    	for (int i = 0; i < pos; i++) {
    		if (test == null) {
    			throw new IllegalArgumentException("Index Out of Range.");
    		}
    		test = test.next();
    	}
    	return test.item();
    }
}

