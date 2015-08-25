public class ListNode extends AbstractListNode{

    private Object myItem;
    private AbstractListNode myNext;

    public ListNode(Object item, AbstractListNode next) {
        myItem = item;
        myNext = next;
    }
    public ListNode(Object item) {
        this(item, null);
    }

    public Object item() {
        return myItem;
    }
    public AbstractListNode next() {
        return myNext;
    }
    
    public static void main(String args[]){
    	AbstractListNode empty = new EmptyListNode();
    	ListNode node = new ListNode(3,new ListNode(5,new ListNode(6, null)));
    	System.out.println(node.get(0));
    	System.out.println(node.get(1));
    	System.out.println(node.get(2));

    	
    }
    
}

