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
    	ListNode x = this;
    	int length = 1;
    	while (x.next() != null) {
    		length++;
    		x = x.next();
    	}
    	if (pos > length) {
    		throw new IllegalArgumentException("out of bounds Element");
    	}
    	if (pos < 0) {
    		throw new IllegalArgumentException("cannot get negative element");
    	}
    	if (pos == 0) {
    		return this.item();
    	}
    	x = this;
    	while (pos > 0) {
    		x = x.next();
    		pos--;
    	}
    	return x.item();
    }

}

