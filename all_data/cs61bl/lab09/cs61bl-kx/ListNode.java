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
    
    public Object get(int pos){
    	if(pos<0 || (next()==null && pos!=1)){
    		throw new IllegalArgumentException("position out of range");
    	}
    	else if(pos==0){
    		return item();
    	}
    	else if(next()!=null){
    		return next().get(pos - 1);
    	}
    	return null;
    }

}

