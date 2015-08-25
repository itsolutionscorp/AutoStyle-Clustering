import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	/**
	 * BST Node that converts the given iterator into a balanced BST Node based
	 * on the length of the iterator.
	 * 
	 * @param iter
	 *            the given iterator based on the linked list
	 * @param n
	 *            the size of the linked list
	 * @return a balanced BST Node
	 */
	private static BSTNode linkedListToTree(Iterator iter, int n) {
		// TODO Your code here
		int middle;
		if (n == 0) {
			return null;
		} else if (n == 1) {
			BSTNode root = new BSTNode();
			root.myItem = iter.next();
			return root;
		} else if (n == 2) {
			BSTNode leftChild = new BSTNode();
			leftChild.myItem = iter.next();
			BSTNode parent = new BSTNode();
			parent.myItem = iter.next();
			parent.myLeft = leftChild;
			return parent;
		} else {
			middle = n / 2;
			BSTNode left = linkedListToTree(iter, middle);
			BSTNode parent = new BSTNode();
			parent.myItem = iter.next();
			BSTNode right = linkedListToTree(iter, n - middle - 1);
			parent.myLeft = left;
			parent.myRight = right;
			return parent;
		}
	}

	private static class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;
	}
}
