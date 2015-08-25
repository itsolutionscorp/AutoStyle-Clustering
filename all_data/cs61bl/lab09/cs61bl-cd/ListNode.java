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
    
    public Object get(int index) {
    	int i = 1;
    	ListNode nextNode = this;
    	while (i < index){
    		nextNode = nextNode.next();
    		if(nextNode == null){
    			throw new IllegalArgumentException();
    		}
    		i++;
    	}
    	return nextNode.item();
    }

}

