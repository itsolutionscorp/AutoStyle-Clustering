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
    	if(pos == 0){
    		return this.item();
    	}
    	else{
    		if (next() == null){
    			throw new IllegalArgumentException("Out of Bound");
    		}
    		else{
    			return next().get(pos-1);
    		}
    
    	   
    	 
    	}
    }

}

