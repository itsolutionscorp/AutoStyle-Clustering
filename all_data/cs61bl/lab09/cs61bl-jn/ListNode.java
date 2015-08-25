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
    public Object get (int i) {
    		if (i < 0) {
    			throw new IllegalArgumentException();
    		}
    		if (i == 0) {
				return item();
			}
    		
    		if (myNext==null){
    			if (i == 0) {
    				return item();
    			}
    			throw new IllegalArgumentException();
    		
    		} else {
    			return myNext.get(i - 1);
    		}
    }


}

