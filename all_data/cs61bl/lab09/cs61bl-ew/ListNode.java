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
    public Object get(int pos) throws IllegalArgumentException{
    	if (pos < 0){
    		throw new IllegalArgumentException ("Enter a valid position.");
    	}
    	while (pos > 0){
    		try{
    			return this.myNext.get(pos-1);
    		}
    		catch (NullPointerException e){
    			throw new IllegalArgumentException ("Enter a valid position.");
    		}
    	}
    	return this.myItem;
    }

}

