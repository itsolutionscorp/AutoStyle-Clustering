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
    
    public ListNode get(int index) {
    	ListNode next = myNext;
    	for (int i = 0; i < index; i++) {
    		try {
    			next = next.next();
    		} catch (IllegalArgumentException e) {
    			System.err.println("index out of bounds");
    		}
    	}
    	return next;
    }		

    public static void main (String[] args) {
    	ListNode a = new ListNode(1, new ListNode(2, new ListNode(3, null)));
    }
}

