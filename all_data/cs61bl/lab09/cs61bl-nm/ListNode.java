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
    	ListNode rtnList = this;
    	int i = 0;
    	while (i < index) {
    		rtnList = rtnList.next();
    		if (rtnList == null) {
    			throw new IllegalArgumentException();
    		}
    		i++;
    	}
    	return rtnList;
    }

}

