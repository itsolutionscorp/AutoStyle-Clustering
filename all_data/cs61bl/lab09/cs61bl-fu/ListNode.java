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
    
    public Object get (int i) {
    	if (i < 0) {
    		throw new IllegalArgumentException("Must be a positive index.");
    	}
        ListNode current = this;
        int counter = 0;
        while  (counter < i) {
        	if (current.myNext == null) {
        		throw new IllegalArgumentException("Requested Position is out of Range.");
        	}
        	current = current.myNext;
        	counter ++;
        }
        return current.myItem;
    }

}

