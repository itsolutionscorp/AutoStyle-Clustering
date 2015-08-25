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
    public Object get(int k){
    	if(k<0){
    		throw new IllegalArgumentException("Input positive number");
    	}
    	if(k==0){
    		return this.myItem;
    	}
    	if(this.next()==null){
    		throw new IllegalArgumentException("Out of bounds");
    	}
    	return this.next().get(k-1);
    }
}

