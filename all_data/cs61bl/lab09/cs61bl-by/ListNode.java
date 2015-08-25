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
    
    public Object get(int i){    

    if (i==0){

               return this.item();

    } else {

            if (this.next()==null){

          throw new IllegalArgumentException("Out of range");

            }

    return (this.next()).get(i-1);

    }

    }
    
    
    
    public static void main(String[] args){
    	ListNode n = new ListNode(1, new ListNode(2, new ListNode(3)));
    	ListNode m = n.next();
    	ListNode p = m.next();
    	System.out.println(n.item());
    	System.out.println(m.item());
    	System.out.println(p.item());
    	System.out.println(n.get(0));
    	System.out.println(n.get(1));
    	System.out.println(n.get(2));
    }

}

