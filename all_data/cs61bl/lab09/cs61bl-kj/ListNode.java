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
    	ListNode temp = this;
    	int counter = 0;
    	while (counter < pos) {
    		if (temp == null) {
    			throw new IllegalArgumentException("out of range");
    		}
    		temp = temp.next();
    		counter ++;
    		
    	}
    	return temp.item();
    	
    }

}

