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
    
    public Object get(int i){

    	if(i == 0){
    		return myItem;
    	}else{
    		if(myNext == null){
    			throw new IllegalArgumentException();
    		}
    		return myNext.get(i -1);
    	}
    }

}

