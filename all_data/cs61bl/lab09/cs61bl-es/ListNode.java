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

    public Object get(int index) throws IllegalArgumentException {
    	if (index == 0) {
    		return item();
    	}
    	else if (index > 0 && next() == null) {
    		throw new IllegalArgumentException("Index out of bounds");
    	}
    	else {
    		return next().get(index - 1);
    	}
    }
}

