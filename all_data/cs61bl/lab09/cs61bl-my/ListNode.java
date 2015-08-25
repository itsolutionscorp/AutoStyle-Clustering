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
    
    public Object get(int num) {
    	if (num == 0) {
    		return item();
    	} else {
    		if ((next() == null) || num < 0) {
        		throw new IllegalArgumentException("Index Out of Range");
        	} 
    		return next().get(num-1);
    	}
    }

}

