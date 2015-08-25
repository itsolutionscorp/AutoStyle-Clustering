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
    
    public int size(){
    	int i = 0;
    	for(ListNode x = this; x.next() != null; x = x.next()){
    		i++;
    	}
    	return i;
    }
   
    
    public Object get(int n){
    	if(n < 0){
    		throw new IllegalArgumentException("Index cannot be negative.");
    	}if (n > this.size()){
    		throw new IllegalArgumentException("Index cannot be greater than length of linked list.");
    	}
    	int j = 0;
    	for(ListNode x = this; x != null; x = x.next()){
    		if(j == n){
    			return x.item();
    		}
    		j++;
    	}
    		return null;
    	}
    }

