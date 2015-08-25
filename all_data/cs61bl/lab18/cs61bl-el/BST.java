import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	/**
	 * Takes in an iterator of objects that are already in order and returns a
	 * balanced binary search tree.
	 * 
	 * @param iter
	 *            Iterator of sorted objects
	 * @param n
	 *            Size of the original linked list
	 * @return a BSTNode tree
	 */
	private BSTNode linkedListToTree(Iterator iter, int n) {
		// TODO Your code here

		Object[] copy = new Object[n];

		for (int i = 0; i < n; i++) {
			copy[i] = iter.next();
		}

		BSTNode rtn = new BSTNode(copy[n / 2], helper(copy, n / 2 - 1, true),
				helper(copy, n / 2 + 1, false));
		return rtn;

	}

	private BSTNode helper(Object[] l, int n, boolean left) {
		if (n < 0 || n == l.length) {
			return null;
		}
		if (!left) {
			BSTNode child = new BSTNode(l[n], null, helper(l, n + 1, false));
			return child;
		} else {
			BSTNode child = new BSTNode(l[n], helper(l, n - 1, true), null);
			return child;
		}
	}

	private class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;

		public BSTNode(Object item, BSTNode left, BSTNode right) {
			myItem = item;
			myLeft = left;
			myRight = right;
		}
	}

	public static void main(String[] args) {
		LinkedList a = new LinkedList();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);

		BST myTree = new BST(a);
		System.out.println(myTree.myRoot.myItem);
	}
	
}
