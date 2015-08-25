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
    
    public Object get(int n) {
    	if (n==0){
    		return this.myItem;
    	}
    	if (this.myNext != null){
    		return this.myNext.get(n-1);    		
    	} else {
    		throw new IllegalArgumentException("get out of range");
    	}
    }

}

