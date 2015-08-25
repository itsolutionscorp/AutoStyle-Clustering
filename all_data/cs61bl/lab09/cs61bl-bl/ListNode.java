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
    public Object get(int pos) {
        ListNode temp = this;
        while (pos > 0) {
            if (temp == null) {
                throw new IllegalArgumentException("Position out of bounds.");
            }
            pos -= 1;
            temp = myNext;
        }
        return temp.myItem;
    }
}

