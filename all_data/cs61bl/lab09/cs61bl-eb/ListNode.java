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
    
    /*********************** our own methods ***********************/
    
    public Object get(int pos) {
    	int num_times_travel = 0;
    	ListNode p = this; // p points to the current node
    	while (p.myNext != null && num_times_travel != pos) {
    		// the next thing is not null and still needs further travel until pos times
    		p = p.myNext;
    		++num_times_travel;
    	}
    	if (num_times_travel != pos) {
    		// you require too many travels and it's already the end of list
    		throw new IllegalArgumentException("you require too many travels and it's already the end of list!");
    	}
    	return p.myItem;
    }
    
    

}

