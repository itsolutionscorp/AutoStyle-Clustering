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
        if (pos == 0) {
            return item();
        }
        if (next() == null) {
            throw new IndexOutOfBoundsException("List index out of bounds");
        }
        return next().get(pos-1);
    }

    public static void main(String[] args) {
        ListNode last = new ListNode("fourth");
        ListNode third = new ListNode("third", last);
        ListNode second = new ListNode("second", third);
        ListNode first = new ListNode("first", second);
        System.out.println(first.get(0));
        System.out.println(first.get(1));
        System.out.println(second.get(0));
        System.out.println(first.get(2));
        System.out.println(second.get(1));
        System.out.println(third.get(0));
        System.out.println(first.get(3));
        System.out.println(second.get(2));
        System.out.println(third.get(1));
        System.out.println(last.get(0));
    }

}
