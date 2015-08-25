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
    	 ListNode current = this;
    	 for(int i = 0; i < pos; i++){
    		 current = current.next();
    	 } return current.item();
    }
}

