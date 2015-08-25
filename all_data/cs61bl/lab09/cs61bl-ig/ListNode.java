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
   public Object get(int index){
	   if(index < 0) throw new IllegalArgumentException("");
	   ListNode n = this;
	   for (int i=0; i<index; i++) {
		   if(n == null) throw new IllegalArgumentException("");
		   n=n.next();
	   }
	   return n.item();
    }

}

