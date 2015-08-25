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
    	if (pos < 0 || (pos > 0 && myNext == null)){
    		throw new IllegalArgumentException();
    	} 
    	if (pos == 0) {
			return item();
		}else {
			return myNext.get(pos-1);
		}
    }

}

