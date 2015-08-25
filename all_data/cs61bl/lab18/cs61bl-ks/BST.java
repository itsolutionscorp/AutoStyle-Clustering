import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	// Builds a balanced tree from the ordered linkedlist.
	private static BSTNode linkedListToTree(Iterator iter, int n) {
		if (n == 0) {
			return null;
		}

		if (n == 1) {
			if (iter.hasNext())
				return new BSTNode(iter.next());
		}

		int myRootIndex = n / 2 + 1;
		LinkedList leftList = new LinkedList();
		int counter = 1;
		while (counter < myRootIndex) {
			leftList.add(iter.next());
			counter++;
		}
		Object root = iter.next();
		// BSTNode root = new BSTNode(iter.next());
		LinkedList rightList = new LinkedList();
		while (iter.hasNext()) {
			rightList.add(iter.next());
		}

		if (n % 2 == 0) {
			return new BSTNode(root, linkedListToTree(leftList.iterator(),
					n / 2), linkedListToTree(rightList.iterator(), n / 2 - 1));
		} else {
			return new BSTNode(root, linkedListToTree(leftList.iterator(),
					n / 2), linkedListToTree(rightList.iterator(), n / 2));
		}

	}

	public String toString() {
		if (myRoot != null) {
			return myRoot.toString();

		}
		return null;

	}

	private static class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;

		public BSTNode(Object item) {
			myLeft = null;
			myRight = null;
			myItem = item;
		}

		public BSTNode(Object item, BSTNode left, BSTNode right) {
			myLeft = left;
			myRight = right;
			myItem = item;
		}

		public Object getItem() {
			return myItem;
		}

		public String toString() {
			if (myItem != null) {
				String stuff = myItem.toString();
				if (myLeft!=null){
					stuff += myLeft.toString();
				}
				if (myRight!=null){
					stuff+= myRight.toString();
				}
				return stuff;
			}
			return null;
		}
	}
}
