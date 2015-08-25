import java.util.*;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	// Builds the left subtree recursively, adds the left to a root node, and
	// then makes the right subtree recursively, adding this as the right child
	// of the root, then returns the balanced tree.
	private BSTNode linkedListToTree(Iterator iter, int n) {
		if (n == 0 || !iter.hasNext()) {
			return null;
		}
		if (n / 2 == 0 && iter.hasNext()) {
			BSTNode leaf = new BSTNode(iter.next());
			return leaf;
		}

		int k = 1;
		while (k < n) {
			k = k * 2;
		}
		k = k / 2;
		BSTNode left = linkedListToTree(iter, k);
		BSTNode root = new BSTNode(iter.next());
		root.myLeft = left;
		BSTNode right = linkedListToTree(iter, n - k - 1);
		root.myRight = right;
		return root;
	}

	private class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;

		public BSTNode(Object b) {
			myItem = b;
			myLeft = myRight = null;
		}
	}

}
