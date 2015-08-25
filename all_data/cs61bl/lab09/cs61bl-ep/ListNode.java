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
    
    /* get takes an int position as argument, and returns the list element 
     * at the given position in the list, starting with element zero. For 
     * example, if get(1) is called, you should return the second item in 
     * the list. If the position is out of range, get should throw 
     * IllegalArgumentException with an appropriate error message. Assume 
     * get is called on the first node in the list.
     */
    public ListNode get(int pos) {
    	if (pos == 0) {
    		return this;
    	} else if (pos > 0 && myNext != null) {
    		return myNext.get(pos-1);
    	} else {
    		throw new IllegalArgumentException("That is not a valid position!");
    	}
    }
    
    /*public static void main (String[] args) {
    	ListNode third = new ListNode(3);
    	ListNode second = new ListNode(2, third);
    	ListNode first = new ListNode(1, second);
    	System.out.print(first.get(0).item());
    	System.out.print(first.get(1).item()); 
    	System.out.println(first.get(2).item()); //123
    	ListNode a = new ListNode("doin'?");
    	ListNode b = new ListNode("you ", a);
    	ListNode c = new ListNode("How ", b); 
    	System.out.print(c.get(0).item()); 
    	System.out.print(c.get(1).item()); 
    	System.out.println(c.get(2).item()); //How you doin'?
    	System.out.println(c.get(2).next()); // null
    	System.out.println(c.get(3)); // IllegalArgumentException
    }*/

}

