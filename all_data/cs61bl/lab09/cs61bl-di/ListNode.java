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
    	
    	if ((position > 0 && myNext == null) || position < 0){
    		throw new IllegalArgumentException("Number is out of bounds");
    	} else if (position==0) {
    			return this.item();
    	} else {
    		return this.myNext.get(position-1);
    	}	
    }
}

