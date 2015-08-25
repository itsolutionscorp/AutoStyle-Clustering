public class ListNode {

    private Object myItem;
    private ListNode myNext;
    private int index = 0;

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
    
    public ListNode get(int pos) {
    	if (pos < 0) {
    		throw new IllegalArgumentException("Position is out of range");
    	}
    	if (index == pos) {
    		return this;
    	} else {
    		if (next() == null) {
    			throw new IllegalArgumentException("Position is out of range");
    		} else {
    			return next().get(pos - 1);
    		}
    	}
    }

}

