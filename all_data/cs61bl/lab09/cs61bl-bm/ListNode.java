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
    public Object get (int i) {
    	ListNode temp = myNext;
    	while (i > 0) {
    		try {
    			temp = temp.myNext;
    		} catch (IllegalArgumentException e) {
    			throw new IllegalArgumentException();
    		}
    	}
    	return temp.myItem;
    }

}

