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
    
    public Object get(int i) {
//    	if (myNext == null || i < 0) {
//    		throw new IllegalArgumentException("The index is out of bounds");
//    	}
    	if (i == 0) {
    		return myItem;
    	}
    	else if (i < 0){
    		throw new IllegalArgumentException("The index is out of bounds");
    	}
    	else {
    		if (myNext == null) {
    			throw new IllegalArgumentException("The index is out of bounds");
    		}
    		return myNext.get(i - 1);
    	}
    }
    
//    public static void main(String[] args) {
//    	ListNode a = new ListNode("a");
//    	ListNode b = new ListNode("b");
//    	ListNode c = new ListNode("c");
//    	//ListNode d = new ListNode(null);
//    	a.myNext = b;
//    	b.myNext = c;
//    	c.myNext = null;
//    	System.out.println(a.get(3));
//    	//System.out.println(a.get(-1));
//    }

}

