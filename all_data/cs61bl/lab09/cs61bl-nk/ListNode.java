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
    public Object get(int i) throws IllegalArgumentException{
    	int k = 0;
    	Object temp1 = this.item();
    	ListNode temp2 = this.next();
    	while (k < i) {
    		if (temp2 == null) {
    			throw new IllegalArgumentException();
    		}
    		temp1 = temp2.item();
    		temp2 = temp2.next();
    		
    		k++;
    	}
    	return temp1;
    }
    public static void main (String[] args) {
    	ListNode mylist1 = new ListNode(3);
    	ListNode mylist2 = new ListNode(2, mylist1);
    	ListNode mylist3 = new ListNode(1, mylist2);
    	System.out.println(mylist3.item());
    	System.out.println(mylist3.next().item());
    	System.out.println(mylist3.next().next().item());
    	System.out.println(mylist3.get(0));
    	System.out.println(mylist3.get(1));
    	System.out.println(mylist3.get(2));
    }

}