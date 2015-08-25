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
    	ListNode cycleThrough = this;//TODO: How do I make the variable Cycle through refer to itself?
    	for (int i = 0; i < pos ; i++) {
    		cycleThrough = cycleThrough.next();
    	}
    	
    	
    	return cycleThrough.item();
    }

}

