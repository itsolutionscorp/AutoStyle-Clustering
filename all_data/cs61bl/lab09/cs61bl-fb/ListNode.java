public class ListNode {

    private Object myItem;
    private ListNode myNext;

    public ListNode(Object item, ListNode next) {
        myItem = item;
        myNext = next;
    }
    public boolean isEmpty(){
    	return (this.item()==null);
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
    public Object get(int element_At) {
    	
    	ListNode placeholder=this;
    	for(int i=0; i<element_At;i++){
    	    if(placeholder.next() == null){
    	    	throw new IllegalArgumentException("User Thrown Exception : Index out of range");
    	    }
    		placeholder = placeholder.next();
    	}
        return placeholder.item();
    }
    

}

