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
    	if (next() == null && pos > 0)
    		throw new IllegalArgumentException("Position out of bounds");
    	
    	if (pos == 0)
    		return item();
    	return next().get(pos - 1);
    }
}