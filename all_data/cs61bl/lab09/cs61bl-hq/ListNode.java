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
    
    public Object get(int position) {
    	int count = 0;
    	ListNode nextNode = this;
    	while (count < position) {
    		if (myNext == null) {
    			throw new IllegalArgumentException();
    		}
    		nextNode = nextNode.next();
    		myItem = nextNode.item();
    		count++;
    	}
    	return myItem;
    }

}

