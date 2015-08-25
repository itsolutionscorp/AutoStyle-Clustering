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
    
    public ListNode get(int n){
//    	int index = 0;
//    	if (next() == null && n > 1) {
//    		throw new IllegalArgumentException("argument out of range.");
//    	} else if (n == 0) {
//    		return item();
//    	} else {
//    		return get(n - 1);
//    	}
//    	
    return null;	
    }

}

