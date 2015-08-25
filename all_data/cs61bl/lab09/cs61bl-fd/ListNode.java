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
    	if (pos > 0 && myNext != null) {
    		return myNext.get(pos - 1);
    	}
    	else if (pos == 0) {
    		return myItem;
    	} else {
    		throw new IllegalArgumentException("Position out of range!");
    	}
    }

}

