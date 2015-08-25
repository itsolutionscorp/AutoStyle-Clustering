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
    
    public Object get(int x){
    	int count = -1;
    	for(ListNode temp = this; temp != null; temp = temp.next()){ 	
    		 count++;
    		 if(count == x){
    			 return temp.item();
    		 }
    	}
		throw new IllegalArgumentException("position out of range.");
    }

}

