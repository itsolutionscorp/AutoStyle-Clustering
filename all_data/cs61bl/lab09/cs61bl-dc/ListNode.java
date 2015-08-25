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

    public Object get(int index) throws IllegalArgumentException
    {
    	if (index == 0)
    		return this.myItem;
    	if (this.myNext == null)
    		throw new IllegalArgumentException("Index is out of bounds");
    	return myNext.get(index - 1);
    }
}

