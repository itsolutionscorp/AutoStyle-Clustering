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
    
    public Object get (int position){
    	if(position< 0){
    		throw new IllegalArgumentException("ERROR, positon is out of bond");
    	}
    	else{
    		ListNode a = this.next();
    		for (int i = 1; i < position; i++) {
                if (a.next() == null)
                    throw new IllegalArgumentException("error");
                
                else{
                	a = a.next();
                }
            }
           return this.item();
           
    	}
    	
    	
    }
    

}

