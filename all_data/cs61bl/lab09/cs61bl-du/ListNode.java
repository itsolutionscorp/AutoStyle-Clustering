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
    public Object get(int position) {
    	

    	if (position == 0) {
    		return myItem;
    	} else {
        	if (next() == null) {
        		throw new IllegalArgumentException();
        	} else {
    		return next().get(position - 1);
        	}
    		
    	}
    }
    /*
	public static void main (String[] args) {

		ListNode test = new ListNode("cat", new ListNode("dog", new ListNode("giraffe")));
		System.out.println(test.get(2));
	}
    */
    
}

