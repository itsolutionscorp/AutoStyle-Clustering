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

	public Object get(int position) {
		int counter = 0;
		ListNode result = this;
		while (counter < position) {
			if (result.next() == null) {
				throw new IllegalArgumentException("No node exists");
			}

			counter++;
			result = result.next();
		}
		
		return result.item();
	}
}
