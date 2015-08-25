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
    	if (pos < 0) {
    		throw new IllegalArgumentException("Input is negative");
    	}
    	ListNode currentNode = this;
    	for (int i = pos; i > 0; i --) {
    		if (currentNode.next() == null) {
    			throw new IllegalArgumentException("Input is too big");
    		}
    		currentNode = currentNode.next();
    	}
    	return currentNode.item();
    }

}

