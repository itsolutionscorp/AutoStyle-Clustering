import java.util.Iterator;
import java.util.LinkedList;

public class BST {
	BSTNode myRoot;

	public BST(LinkedList list) {
		myRoot = linkedListToTree(list.iterator(), list.size());
	}

	// Your comment here
	/**
	 * Uses the given LinkedList iterator to iterate over the items of a sorted
	 * list of size n. Over the course of the iteration, builds a balanced tree
	 * with n nodes, whose inorder traversal yields the same items as the
	 * original LinkedList, in the same order.
	 * 
	 * @param iter
	 *            An iterator over the items of a given LinkedList
	 * @param n
	 *            The number of items in the LinkedList. Also the number of
	 *            nodes in the resulting tree.
	 * @return The root of the resulting BST that contains the same items as the
	 *         original LinkedList.
	 */
	private static BSTNode linkedListToTree(Iterator iter, int n) {
		// TODO Your code here
		BSTNode rv = new BSTNode();
		if (n <= 0) {
			return null;
		} else if (n == 1) {
			rv.myItem = iter.next();
			return rv;
		} else if (n % 2 == 0) {
			rv.myLeft = linkedListToTree(iter, n / 2);
			rv.myItem = iter.next();
			rv.myRight = linkedListToTree(iter, n / 2 - 1);
		} else {
			rv.myLeft = linkedListToTree(iter, n / 2);
			rv.myItem = iter.next();
			rv.myRight = linkedListToTree(iter, n / 2);
		}
		return rv;
	}

	private static class BSTNode {
		Object myItem;
		BSTNode myLeft;
		BSTNode myRight;
	}
}
