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
    public Object get(int x) throws IllegalArgumentException{
    	
    	ListNode currentNode = this;
    	for (int i =0; i<=x; i++){
    		if (i !=x ){
    			currentNode = currentNode.next();
    			
    			if (currentNode == null){
    				throw new IllegalArgumentException("wrong");
    			}
    		}
    		else{
    			return currentNode.item();
    		}
    	}
    	
    	return item();
    } 
    

}
