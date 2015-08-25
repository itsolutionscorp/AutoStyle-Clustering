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
//    public boolean isEmpty() {
//    	return (this == null);
//    }
    public Object get(int idx) {
    	int i = 0;
    	ListNode temp = this.next();
    	while (temp.next() != null) {
    		temp = temp.next();
    		if (i == idx) {
    			return temp.item();
    		}
    		i+=1;
    	}
    	if (idx > i) {
    		throw new IllegalArgumentException("ont of bounds");
    	}
		return temp.item();
    }

}

