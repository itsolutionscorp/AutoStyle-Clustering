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
    
    /**
     * Implement a method get in the ListNode class.
     * get takes an int position as argument, and returns
     * the list element at the given position in the list,
     * starting with element zero.For example, if get(1)
     * is called, you should return the second item in
     * the list. If the position is out of range, get
     * should throw IllegalArgumentException with an
     * appropriate error message. Assume get is called
     * on the first node in the list.
     * *
     * First write JUnit tests to provide evidence of the get method correctness.
     * Then add the get method to your ListNode class.
     */
    
    public Object get(int pos) {
    	ListNode ln = this;
    	int i = 0;
    	while (i < pos) {
    		if (ln.next() == null) {
    			throw new IllegalArgumentException("Out of index");
    		}
    		ln = ln.next();
    		i++;
    	}
    	return ln.item();
    }
}

