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
    
    public Object get(int i) {
    	ListNode L = new ListNode(myItem, myNext);
    	while (i > 0) {
    		if (L.next() == null) {
    			throw new IllegalArgumentException("index out of range");
    		}
    		L.myItem = L.next().item();
    		L.myNext = L.next().next();
    		i--;
    	}
    	return L.myItem;
    }

}

