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
    
    public Object get(int a){
    	ListNode node = this;
    	int i = 0;
    	if(a <0){
    		throw new IllegalArgumentException("Wrong argument");
    	}
    	while(a > i){
    		if(node.next()!=null){
    		node = node.next();
    		}else{
    			throw new IllegalArgumentException("Argument out of bounds");
    		}
    		i++;
    	}
    	return node.myItem;
    }

}

