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
    
    public Object get(int a) {
    	if (a == 0) {
    		return this.item();
    	} else {
    		return this.next().get(a-1);
    	}
    }

}

