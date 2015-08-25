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

    public ListNode get(int pos) {
        int i = 0;
        ListNode node = this;
        while (i < pos) {
            node = this.next();
            i ++;
        }
        return node;
    }

}

