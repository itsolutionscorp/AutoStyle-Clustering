public class ListNode {

    private Object myItem;
    private ListNode myNext;
    private int counter = 0;
    private int length = 0;
    private ListNode a;
   

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
//    public int length() {
//    	
//    	if (myNext != null) {
//    		next();
//    		length ++;	
//    	}
//    	
//    	return length;
//    }
//    
    
    public Object get(int position) {
    	
    	if (next() == null && position != 0) {
    		throw new IllegalArgumentException("Position out of range");
    	}
    	
    	else if (position == 0) {
    		return myItem;
    	}
    	else {
    		next().get(position -1);
    		
    	}
    	return myItem;
    }
    

}

