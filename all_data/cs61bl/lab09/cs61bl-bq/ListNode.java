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
    
    public ListNode get(int pos) {
    	
    	if (pos < 0) {
    		throw new IllegalArgumentException("Position can't be less than zero");
    	}
    	ListNode n = this;
    	while(pos>0) {
    		if(n.next() == null) {
    			throw new IllegalArgumentException("Position out of bounds.");
    		}
    		n = n.next();
    		pos--;
    	}
    	return n;
    }
    
   

}

