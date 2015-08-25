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
    	ListNode A = this;
    	int position = pos;

    	if (position<0)
    		throw new IllegalArgumentException("Position is invalid");
    	while (position>0) {
    		A = A.next();
    		position--;
    		if (A==null) {
    			throw new IllegalArgumentException("Position is invalid");
    		}
    		if (position==0) {
    			return A.item();
    		}
    	}
    	return null;
    }
}

