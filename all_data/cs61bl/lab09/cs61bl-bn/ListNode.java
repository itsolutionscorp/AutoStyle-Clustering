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

	public Object get(int position) throws IllegalArgumentException {
		ListNode placeHolder = this;

		for (int k = 0; k < position; k++) {
			placeHolder = placeHolder.next();

			if (placeHolder == null) {
				throw new IllegalArgumentException();
			}
		}
		return placeHolder;
	}

	public static void main(String[] args) {
		ListNode list1 = new ListNode((Integer) (1));
		ListNode list2 = new ListNode((Integer) (2));
		ListNode list3 = new ListNode((Integer) (3));

		list1.myNext = list2;
		list2.myNext = list3;
		try {
			System.out.println(list1.get(3) == list3);
		} catch (IllegalArgumentException e) {
			System.out.println("out of bounds");
		}
	}
}
