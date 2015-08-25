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
    	int n = 0;
    	Object current = this.myItem;
    	ListNode currentNode = this;
    	while (i > n) {
    		if (currentNode.myItem == null) {
    			throw new IllegalArgumentException("Position is out of range.");
    		}
    		currentNode = currentNode.next();
    		current = currentNode.myItem;
    		n++;	
    	}
		return current;
    }

}

