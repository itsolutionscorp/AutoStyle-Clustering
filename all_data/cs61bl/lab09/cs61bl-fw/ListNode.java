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
    	ListNode cur = this;
    	
    	if (pos < 0) {
    		throw new IllegalArgumentException("Position out of bound!");
    	}
    	
    	while (cur != null && pos > 0) {
    		cur = cur.next();
    		pos--;
    	}
    	
    	if (cur == null || pos > 0) {
    		throw new IllegalArgumentException("Position out of bound!");
    	}
    	
    	return cur.myItem;
    }

}

