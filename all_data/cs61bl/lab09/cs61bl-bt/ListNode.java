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

    public Object get(int position){
    	Object x = this;
    	for (int i = 0; i < position; i++){
    		if (x != null){
        		x = ((ListNode) x).next();
    		}
    		else{
    			throw new IllegalArgumentException("Position out of range.");
    		}
    	}
    	return ((ListNode) x).item();
    }
}

