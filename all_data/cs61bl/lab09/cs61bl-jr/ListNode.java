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
    	int count = 0;
    	ListNode temp = new ListNode (myItem, myNext);
    	while (temp.next() != null) {
    		temp = temp.next();
    		count++;
    	}
    	if (pos < 0 || pos > count) {
    		throw new IllegalArgumentException("Invalid argument");
    	}
    	if (pos == 0){
    		return myItem;
    	} else {
    		return next().get(pos-1);
    	}
    }

}

